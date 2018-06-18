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
		resposta.done(onItemAtualizadoNoServidor.bind(this));
	}
	
	function onItemAtualizadoNoServidor(html){
		
		this.tabelaProdutosContainer.html(html);
		$('.js-tabela-produto-quantidade-item').on('keypress',onQuantidadeItemAlterado.bind(this));
		
	}
	function onQuantidadeItemAlterado(evento) {
		var input = $(evento.target);
		var quantidade = input.val();
		var codigoCerveja = input.data('codigo-cerveja');
		
		var resposta = $.ajax({
			url: 'item/' + codigoCerveja,
			method: 'POST',
			data:{
				'quantidade': quantidade
			}
		});
		
		resposta.done(onItemAtualizadoNoServidor.bind(this));
	}
	
	return TabelaItens;
	
}());
$(function() {
	
	var autocomplete = new Erp.Autocomplete();
	autocomplete.iniciar();
	
	var tabelaItens = new Erp.TabelaItens(autocomplete);
	tabelaItens.iniciar();
	
});