function codificar() 
{
	select = 	$('input:radio[name=select]:checked').val();
	if (select == "1") 
	{
		if($("#passInicio").val() != "")
		{
			var control = document.getElementById('passInicio');
	    	 validaPsw(control)
		}
		if($("#passInicioConfirma").val() != "")
		{
			var control = document.getElementById('passInicioConfirma');
	    	 validaPsw(control)
		}
	}
	if (select == "2") 
	{
		if($("#passEdo").val() != "")
		{
			var control = document.getElementById('passEdo');
	    	 validaPsw(control)
		}
		if($("#passEdoConfirma").val() != "")
		{
			var control = document.getElementById('passEdoConfirma');
	    	 validaPsw(control)
		}
	}
	if (select == "3")
	{
		if($("#passInicio").val() != "")
		{
			var control = document.getElementById('passInicio');
	    	 validaPsw(control)
		}
		if($("#passInicioConfirma").val() != "")
		{
			var control = document.getElementById('passInicioConfirma');
	    	 validaPsw(control)
		}
		if($("#passEdo").val() != "")
		{
			var control = document.getElementById('passEdo');
	    	 validaPsw(control)
		}
		if($("#passEdoConfirma").val() != "")
		{
			var control = document.getElementById('passEdoConfirma');
	    	 validaPsw(control)
		}
	}
}

function codificarUnico(valor)
{
	var control = document.getElementById(''+valor+'');
	 validaPsw(control)
}

function mostrarOpciones() 
{
	$("#alertaOpciones").modal("toggle");
	$('#alertaOpciones').on('shown.bs.modal', function (e) 
			{
				 document.getElementById("continuarOpciones").focus();
			})
}

function limpiarValidacionLbl(id) 
{
	console.log(id)
	$("#"+id).text("")
}

function validarPas(valor) 
{
	nnnn($("#passInicio").val(),valor);
}
function validarPasEdo(valor) 
{
	nnnn($("#passEdo").val(),valor);
}
function validarQueMostrar()
{
	
select = 	$('input:radio[name=select]:checked').val();
	if (select == "1") 
	{
		$("#btn_cambiar").show();
		$("#alertaOpciones").modal("hide");
		$("#pass1").hide()
		$("#pass2").hide()
		$("#pass3").hide()
		document.getElementById("passInicio").focus();
	}
	else if (select == "2") 
	{
		document.getElementById("passEdo").focus();
		$("#btn_cambiar").show();
		$("#alertaOpciones").modal("hide");
		$("#inicio1").hide()
	}
	else if (select == "3") 
	{
		document.getElementById("passInicio").focus();
		$("#btn_cambiar").show();
		$("#alertaOpciones").modal("hide");
	}
	else
	{
		alertSuccess("\xa1ATENCION!", "Debe seleccionar una opcion",'')
	}
	
}

