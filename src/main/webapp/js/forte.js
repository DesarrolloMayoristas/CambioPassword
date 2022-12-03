function nnnn(valor,id) 
{
     var strongRegex = new RegExp("^(?=.{8,})(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9]).*$", "g");
     var mediumRegex = new RegExp("^(?=.{7,})(((?=.*[A-Z])(?=.*[a-z]))|((?=.*[A-Z])(?=.*[0-9]))|((?=.*[a-z])(?=.*[0-9]))).*$", "g");
     var enoughRegex = new RegExp("(?=.{6,}).*", "g");
     var aa = new RegExp("^(?=.*\\W)","g");
     if (false == enoughRegex.test(valor)) {
    	 $("#"+id+"").text("\xa1No cumple con el estandar!");
    	 return false;
     }
     else if(aa.test(valor))
     {
    	 $("#"+id+"").text("\xa1No debe tener caracteres especiales o simbolos!");
    	 return false;
     }
     else if (strongRegex.test(valor)) {
    	 $("#"+id+"").text("\xa1CORRECTA!");
    	 return true;
     } else if (mediumRegex.test(valor)) {
    	 $("#"+id+"").text("\xa1No cumple con el estandar!");
    	 return false;
     } else {
    	 $("#"+id+"").text("\xa1No cumple con el estandar!");
          return false;
     }
}