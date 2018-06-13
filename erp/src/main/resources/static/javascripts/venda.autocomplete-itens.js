Erp = Erp || {};

Erp.Autocomplete = (function() {

	function Autocomplete() {
		this.nomeInput = $('.js-nome-produto-input');
		var htmlTemplateAutocomplete = $('#template-autocomplete-produto').html();
		this.template = Handlebars.compile(htmlTemplateAutocomplete);
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
				method : function(nome,produto){
					return this.template(produto);
				}.bind(this)
			}
		};

		this.nomeInput.easyAutocomplete(options);
	}

	return Autocomplete

}());

$(function() {
	var autocomplete = new Erp.Autocomplete();
	autocomplete.iniciar();
})