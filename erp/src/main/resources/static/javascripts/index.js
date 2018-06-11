var $ = jQuery;
function selecionouEstado(id, url) {
	$.ajax({
		url : url,
		method : 'POST',
		contentType : 'application/json',
		data : JSON.stringify({
			id : id
		}),
		error : erroEstado,
		success : selecionarCidade
	});

	function selecionarCidade(cidades) {
		var comboCidade = $("#cidade");
		comboCidade
				.html('<option value =' + 0 + '>Selecione a Cidade</option>');

		for ( var i in cidades) {
			comboCidade.append('<option value =' + cidades[i].id + '>'
					+ cidades[i].nome + '</option>');
		}

	}
	function erroEstado() {
		console.log('Erro em carregar as cidades!')
	}

}
function selecionouIcms(id, url) {
	$.ajax({
		url : url,
		method : 'POST',
		contentType : 'application/json',
		data : JSON.stringify({
			id : id
		}),
		error : erroIcms,
		success : attListaCfop
	});

	function attListaCfop(cfops) {
		var comboCfop = $("#cfop");
		var icmsDesc = $("#icmsDesc");
		var cfopDesc = $("#cfopDesc");

		cfopDesc.val("");
		comboCfop.html('<option value =' + 0 + '>Selecione o CFOP</option>');
		console.log("Deu certo");
		console.log(cfops);
		icmsDesc.val(cfops[0].icmsDescricao);
		for ( var i in cfops) {
			comboCfop.append('<option value =' + cfops[i].id + '>'
					+ cfops[i].codigo + '</option>');
			console.log(cfops[i].codigo);
		}

	}
	function erroIcms() {
		console.log('Erro em carregar a descrição do ICMS!')
	}

}

function selecionouCfop(id, url) {
	$.ajax({
		url : url,
		method : 'POST',
		contentType : 'application/json',
		data : JSON.stringify({
			id : id
		}),
		error : erroCfop,
		success : alterarDescricao
	});

	function alterarDescricao(cfop) {
		var cfopDesc = $("#cfopDesc");
		cfopDesc.val(cfop.descricao);

	}
	function erroCfop() {
		console.log('Erro em carregar a descrição do CFOP!')
	}

}

function selecionouNcm(id, url) {
	$.ajax({
		url : url,
		method : 'POST',
		contentType : 'application/json',
		data : JSON.stringify({
			id : id
		}),
		error : erroNcm,
		success : alterarDescricao
	});

	function alterarDescricao(ncm) {
		var ncmDesc = $("#ncmDesc");

		ncmDesc.val(ncm.descricao);

	}
	function erroNcm() {
		console.log('Erro em carregar a descrição do CFOP!')
	}

}

function atualizaCfop(id, url) {
	$.ajax({
		url : url,
		method : 'POST',
		contentType : 'application/json',
		data : JSON.stringify({
			id : id
		}),
		error : erroCfop,
		success : attListaCfop
	});

	function attListaCfop(cfops) {
		var comboCfop = $("#cfop");
		comboCfop.html('<option value =' + 0 + '>Selecione o CFOP</option>');
		console.log("Deu certo");

		for ( var i in cfops) {
			comboCfop.append('<option value =' + cfops[i].id + '>'
					+ cfops[i].codigo + '</option>');
			console.log(cfops[i].codigo);
		}

	}
	function erroCfop() {
		console.log('Erro em carregar os CFOPS!')
	}

}

$('.form').find('input, textarea').on('keyup blur focus', function(e) {

	var $this = $(this), label = $this.prev('label');

	if (e.type === 'keyup') {
		if ($this.val() === '') {
			label.removeClass('active highlight');
		} else {
			label.addClass('active highlight');
		}
	} else if (e.type === 'blur') {
		if ($this.val() === '') {
			label.removeClass('active highlight');
		} else {
			label.removeClass('highlight');
		}
	} else if (e.type === 'focus') {

		if ($this.val() === '') {
			label.removeClass('highlight');
		} else if ($this.val() !== '') {
			label.addClass('highlight');
		}
	}

});

$('.tab a').on('click', function(e) {

	e.preventDefault();

	$(this).parent().addClass('active');
	$(this).parent().siblings().removeClass('active');

	target = $(this).attr('href');

	$('.tab-content > div').not(target).hide();

	$(target).fadeIn(600);

});