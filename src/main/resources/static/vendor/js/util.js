var Financeiro = Financeiro || {};

Financeiro.DialogoDeRemocao = (function() {

	function DialogoDeRemocao() {
		this.modal = $('#modal-remover-entidade');
		this.botaoRemover = $('.js-remover-entidade-btn');
		this.alertInfo = $('#info');
	}

	DialogoDeRemocao.prototype.iniciar = function() {
		this.botaoRemover.on('click', onModalShow.bind(this));
		this.alertInfo.on('dblclick', onDoubleClickInfo);
	};

	function onModalShow(evento) {
		evento.preventDefault();
		var botao = $(evento.currentTarget);
		var idPessoa = botao.data('id');
		var nome = botao.data('nome');

		var form = this.modal.find('form');
		var action = form.data('url-remover');
		if (!action.endsWith('/')) {
			action += '/';
		}

		form.attr('action', action + idPessoa);

		this.modal.find('.modal-content span').html(
				'Tem certeza que deseja remover <strong> ' + nome
						+ '</strong> ?');
	}

	function onDoubleClickInfo(evento) {
		$(this).remove();
	}

	return DialogoDeRemocao;

}());

$(function() {

	var removerEntidade = new Financeiro.DialogoDeRemocao();
	removerEntidade.iniciar();
});

var inputsCEP = $('#logradouro, #bairro, #localidade, #uf, #ibge');
var inputsRUA = $('#cep, #bairro, #ibge');
var validacep = /^[0-9]{8}$/;

function limpa_formulário_cep(alerta) {
	if (alerta !== undefined) {
		alert(alerta);
	}

	inputsCEP.val('');
}

function get(url) {

	$.get(url, function(data) {

		if (!("erro" in data)) {

			if (Object.prototype.toString.call(data) === '[object Array]') {
				var data = data[0];
			}

			$.each(data,
					function(nome, info) {
						$('#' + nome)
								.val(
										nome === 'cep' ? info
												.replace(/\D/g, '') : info)
								.attr(
										'info',
										nome === 'cep' ? info
												.replace(/\D/g, '') : info);
					});

		} else {
			limpa_formulário_cep("CEP não encontrado.");
		}

	});
}

// Digitando RUA/CIDADE/UF
$('#logradouro, #localidade, #uf').on(
		'blur',
		function(e) {

			if ($('#logradouro').val() !== ''
					&& $('#logradouro').val() !== $('#logradouro').attr('info')
					&& $('#localidade').val() !== ''
					&& $('#localidade').val() !== $('#localidade').attr('info')
					&& $('#uf').val() !== ''
					&& $('#uf').val() !== $('#uf').attr('info')) {

				inputsRUA.val('...');
				get('https://viacep.com.br/ws/' + $('#uf').val() + '/'
						+ $('#localidade').val() + '/' + $('#logradouro').val()
						+ '/json/');
			}

		});

// Digitando CEP
$('#cep').on(
		'blur',
		function(e) {

			var cep = $('#cep').val().replace(/\D/g, '');

			if (cep !== "" && validacep.test(cep)) {

				inputsCEP.val('...');
				get('https://viacep.com.br/ws/' + cep + '/json/');

			} else {
				limpa_formulário_cep(cep == "" ? undefined
						: "Formato de CEP inválido.");
			}
		});

window.onload = function(){
	preencheInputsConta();
};

function preencheInputsConta(){
var url_string = document.URL; 
var url = new URL(url_string);
var ano = url.searchParams.get("ano");
var mes = url.searchParams.get("mes");
document.getElementById('meshidden').value = mes;
document.getElementById('anohidden').value = ano;
};