function actualizarPasswords() 
{
	ocultarEtiquetas();
	var passInicio = $("#passInicio").val();
	var passInicioConfirma = $("#passInicioConfirma").val();
	var passInicioDecode = $("#passInicioDecode").val();
	var passEdo = $("#passEdo").val()
	var passEdoConfirma = 	$("#passEdoConfirma").val()
	var passEdoDecode = $("#passEdoDecode").val()
	select = 	$('input:radio[name=select]:checked').val();
	if (select == "3")  
	{
		if (passInicio != '' && passInicioConfirma != '' && passEdo != '' && passEdoConfirma != '') 
		{
			if (validarPassword(passInicio,passInicioConfirma)) 
			{
				if (validarPassword(passEdo,passEdoConfirma)) 
				{
					if (nnnn(passInicioDecode)) 
					{
						if (nnnn(passEdoDecode)) 
						{
							actualizarPassAjax(passInicio,passInicioDecode,passEdo,passEdoDecode,select);
						}
						else
						{
							$("#passEdoConfirma").val(passEdoDecode);
							$("#passEdo").val(passEdoDecode);
							document.getElementById("passEdo").focus();
							alertSuccess('\xa1ATENCION!',"<label style = '    text-align: left;'>Las constrase\u00f1a de estado de cuenta debe tener minimo:<br>- 1 Numero.<br>- 1 mayuscula.<br>- 1 minuscula. <br>- 8 caracteres.<label>")
						}
					}
					else
					{
						$("#passInicioConfirma").val(passInicioDecode);
						$("#passInicio").val(passInicioDecode);
						document.getElementById("passInicio").focus();
						alertSuccess('\xa1ATENCION!',"<label style = '    text-align: left;'>Las constrase\u00f1a de inicio de sesion debe tener minimo:<br>- 1 Numero.<br>- 1 mayuscula.<br>- 1 minuscula. <br>- 8 caracteres.<label>")
					}
				}
				else
				{
					limpiarValidacionLbl('inicioEdoNivel')
					document.getElementById("passEdo").focus();
					alertSuccess('\xa1ATENCION!',"Las constrase\u00f1as para ver estado de cuenta no coinciden")
					limpiarCampos("passEdo","passEdoConfirma","passEdoDecode","1");
					
				}
			}
			else
			{
				limpiarValidacionLbl('inicioNivel')
				document.getElementById("passInicio").focus();
				alertSuccess('\xa1ATENCION!',"Las constrase\u00f1as de inicio de sesion no coinciden")
				limpiarCampos("passInicio","passInicioConfirma","passInicioDecode","2");
			}
		}
		else
		{
			alertSuccess('\xa1ATENCION!','No puede dejar campos vac\u00edos')
		}
	}
	else if (select == "2")
	{
		if (passEdo != '' && passEdoConfirma != '') 
		{
			if (validarPassword(passEdo,passEdoConfirma)) 
			{
				if (validarPassword(passEdo,passEdoConfirma)) 
				{
					if (nnnn(passEdoDecode)) 
					{
						actualizarPassAjax(passInicio,passInicioDecode,passEdo,passEdoDecode,select);
//						alert("corretecto")
					}
					else
					{
						$("#passEdoConfirma").val(passEdoDecode);
						$("#passEdo").val(passEdoDecode);
						document.getElementById("passEdo").focus();
						alertSuccess('\xa1ATENCION!',"<label style = '    text-align: left;'>Las constrase\u00f1a de estado de cuenta debe tener minimo:<br>- 1 Numero.<br>- 1 mayuscula.<br>- 1 minuscula. <br>- 8 caracteres.<label>")
					}
				}
			}
			else
			{
				
				document.getElementById("passEdo").focus();
				
				limpiarValidacionLbl('inicioEdoNivel')
				alertSuccess('\xa1ATENCION!',"Las constrase\u00f1as para ver estado de cuenta no coinciden")
				limpiarCampos("passEdo","passEdoConfirma","passEdoDecode","1");
				
			}
		}
		else
		{
			alertSuccess('\xa1ATENCION!','No puede dejar campos vac\u00edos')
		}
	}
	else if (select == "1")
	{
		if (passInicio != '' && passInicioConfirma != '' ) 
		{
			if (validarPassword(passInicio,passInicioConfirma)) 
			{
				if (nnnn(passInicioDecode)) 
				{
					actualizarPassAjax(passInicio,passInicioDecode,passEdo,passEdoDecode,select);
//					console.log("correcto")
				}
				else
				{
					$("#passInicioConfirma").val(passInicioDecode);
					$("#passInicio").val(passInicioDecode);
					document.getElementById("passInicio").focus();
					alertSuccess('\xa1ATENCION!',"<label style = '    text-align: left;'>Las constrase\u00f1a de inicio de sesion debe tener minimo:<br>- 1 Numero.<br>- 1 mayuscula.<br>- 1 minuscula.<label>")
				}
			}
			else
			{
				document.getElementById("passInicio").focus();
				limpiarValidacionLbl('inicioNivel')
				alertSuccess('\xa1ATENCION!',"Las constrase\u00f1as de inicio de sesion no coinciden")
				limpiarCampos("passInicio","passInicioConfirma","passInicioDecode","2");
			}
		}
		else
		{
			alertSuccess('\xa1ATENCION!','No puede dejar campos vac\u00edos')
		}
	}
	else
	{
		alertSuccess("\xa1ATENCION!", "Debe seleccionar una opcion para el cambio de contrase\u00f1a",'')
	}
}



function actualizarPassAjax(passInicio,passInicioDecode,passEdo,passEdoDecode,opcion) 
{
	codificarUnico('passInicio')
	codificarUnico('passEdo')
	$.ajax({
	    url :'Password', 
	    type : 'POST',
	    data : "operacion=ActualizarPasswrod&passInicio="+$("#passInicio").val()+"&passInicioDecode="+passInicioDecode+"&passEdo="+$("#passEdo").val()+"&passEdoDecode="+passEdoDecode+"&opcion="+opcion, 
	    dataType : 'text',
	    success : function(respuesta)
	    {
	    			
	    			if (respuesta.includes('true')) 
	    			{
	    				$("#modalPassword1").hide();
	    				Swal.fire({
	  					  title:"\xa1CORRECTO!",
	  					  text:"Las constrase\u00f1as se han cambiado",
	  					  icon:'success',
	  					  confirmButtonColor: '#28a745',
	  					  confirmButtonText: '<i class="bi-check-circle-fill"></i>'
	  					})
	  					
					}
	    			else
    				{
	    				$("#passInicio").val(passInicioDecode)
	    				$("#passEdo").val(passEdoDecode)
	    				Swal.fire({
		  					  title:"\xa1ERROR!",
		  					icon: 'error',
		  					  text:"Al actualizar constrase\u00f1as",
		  						  confirmButtonColor: '#d33',
		  						  confirmButtonText: '<i class="bi-x-lg"></i>'
		  					})
    				}
		},
		error : function(xhr, status, error)
		{
			$("#passInicio").val(passInicioDecode)
			$("#passEdo").val(passEdoDecode)
			document.getElementById('cargando').style.display = 'none';
			if (xhr.status === 200)
			{
				alert('Tu sesion actual ha expirado, para continuar vuelve a iniciar sesion.')
				window.location.href='/BxAMayorista/';
			}
		}
	});
}

function alertSuccess(title,text,caja) 
{
	Swal.fire({
		  title:title,
		  html:text,
		  icon:'warning',
		  confirmButtonColor: '#d33',
		  confirmButtonText: '<i class="bi-x-lg"></i>'
		})
			 
}

function ocultarEtiquetas() 
{
	$("#div_Warning").hide()
	$("#div_Successs").hide()
}

function limpiarCampos(pass1,pass2,pass3,s)
{
	$("#"+pass1).val("")
	$("#"+pass2).val("")
	$("#"+pass3).val("")
}

function validarPassword(pass1,pass2) 
{
	if (pass1 === pass2) 
	{
		return true;
	}
	return false;
}

function llenarCaja(id)
{
	$("#"+id+"Decode").val($("#"+id).val());
}