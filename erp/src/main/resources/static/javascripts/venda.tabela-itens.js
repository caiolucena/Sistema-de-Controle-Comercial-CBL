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
		
		var quantidadeItemInput =  $('.js-tabela-produto-quantidade-item');
		quantidadeItemInput.on('change',onQuantidadeItemAlterado.bind(this));
		quantidadeItemInput.maskMoney({precision:0,thousands:''});
		
		var tabelaItem = $('.bw-tabela-item');
		tabelaItem.on('dblclick',onClick);
		$('.js-exclusao-item-btn').on('click',onExclusaoItem.bind(this));
		
		this.emitter.trigger('tabela-itens-atualizada',tabelaItem.data('valor-total'));
		
		
		
	}
	function onQuantidadeItemAlterado(evento) {
		var input = $(evento.target);
		var quantidade = input.val();
		
		if(quantidade<=0){
			input.val(1);
			quantidade =1;
		}
		
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
	
	function onClick(evento){		
		$(this).toggleClass('solicitando-exclusao');
		
	}
	
	function onExclusaoItem(evento){
		
		var idProduto = $(evento.target).data('codigo-cerveja');
		var resposta = $.ajax({
			url:'item/'+ idProduto,
			method:'DELETE'
			
		});
		resposta.done(onItemAtualizadoNoServidor.bind(this));
	}
	
	return TabelaItens;
	
}());
