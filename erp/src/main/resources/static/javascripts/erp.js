var Erp = Erp ||{}
var $ = jQuery;

Erp.MaskMoney = (function(){
	function MaskMoney(){
		this.decimal =  $('.js-decimal');
		this.plain = $('.js-plain');
	}
	MaskMoney.prototype.enable = function(){
		this.decimal.maskMoney({thousands:'.', decimal:','}); //
		this.plain.maskMoney({precision:0,thousands:"."});
	}
	return MaskMoney;
}());

Erp.MaskPhoneNumber = (function() {
	
	function MaskPhoneNumber() {
		this.inputPhoneNumber = $('.js-phone-number');
	}
	
	MaskPhoneNumber.prototype.enable = function() {
		var maskBehavior = function (val) {
		  return val.replace(/\D/g, '').length === 11 ? '(00) 00000-0000' : '(00) 0000-00009';
		};
		
		var options = {
		  onKeyPress: function(val, e, field, options) {
		      field.mask(maskBehavior.apply({}, arguments), options);
		    }
		};
		
		this.inputPhoneNumber.mask(maskBehavior, options);
	}
	
	return MaskPhoneNumber;
	
}());

Erp.MaskCpf = (function(){

	function MaskCpf(){
		this.inputCpf = $('.js-cpf');
	}
	MaskCpf.prototype.enable = function(){
		this.inputCpf.mask('000.000.000-00', {reverse: true});
		
	}
	
	return MaskCpf;
}());

Erp.MaskCnpj = (function(){

	function MaskCnpj(){
		this.inputCnpj = $('.js-cnpj');
	}
	MaskCnpj.prototype.enable = function(){
		this.inputCnpj.mask('000.000.000/0000-00', {reverse: true});
		
	}
	
	return MaskCnpj;
}());

Erp.MaskCep = (function() {
	
	function MaskCep() {
		this.inputCep = $('.js-cep');
	}
	
	MaskCep.prototype.enable = function() {
		this.inputCep.mask('00.000-000');
	}
	
	return MaskCep;
	
}());

$(function(){

		var maskMoney = new Erp.MaskMoney();
		maskMoney.enable();
		
		var maskPhoneNumber = new Erp.MaskPhoneNumber();
		maskPhoneNumber.enable();
		
		var maskCpf = new Erp.MaskCpf();
		maskCpf.enable();
		
		var maskCep = new Erp.MaskCep();
		maskCep.enable();
		
		var maskCnpj = new Erp.MaskCnpj();
		maskCnpj.enable();
		
		
});