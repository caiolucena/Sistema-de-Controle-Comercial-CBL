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
		
	}
	
	return TabelaItens;
	
}());

$(function() {
	
	var autocomplete = new Erp.Autocomplete();
	autocomplete.iniciar();
	
	var tabelaItens = new Erp.TabelaItens(autocomplete);
	tabelaItens.iniciar();
	
});