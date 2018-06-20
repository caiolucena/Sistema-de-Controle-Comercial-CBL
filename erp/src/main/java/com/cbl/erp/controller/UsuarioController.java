package com.cbl.erp.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.cbl.erp.model.Usuario;
import com.cbl.erp.repository.Grupos;
import com.cbl.erp.repository.Usuarios;
import com.cbl.erp.service.CadastroGrupoService;
import com.cbl.erp.service.CadastroUsuarioService;
import com.cbl.erp.service.exception.ImpossivelExcluirEntidadeException;
import com.cbl.erp.service.exception.ItemDuplicadoException;
import com.cbl.erp.service.exception.LoginDuplicadoException;
import com.cbl.erp.service.exception.SenhaObrigatoriaUsuarioException;

/**
 * Essa é a classe Controller da classe Usuario, e é responsável por fazer a
 * ponte entre as views referentes a esse objeto e os Models, de acordo com as
 * solicitações realizadas nas rotas.
 * 
 * @author EquipeACL
 *
 */
@Controller
@RequestMapping("/usuarios")
public class UsuarioController {

	@Autowired
	private CadastroUsuarioService cadastroUsuarioService;

	@Autowired
	private CadastroGrupoService cadastroGrupoService;

	@Autowired
	private Usuarios usuarios;
	//
	@Autowired
	private Grupos grupos;

	/**
	 * Esse método é responsável por adicionar os parâmetros que vão ser exibidos na
	 * view renderizada ao acessar a rota usuarios/novo
	 * 
	 * @param usuario,
	 *            que é o objeto a ser acessado
	 * @return mv, que é um objeto ModelAndView que contém os parâmetros que foram
	 *         adicionados para exibir na view.
	 */
	@GetMapping("/novo")
	public ModelAndView novo(Usuario usuario) {
		ModelAndView mv = new ModelAndView("usuario/CadastroUsuario");
		mv.addObject("grupos", cadastroGrupoService.buscaGrupos());

		return mv;

	}

	@RequestMapping(value = { "novo", "{\\d+}" }, method = RequestMethod.POST)
	public ModelAndView cadastro(@Valid Usuario usuario, BindingResult result, RedirectAttributes attributes,
			Model model) {
		if (result.hasErrors()) {
			return novo(usuario);
		}
		String mensagem;
		if (usuario.isNovo()) {
			mensagem = "Usuario salvo com sucesso!";
		} else {
			mensagem = "Usuario atualizado com sucesso!";
		}
		try {
			cadastroUsuarioService.salvar(usuario);
		} catch (ItemDuplicadoException e) {
			result.rejectValue("cpf", e.getMessage(), e.getMessage());
			return (novo(usuario));
		} catch (LoginDuplicadoException e) {
			result.rejectValue("login", e.getMessage(), e.getMessage());
			return (novo(usuario));
		} catch (SenhaObrigatoriaUsuarioException e) {
			result.rejectValue("senha", e.getMessage(), e.getMessage());
		}
		attributes.addFlashAttribute("mensagem", mensagem);

		return new ModelAndView("redirect:/usuarios/novo");

	}

	@RequestMapping(path = "/busca", method = RequestMethod.GET)
	public ModelAndView retornarTodos() {
		ModelAndView mv = new ModelAndView("usuario/ProcurarUsuario");
		mv.addObject("usuarios", cadastroUsuarioService.buscaUsuarios());
		return mv;
	}

	@DeleteMapping("/{id}")
	public @ResponseBody ResponseEntity<?> excluir(@PathVariable int id) {
		try {
			Usuario usuario = usuarios.findOne(id);
			cadastroUsuarioService.excluir(usuario);
		} catch (ImpossivelExcluirEntidadeException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
		return ResponseEntity.ok().build();
	}

	@GetMapping("/{id}")
	public ModelAndView editar(@PathVariable int id) {
		Usuario usuario = usuarios.findOne(id);
		ModelAndView mv = novo(usuario);
		mv.addObject(usuario);
		return mv;
	}
}
