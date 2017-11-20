// JS do combo-box "select"
$(document).ready(function() {
    $('select').material_select();
});

// JS do menu lateral
$('.button-collapse').sideNav({
      menuWidth: 240, //Tamanho do menu, por default é 240px
      edge: 'left', // Origem horizontal (esquerda)
      closeOnClick: true // Fecha o side-nav ao clicar
    }
  );

$(function() {
  $('input.autocomplete').autocomplete({
    data: {
      "Elton": null,
      "Fábio": null,
      "Ricardo": 'http://placehold.it/250x250',
    }
  });
});

(function($) {
	$(function() {

		$('.button-collapse').sideNav();

	}); // end of document ready
})(jQuery); // end of jQuery name space

$(document).ready(function() {
	$('.tooltipped').tooltip({
		delay : 50
	});
});

$(document).ready(function() {
	$('select').material_select();
});

$(document).ready(function() {
	Materialize.updateTextFields();
});

var diaSemana = [ 'Domingo', 'Segunda-Feira', 'Terca-Feira', 'Quarta-Feira',
		'Quinta-Feira', 'Sexta-Feira', 'Sabado' ];
var mesAno = [ 'Janeiro', 'Fevereiro', 'Marco', 'Abril', 'Maio', 'Junho',
		'Julho', 'Agosto', 'Setembro', 'Outubro', 'Novembro', 'Dezembro' ];
var data = new Date();
var hoje = diaSemana[data.getDay()] + ', ' + mesAno[data.getMonth()] + ' de '
		+ data.getFullYear();
$("#dataPesquisa").attr("value", hoje);
$(".datepicker") .pickadate( {
					selectMonths: true,
			        selectYears: 15,
			        labelMonthNext: 'Proximo Mês',
			        labelMonthPrev: 'Mês Anterior',
			        labelMonthSelect: 'Selecionar Mês',
			        labelYearSelect: 'Selecionar Ano',
			        monthsFull: [ 'Janeiro', 'Fevereiro', 'Março', 'Abril', 'Maio', 'Junho', 'Julho', 'Agosto', 'Setembro', 'Outubro', 'Novembro', 'Dezembro' ],
			        monthsShort: [ 'Jan', 'Fev', 'Mar', 'Abr', 'Maio', 'Jun', 'Jul', 'Ago', 'Set', 'Out', 'Nov', 'Dez' ],
			        weekdaysFull: [ 'Domingo', 'Segunda', 'Terça', 'Quarta', 'Quinta', 'Sexta', 'Sábado' ],
			        weekdaysShort: [ 'Dom', 'Seg', 'Ter', 'Qua', 'Qui', 'Sex', 'Sáb' ],
			        weekdaysLetter: [ 'D', 'S', 'T', 'Q', 'Q', 'S', 'S' ],
			        today: 'Hoje',
			        clear: 'Limpar',
			        close: 'Fechar',
			        format: 'dd/mm/yyyy'
				});

$("#dataPesquisa").click(function(event) {
	event.stopPropagation();
	$(".datepicker").first().pickadate("picker").open();
});

$(document).ready(function() {
	$('.modal').modal();
});
