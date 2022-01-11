/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
function nuevoBeneficio()
{
      $("#modalNuevoBeneficio").modal('show');
    
}
$(
        function ()
        {
            $("#btnGrabar").click(
                    function ()
                    {
                        var nombreBeneficio = $("#nombre").val();
                        var idTipoBeneficio = $("#ddlTipoBeneficio option:selected").attr('value');
                        
                        $.ajax(
                                {
                                    type: 'POST',
                                    url: '../addBeneficioJSON',
                                    data: {nombreBeneficio: nombreBeneficio, idTipoBeneficio: idTipoBeneficio},
                                    success: function (resp) {
                                        if (resp.success === 1)
                                        {
                                            $("#divAlerts").html('<div class="alert alert-success" role="alert"><button class="close" type="button" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">x</span></button>' + resp.mensaje + '</div>');

                                        } else
                                        {
                                            $("#divAlerts").html('<div class="alert alert-danger" role="alert"><button class="close" type="button" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">x</span></button>' + resp.mensaje + '</div>');

                                        }
                                        $("#modalNuevoBeneficio").modal('hide');
                                    }
                                }
                        );
                    }
            );
        }
);

