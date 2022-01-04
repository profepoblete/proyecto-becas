$("#rut").focusout(
        function ()
        {
            var rut = this.value;
            $.ajax
                    (
                            {
                                type: 'POST',
                                url: '../buscarDatosAlumno',
                                data: {rut: rut},
                                success: function (data) {
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
        function () {
            var tipoBeneficio = this.value;


            $.ajax(
                    {
                        type: 'POST',
                        url: '../listaBeneficio',
                        data: {tipoBeneficio: tipoBeneficio},
                        success: function (resp) {
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
function editSolicitud(id_Solciitud)
{
    $("#modalSolicitud").modal('show');
    $.ajax(
            {
                type: 'POST',
                url: '../detalleSolicitud',
                data: {idSolicitud: id_Solciitud},
                success: function (data) {
                    //id_beneficio = data.id_beneficio;
                    $("#idSolicitud").val(data.id_formulario);
                    $("#hdnIdAlumno").val(data.id_alumno);
                    $("#hdnIdBeneficio").val(data.id_beneficio);
                    $("#beneficio").val(data.beneficio);
                    $("#hdnSemestre").val(data.semestre);
                    $("#hdnAnio").val(data.anio_ingreso);
                    //var id_usuario = $("#hdnIdAlumno").value;
                    $.ajax
                            (
                                    {
                                        type: 'POST',
                                        url: '../datosUsuarioPorIdJSON',
                                        data: {id_usuario: data.id_alumno},
                                        success: function (dataU) {
                                            //console.log(data);
                                            $("#hdnIdAlumno").val(dataU.id_usuario);
                                            $("#nombre").val(dataU.nombre_completo);
                                            $("#email").val(dataU.email);
                                            $("#hdnIdCarrera").val(dataU.id_carrera);
                                            $("#carrera").val(dataU.carrera);
                                            $("#telefono").val(dataU.telefono);

                                        }
                                    }
                            );
                    var id_beneficio = $("#hdnIdBeneficio").value;
                    $.ajax(
                            {
                                type: 'POST',
                                url: '../listaPorcentajesJSON',
                                data: {id_beneficio: data.id_beneficio},
                                success: function (dataB) {
                                    //$("#ddlBeneficio").html(resp);
                                    $('#ddlPorcentaje').append($('<option>').text("Seleccione").attr('value', 0));
                                    $.each(dataB, function (index, porcentaje) {
                                        $('#ddlPorcentaje').append($('<option>').text(porcentaje.descripcion).attr('value', porcentaje.id_porcentaje));
                                    });

                                }
                            }
                    );
                }
            }
    );


    $.ajax(
            {
                type: 'GET',
                url: '../listaEstadosJSON',
                success: function (data) {
                    //$("#ddlBeneficio").html(resp);
                    $.each(data, function (index, estado) {
                        $('#ddlEstado').append($('<option>').text(estado.estado).attr('value', estado.id_estado));
                    });

                }
            }
    );

}

$("#ddlPorcentaje").change(
        function () {
            var idPorcentaje = this.value;


            $.ajax(
                    {
                        type: 'POST',
                        url: '../datosPorcentajeJSON',
                        data: {id_porcentaje: idPorcentaje},
                        success: function (dataPorc) {
                            var idCarrera = $("#hdnIdCarrera").value;
                            var semestre = $("#hdnSemestre").value;
                            var anio = $("#hdnAnio").value;
                            $.ajax(
                                    {
                                        type: 'POST',
                                        url: '../datosArancelJSON',
                                        data: {anio: 2020, semestre: 1, id_carrera: 1},
                                        success: function (dataAra) {
                                            var monto = (parseInt(dataPorc.porcentaje) / 100) * parseInt(dataAra.valor_arancel);
                                            $("#monto").val(monto);
                                        }
                                    }
                            );
                        }
                    }
            );
        }
);
$(
        function ()
        {
            $("#btnGrabar").click(
                    function ()
                    {
                        var idsolicitud = $("#idSolicitud").attr('value');
                        var idestado = $("#ddlEstado option:selected").attr('value');
                        var estado = $("#ddlEstado option:selected").text();
                        var porcentaje = $("#ddlPorcentaje option:selected").attr('value');
                        var monto = $("#monto").val();
                        $.ajax(
                                {
                                    type: 'POST',
                                    url: '../updSolicitudFinanciamientoJSON',
                                    data: {idsolicitud: idsolicitud, idestado: idestado, estado: estado, porcentaje: porcentaje, monto: monto},
                                    success: function (resp) {
                                        if (resp.success === 1)
                                        {
                                            $("#divAlerts").html('<div class="alert alert-success" role="alert"><button class="close" type="button" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">x</span></button>' + resp.mensaje + '</div>');

                                        } else
                                        {
                                            $("#divAlerts").html('<div class="alert alert-danger" role="alert"><button class="close" type="button" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">x</span></button>' + resp.mensaje + '</div>');

                                        }
                                    }
                                }
                        );
                    }
            );
        }
);


/*
 function UpdSolicitud()
 {
 var idsolicitud = $("#idSolicitud").value;
 var idestado = $("#ddlEstado option:selected").value;
 var estado = $("#ddlEstado option:selected").text();
 var porcentaje = $("#ddlPorcentaje").value;
 var monto = $("#monto").value;
 $.ajax(
 {
 type: 'POST',
 url: '../updSolicitudFinanciamientoJSON',
 data: {idsolicitud: idsolicitud, idestado: idestado, estado: estado, porcentaje: porcentaje, monto: monto},
 success: function (resp) {
 if (resp.success === '1')
 {
 $("#divAlerts").html('<div class="alert alert-success" role="alert"><button class="close" type="button" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">×</span></button>' + resp.mensaje + '</div>');
 
 } else
 {
 $("#divAlerts").html('<div class="alert alert-danger" role="alert"><button class="close" type="button" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">×</span></button>' + resp.mensaje + '</div>');
 
 }
 }
 }
 );
 }
 * 
 */