$(function){
	var atual_page, next_page, prev_page;
	
	$('.next').click(function){
		atual_fs = $(this).parent();
		next_page = $(this).parent().next(); 
	})
	
	$('#form input[type=submit]').click(function){
		return false;
	}
});