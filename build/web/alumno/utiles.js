$("#rut").focusout(
        function()
        {
            var rut = this.value;
            $.ajax
            (
                {
                    type:'POST',
                    url:'../buscarDatosAlumno',
                    data:{rut:rut},
                    success:function(data){
                        //console.log(data);
                        $("#hdnIdAlumno").val(data.id_usuario);
                        $("#nombre").val(data.nombre_completo);
                        $("#email").val(data.email);
                        $("#hdnIdCarrera").val(data.id_carrera);
                        $("#carrera").val(data.carrera);
                        $("#telefono").val(data.telefono);
                        
                    }
                }
            );
        }
);
$("#ddlTipoBeneficio").change(
    function(){
        var tipoBeneficio = this.value;
        
        
        $.ajax(
                {
                    type:'POST',
                    url:'../listaBeneficio',
                    data:{tipoBeneficio:tipoBeneficio},
                    success:function(resp){
                            $("#ddlBeneficio").html(resp);
                    }
                }
        );
    }
);

$("#ddlBeneficio").change(function () {
    if (this.value === "8") {
        $("#divSegundoHermano").attr("hidden", false);
        $("#rutSegundoHermano").attr("hidden", false);
        $("#divFile").attr("hidden", false);
        $("#archivo").attr("hidden", false);
        $("#divAnioEgreso").attr("hidden", true);
                       
                        
        $("#rutSegundoHermano").attr("required", true);
        $("#rutTercerHermano").attr("required", false);
        $("#anioegreso").attr("required", false);
        $("#archivo").attr("required", true);
                        
    } else if (this.value === "10") {
        $("#divAnioEgreso").attr("hidden", false);
        $("#divFile").attr("hidden", false);
        $("#archivo").attr("hidden", false);
        $("#divSegundoHermano").attr("hidden", true);
        $("#divTercerHermano").attr("hidden", true);
        
                        
        $("#rutSegundoHermano").attr("required", false);
        $("#rutTercerHermano").attr("required", false);
        $("#anioegreso").attr("required", false);
        $("#archivo").attr("required", true);
                        
    } else if (this.value === "11") {
        $("#divFile").attr("hidden", false);
        $("#archivo").attr("hidden", false);
                        
        $("#rutSegundoHermano").attr("required", false);
        $("#rutTercerHermano").attr("required", false);
        $("#anioegreso").attr("required", false);
        $("#archivo").attr("required", true);
                        
    } else if (this.value === "9") {
        $("#divSegundoHermano").attr("hidden", false);
        $("#divTercerHermano").attr("hidden", false);
        
        $("#divFile").attr("hidden", false);
        $("#archivo").attr("hidden", false);
        $("#divAnioEgreso").attr("hidden", true);
        
                        
        $("#rutSegundoHermano").attr("required", true);
        $("#rutTercerHermano").attr("required", true);
        $("#anioegreso").attr("required", false);
        $("#archivo").attr("required", true);
                        
    } else if (this.value === "5") {
        $("#divFile").attr("hidden", false);
        $("#archivo").attr("hidden", false);
        $("#archivo").attr("required", true);
    } else if (this.value === "1") {
        $("#divFile").attr("hidden", false);
        $("#archivo").attr("hidden", false);
        $("#archivo").attr("required", true);
    } else if (this.value === "6") {
        $("#divFile").attr("hidden", false);
        $("#archivo").attr("hidden", false);
        $("#archivo").attr("required", true);
    } else if (this.value === "2") {
        $("#divFile").attr("hidden", false);
        $("#archivo").attr("hidden", false);
        $("#archivo").attr("required", true);
    } else if (this.value === "7") {
        $("#divFile").attr("hidden", false);
        $("#archivo").attr("hidden", false);
        $("#archivo").attr("required", true);
    } else if (this.value === "3") {
        $("#divFile").attr("hidden", false);
        $("#archivo").attr("hidden", false);
        $("#archivo").attr("required", true);
    } else if (this.value === "4") {
        $("#divFile").attr("hidden", false);
        $("#archivo").attr("hidden", false);
        $("#archivo").attr("required", true);
                        
    } else {
        $("#divAnioEgreso").attr("hidden", true);
        $("#anioegreso").attr("hidden", true);
        $("#divSegundoHermano").attr("hidden", true);
        $("#divTercerHermano").attr("hidden", true);
        $("#divFile").attr("hidden", true);
        $("#archivo").attr("hidden", true);
                        
        $("#rutSegundoHermano").attr("required", false);
        $("#rutTercerHermano").attr("required", false);
        $("#anioegreso").attr("required", false);
        $("#archivo").attr("required", false);
    }
});