var Erp = Erp ||{}
var $ = jQuery;

Erp.PesquisaRapidaCliente = (function() {
	
	function PesquisaRapidaCliente() {
		this.pesquisaRapidaClientesModal = $('#pesquisaRapidaClientes');
		this.nomeInput = $('#nomeClienteModal');
		this.pesquisaRapidaBtn = $('.js-pesquisa-rapida-clientes-btn'); 
		this.containerTabelaPesquisa = $('#containerTabelaPesquisaRapidaClientes');
		this.htmlTabelaPesquisa = $('#tabela-pesquisa-rapida-cliente').html();
		this.template = Handlebars.compile(this.htmlTabelaPesquisa);
		this.mensagemErro = $('.js-mensagem-erro');
	}
	
	PesquisaRapidaCliente.prototype.iniciar = function() {
		this.pesquisaRapidaBtn.on('click', onPesquisaRapidaClicado.bind(this));
		this.pesquisaRapidaClientesModal.on('shown.bs.modal', onModalShow.bind(this));
	}
	
	function onModalShow(){
		
		this.nomeInput.focus();
		
	}
	
	function onPesquisaRapidaClicado(event) {
		event.preventDefault();
		
		$.ajax({
			url: this.pesquisaRapidaClientesModal.find('form').attr('action'),
			method: 'GET',
			contentType: 'application/json',
			data: {
				nome: this.nomeInput.val()
			}, 
			success: onPesquisaConcluida.bind(this),
			error: onErroPesquisa.bind(this)
		});
	}
	
	function onPesquisaConcluida(resultado) {
		this.mensagemErro.addClass('hidden');

		var html = this.template(resultado);
		this.containerTabelaPesquisa.html(html);
		
		var tabelaClientePesquisaRapida = new Erp.TabelaClientePesquisaRapida(this.pesquisaRapidaClientesModal);
		tabelaClientePesquisaRapida.iniciar();
		
	} 
	
	function onErroPesquisa() {
		this.mensagemErro.removeClass('hidden');
	}
	
	return PesquisaRapidaCliente;
	
}());

Erp.TabelaClientePesquisaRapida = (function(){
	function TabelaClientePesquisaRapida(modal){

		this.modalCliente = modal;
		
		this.cliente = $('.js-cliente-pesquisa-rapida');
		
	}
	
	TabelaClientePesquisaRapida.prototype.iniciar = function(){
		this.cliente.on('click',onClienteSelecionado.bind(this));

		
	}
	
	function onClienteSelecionado(evento){
		
		this.modalCliente.modal('hide');
		var clienteSelecionado = $(evento.currentTarget);
			$('#nomeCliente').val(clienteSelecionado.data('nome'));
			$('#idCliente').val(clienteSelecionado.data('id'));
		console.log($('#idCliente').val());
		console.log($('#nomeCliente').val());
		
	}
	
	return TabelaClientePesquisaRapida;
}());

$(function() {
	var pesquisaRapidaCliente = new Erp.PesquisaRapidaCliente();
	pesquisaRapidaCliente.iniciar();
});