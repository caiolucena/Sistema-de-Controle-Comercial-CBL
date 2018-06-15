Erp.TabelaItens = (function() {
	
	function TabelaItens(autocomplete) {
		this.autocomplete = autocomplete;
		this.tabelaProdutosContainer = $('.js-tabela-produtos-container');
		this.emitter = $({});
		this.on = this.emitter.on.bind(this.emitter);
	}
	
	TabelaItens.prototype.iniciar = function() {
		this.autocomplete.on('item-selecionado',onItemSelecionado.bind(this));
	}
	
	
	function onItemSelecionado(evento,item){
		var resposta = $.ajax({
			url:'item',
			method: 'POST',
			data:{
				idProduto: item.id
			}
		});
		resposta.done(onItemAdicionadoNoServidor.bind(this));
	}
	
	function onItemAdicionadoNoServidor(html){
		
		this.tabelaProdutosContainer.html(html);
		$('.js-tabela-produto-quantidade-item').on('change',onQuantidadeItemAlterado.bind(this));
		
	}
	function onQuantidadeItemAlterado(evento){
		var input = $(evento.target);
		var quantidade = input.val();
		
		console.log('novaQuantidade',quantidade);
		
		if (quantidade <= 0) {
			input.val(1);
			quantidade = 1;
		}
		
		var idProduto = input.data('codigo-cerveja');
		console.log('idProduto',idProduto);
		
		var resposta = $.ajax({
			url: 'item/'+ idProduto,
			method: 'PUT',
			data:{
				quantidade: quantidade
			}
		});
		resposta.done(onItemAdicionadoNoServidor.bind(this));
	}
	
	return TabelaItens;
	
}());

$(function() {
	
	var autocomplete = new Erp.Autocomplete();
	autocomplete.iniciar();
	
	var tabelaItens = new Erp.TabelaItens(autocomplete);
	tabelaItens.iniciar();
	
});