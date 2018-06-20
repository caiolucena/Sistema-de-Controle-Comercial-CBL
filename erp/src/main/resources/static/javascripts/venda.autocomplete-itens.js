Erp = Erp || {};

Erp.Autocomplete = (function() {

	function Autocomplete() {
		this.nomeInput = $('.js-nome-produto-input');
		var htmlTemplateAutocomplete = $('#template-autocomplete-produto').html();
		this.template = Handlebars.compile(htmlTemplateAutocomplete);
		this.emitter = $({});
		this.on = this.emitter.on.bind(this.emitter);
		
	}

	Autocomplete.prototype.iniciar = function() {
		var options = {
			url : function(nome) {
				return '/erp/produtos?nome=' + nome;
			},
			getValue : 'nome',
			requestDelay : 300,
			ajaxSettings : {
				contentType : 'application/json'
			},
			template : {
				type : 'custom',
				method : template.bind(this)
			},
			list:{
				onChooseEvent: onItemSelecionado.bind(this)
			}
		};

		this.nomeInput.easyAutocomplete(options);
	}

	function onItemSelecionado(){
		this.nomeInput.val('');
		this.emitter.trigger('item-selecionado',this.nomeInput.getSelectedItemData());
	}
	
	function template(nome,produto){
			produto.valorFormatado = Erp.formatarMoeda(produto.preco);
			return this.template(produto);
		}
	
	return Autocomplete

}());
