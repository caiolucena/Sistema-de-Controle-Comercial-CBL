var Erp = Erp || {}
var $ = jQuery;

Erp.PesquisaRapidaCliente = (function() {

	function PesquisaRapidaCliente() {
		this.pesquisaRapidaClientesModal = $('#pesquisaRapidaClientes');
		this.nomeInput = $('#nomeClienteModal');
	}

	PesquisaRapidaCliente.prototype.iniciar = function() {

		$.ajax({
			url : this.pesquisaRapidaClientesModal.find('form').attr('action'),
			method : 'GET',
			contentType : 'application/json',
			data : {
				nome : 'ca'
			},
			success : onPesquisaConcluida.bind(this)
		});
	}

	function onPesquisaConcluida(resultado) {
		console.log('resultado', resultado);
	}

	return PesquisaRapidaCliente;

}());

$(function() {
	var pesquisaRapidaCliente = new Erp.PesquisaRapidaCliente();
	pesquisaRapidaCliente.iniciar();
});