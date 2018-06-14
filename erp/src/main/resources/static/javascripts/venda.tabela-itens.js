Erp.TabelaItens = (function() {
	
	function TabelaItens(autocomplete) {
		this.autocomplete = autocomplete;
		this.tabelaProdutosContainer = $('.js-tabela-produtos-container');
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
		$('.js-tabela-produto-quantidade-item').on('keypress',onQuantidadeItemAlterado.bind(this));
		
	}
	function onQuantidadeItemAlterado(evento){
		var input = $(evento.target);
		var quantidade = input.val();
		console.log('novaQuantidade',quantidade);
		
		var idProduto = input.data('codigo-produto');
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