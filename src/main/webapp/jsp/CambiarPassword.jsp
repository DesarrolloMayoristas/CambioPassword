<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>  
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<html>
	<head>
	
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<meta name="viewport" content="width=device-width, initial-scale=1.0,  minimum-scale=1.0">
		<title>CDO ROA AutoPartes</title>
		<link rel="icon" type="image/png" href="${pageContext.request.contextPath}/images/logo_cdo.png">	
		
					
		<script src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/bootstrap.min.css">
		<script  type="text/javascript" src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
		<script  type="text/javascript" src="js/sweetAlert.js"></script>						
		<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.13.0/css/all.min.css">
		<!--  CSS BOOTSTRAP  y JQUERY-->
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/bootstrap.css">
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/sweetAlert.css">
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/jquery-ui-1.10.3.custom.css">
				
		<!--  JS PERSONALIZADO Y JQUERY -->
		<script  type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
		<script  type="text/javascript" src="${pageContext.request.contextPath}/js/jsjquery.min.js"></script>
		 <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css">
		<!-- JS BOOTSTRAP -->
		<script  type="text/javascript" src="${pageContext.request.contextPath}/js/bootstrap.js"></script> 
		<script src="js/forte.js"></script>
				<script  type="text/javascript" src="js/sha1.js"></script>  
				<script  type="text/javascript" src="js/CambiarPassword.js"></script>  
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/EstilosGenerales.css">
		
		
	<SCRIPT type="text/javascript">
	
	/********** FUNCION PARA VALIDAR CONTRASEÑA **********/
	function validaPsw(psw) 
	{
		if(psw.value != null && psw.value != '')
			sha1(psw);
		else
		{
// 			alert('Ingresar una contraseña.');
			return false;
		}
	}

	/********** FUNCION PARA ENCRIPTAR CONTRASEÑA EN FORMATO SHA1 **********/
	function sha1(psw) 
	{	
		psw.value = rstr2hex(rstr_sha1(psw.value));
	}
	
	
	function changeTypeInput(id)
	{
			const password = document.querySelector('#'+id+'');
		 const type = password.getAttribute('type') === 'password' ? 'text' : 'password';
		 password.setAttribute('type', type);	
	}
	
	
	
	
	$(window, document, undefined).ready(function() {

		  $('input').blur(function() {
		    var $this = $(this);
		    if ($this.val())
		      $this.addClass('used');
		    else
		      $this.removeClass('used');
		  });

		  var $ripples = $('.ripples');

		  $ripples.on('click.Ripples', function(e) {

		    var $this = $(this);
		    var $offset = $this.parent().offset();
		    var $circle = $this.find('.ripplesCircle');

		    var x = e.pageX - $offset.left;
		    var y = e.pageY - $offset.top;

		    $circle.css({
		      top: y + 'px',
		      left: x + 'px'
		    });

		    $this.addClass('is-active');

		  });

		  $ripples.on('animationend webkitAnimationEnd mozAnimationEnd oanimationend MSAnimationEnd', function(e) {
		  	$(this).removeClass('is-active');
		  });

		});
	
	
	</SCRIPT>
	</head>
	
	<body style="background: #fafafa;" onload="mostrarOpciones()">
	<div id ="divMenuPrincipal" style="width: 100%; height: 100%">
		<div class="row" style="margin: 0; text-align: center;" >
				<div  style="margin: 0; " class=" col-12 col-sm-12 col-md-2 col-lg-3" ></div>
				<div  style="margin: 0; " class=" col-12 col-sm-12 col-md-8 col-lg-6" align="center">
					<div class="modalPassword" id="modalPassword1" >
						<div class="row" style="margin: 0; text-align: center;" >
							<div  style="margin: 0; text-align: center;padding-right: 0px;padding-left: 0px;" class=" col-12 col-sm-12 col-md-12 col-lg-12" >
								<div class="divRojo">
								<label class="ECC_lb_size_16 distancia">CAMBIAR CONTRASEÑA <i class="bi-lock-fill"></i></label>
								</div>
							</div>
						</div>
						<div class="row" style="margin: 0; text-align: center;     padding-top: 30px;" >
							<div  style="margin: 0;padding-right: 0px;padding-left: 0px; " align="left" class=" col-12 col-sm-12 col-md-12 col-lg-12" >
								<table style="width: 85%;font-weight: bolder; color: #17a2b8;font-size: 20px; margin-left: 40px;">
									<tr>
										<td>
											<label class="mayus"> <i class="bi-person-fill bi-lg"></i> ${cliente} - ${clave}</label>
										</td>
									</tr>
									<tr>
										<td>
											<label style="    text-align: justify;color: black;font-weight: normal;font-size: 16px;">Contraseña debe tener minimo 1 numero, 1 mayuscula, 1 minuscula, 8 caracteres.</label>
										</td>
									</tr>
								</table>
							</div>
						</div>
						<div class="row" style="margin: 0; text-align: center;     padding-top: 30px;" id="inicio1">
							<div  style="margin: 0;padding-right: 0px;padding-left: 0px; " align="left" class=" col-12 col-sm-12 col-md-12 col-lg-12" >
							<label class="subtitulos"> Contraseña para inicio de sesion</label>
							</div>
							<div  style="margin-bottom: 16px;padding-right: 0px;padding-left: 0px; "  align="center" class=" col-12 col-sm-12 col-md-6 col-lg-6" >
							  <div class="group" >
							  	<TABLE style="width: 100%">
							  		<tr>
								  		<td>
										  	<input type="hidden" id="passInicioDecode">
										    <input type="password" onchange="llenarCaja('passInicio');" onkeyup="validarPas('inicioNivel')" maxlength="15" id="passInicio">
										    <span class="highlight"  appnoautocomplete="" ></span><span class="bar"></span>
										    <label class="lbl">Contraseña</label>
										</td>
										<td>
										  	<i class="far fa-eye" onclick="changeTypeInput('passInicio')"></i>
										</td>
								    </tr>
							    </TABLE>
							  </div>
							  
							  <label style="margin-top: 8px;"  id="inicioNivel"></label>
							</div>
							<div  style="margin-bottom: 16px;padding-right: 0px;padding-left: 0px;"  align="center" class=" col-12 col-sm-12 col-md-6 col-lg-6" >
							  <div class="group">
								  <TABLE style="width: 100%">
							  		<tr>
								  		<td>
										  	<input type="hidden" id="passInicioConfirmaDecode">
										    <input type="password" onchange="" id="passInicioConfirma" maxlength="15"> 
										    <span class="highlight" autocomplete = "off"></span><span  class="bar"></span>
										    <label class="lbl">Confirme contraseña</label>
							    		</td>
										<td>
										  	<i class="far fa-eye" onclick="changeTypeInput('passInicioConfirma')"></i>
										</td>
								    </tr>
							    </TABLE>
							  </div>
							</div>		
						</div>
						<div class="row" style="margin: 0; text-align: center;     padding-top: 30px;" >
							<div  style="margin: 0;padding-right: 0px;padding-left: 0px; " align="left" class=" col-12 col-sm-12 col-md-12 col-lg-12" id="pass1">
							<label class="subtitulos"> Contraseña para ver Estado de Cuenta</label>
							</div>
							<div  style="margin-bottom: 16px;padding-right: 0px;padding-left: 0px; "  align="center" class=" col-12 col-sm-12 col-md-6 col-lg-6" id="pass2">
							  <div class="group">
							  <TABLE style="width: 100%">
							  		<tr>
								  		<td>
							  				<input type="hidden" id="passEdoDecode">
										    <input type="password" onchange="llenarCaja('passEdo');"onkeyup="validarPasEdo('inicioEdoNivel')" id="passEdo" maxlength="15"><span class="highlight" autocomplete = "off"></span><span class="bar"></span>
										    <label class="lbl">Contraseña</label>
							    		</td>
										<td>
										  	<i class="far fa-eye" onclick="changeTypeInput('passEdo')"></i>
										</td>
								    </tr>
							    </TABLE>
							  </div>
							  <label style="margin-top: 8px;"  id="inicioEdoNivel"></label>
							</div>
							<div  style="margin-bottom: 16px;padding-right: 0px;padding-left: 0px;"  align="center" class=" col-12 col-sm-12 col-md-6 col-lg-6" id="pass3">
							  <div class="group">
							  <TABLE style="width: 100%">
							  		<tr>
								  		<td>
										    <input type="password" onchange="" id="passEdoConfirma" maxlength="15" ><span class="highlight" autocomplete = "off" ></span><span class="bar"></span>
										    <label class="lbl">Confirme contraseña</label>
							    		</td>
										<td>
										  	<i class="far fa-eye" onclick="changeTypeInput('passEdoConfirma')"></i>
										</td>
								    </tr>
							    </TABLE>
							  </div>
							</div>	
							<div  style="margin: 0; " class=" col-12 col-sm-12 col-md-12 col-lg-12" >
								<BUTTON type="button" id="btn_cambiar" class="btn btn-info button" style="color: white;  margin-bottom: 25px;    margin-top: 25px;display: none" onclick="actualizarPasswords()"> Continuar <i class="bi-check-circle-fill"></i></BUTTON>
							</div>	
						</div>
					</div>
				</div>
				<div  style="margin: 0; " class=" col-12 col-sm-12 col-md-2 col-lg-3" ></div>
				<br>
		</div>
	</div>
	<%@include file="ALertaOpciones.html"%>
	</body>
	</html>