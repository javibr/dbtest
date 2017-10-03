$(document).ready(function() {
	$('#reg-submit').click(function(event){
		event.preventDefault();

		var error = false;


		if (error== false && (!$('#firstName').val())) {
			$("#vacio").text("First name field empty :-(");
			error = true;
		}
	

		if (error == false && (!$('#lastName').val())) {
			$("#vacio").text("Campo de lastname vacío :-(");
			error = true;
		}

		if (error == false && (!$('#email').val())) {
			$("#vacio").text("email can not be empty :-(");
			error = true;
		}
		

		

		if (!error) {
			$('#reg-form').submit();
		}

		


	});
	
});

