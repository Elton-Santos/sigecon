(function($){
  $(function(){

    $('.button-collapse').sideNav();

  }); // end of document ready
})(jQuery); // end of jQuery name space

$(document).ready(function() {
    $('select').material_select();
  });

$(document).ready(function() {
    Materialize.updateTextFields();
  });


$('.datepicker').pickadate({
    selectMonths: true, // Creates a dropdown to control month
    selectYears: 15, // Creates a dropdown of 15 years to control year,
    format: 'yyyy-mm-dd',
    today: 'Today',
    clear: 'Clear',
    close: 'Ok',
    closeOnSelect: false // Close upon selecting a date,
    
  });

$(document).ready(function(){
	$('.modal').modal();
});



