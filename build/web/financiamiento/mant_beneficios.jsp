<%@taglib prefix="j" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="es">
    <head>
        <meta charset="UTF-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
        <meta name="description" content="" />
        <meta name="author" content="" />
        <title>Becas - Financiamiento</title>
        <link href="../admin/css/styles.css" rel="stylesheet" />
        <link href="https://cdn.datatables.net/1.10.20/css/dataTables.bootstrap4.min.css" rel="stylesheet" crossorigin="anonymous" />
        <link rel="icon" type="image/x-icon" href="../img/favicon.png" />
        <script data-search-pseudo-elements defer src="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.11.2/js/all.min.js" crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/feather-icons/4.24.1/feather.min.js" crossorigin="anonymous"></script>
    </head>
    <body class="nav-fixed">
        <%@include file="menu.jsp"%>
        <div id="layoutSidenav">
            <%@include file="sidenav.jsp"  %>
            <jsp:include page="/listaTipoBeneficio" flush="true"/>
            <jsp:include page="/listaBeneficiosMant" flush="true"/>
            <div id="layoutSidenav_content">
                <main>
                    <div class="page-header pb-10 page-header-dark bg-gradient-primary-to-secondary">
                        <div class="container-fluid">
                            <div class="page-header-content">
                                <h1 class="page-header-title">
                                    <div class="page-header-icon"><i data-feather="activity"></i></div>
                                    <span>Mantenedor de Beneficios</span>
                                </h1>
                                <div class="page-header-subtitle"></div>
                            </div>
                        </div>
                    </div>
                    <div class="container-fluid mt-n10">
                        <div class="row">
                            <div class="col-12">
                            <div class="card card-header-actions mx-auto">
                                <div class="card-header">
                                    Lista de Beneficios
                                    <div>
                                        
                                        <button class="btn btn-blue btn-icon" id="btnNuevo" data-toggle="tooltip" data-placement="top" data-original-title="Nuevo Beneficio" onclick="nuevoBeneficio();">
                                            <i data-feather="plus"></i>
                                        </button>
                                    </div>
                                </div>
                                <div class="card-body">
                                    <div id="divAlerts">
                                        
                                    </div>
                                    <div class="datatable table-responsive">
                                        <table class="table table-bordered table-hover" id="dataTable" width="100%" cellspacing="0">
                                            <thead>
                                                <tr>
                                                    <th>Nombre Beneficio</th>
                                                    <th>Tipo Beneficio</th>
                                                    <th>Accion</th>
                                                </tr>
                                            </thead>
                                            <tfoot>
                                                <tr>
                                                    <th>Nombre Beneficio</th>
                                                    <th>Tipo Beneficio</th>
                                                    <th>Accion</th>
                                                </tr>
                                            </tfoot>
                                            <tbody>
                                                <j:forEach items="${listaBeneficios}" var="b">
                                                    <tr id="${b.getId_beneficio()}">
                                                        <td>${b.getNombre()}</td>
                                                        <td>${b.getTipo_beneficio()}</td>
                                                        <td>
                                                            <!-- mant porcentajes -->
                                                            <a class="btn btn-datatable btn-icon btn-transparent-dark mr-2" href="../mant_porcentajes.jsp?id=${b.getId_beneficio()}" data-toggle="tooltip" data-placement="top" data-original-title="Mantenedor Porcentajes">
                                                                <i data-feather="percent"></i>
                                                            </a>
                                                            <!-- Editar -->
                                                            <button class="btn btn-datatable btn-icon btn-transparent-dark mr-2" onclick="editBeneficio(${b.getId_beneficio()});"  data-toggle="tooltip" data-placement="top" data-original-title="Editar Beneficio">
                                                                <i data-feather="edit"></i>
                                                            </button>
                                                            <!-- Deshabilitar -->
                                                            <button class="btn btn-datatable btn-icon btn-transparent-dark mr-2" onclick="disableBeneficio(${b.getId_beneficio()});"  data-toggle="tooltip" data-placement="top" data-original-title="Deshabilitar Beneficio">
                                                                <i data-feather="x-square"></i>
                                                            </button>
                                                        </td>
                                                    </tr>
                                                </j:forEach>
                                            </tbody>
                                        </table>
                                    </div>
                                </div>
                            </div>
                            </div>
                        </div>
                    </div>
                </main>
                <footer class="footer mt-auto footer-light">
                    <div class="container-fluid">

                    </div>
                </footer>
                <!-- Modal Nuevo Beneficio -->
                <div id="modalNuevoBeneficio" class="modal fade" id="exampleModalScrollable" tabindex="-1" role="dialog" aria-labelledby="exampleModalScrollableTitle" aria-hidden="true">
                    <div class="modal-dialog modal-dialog-scrollable" role="document">
                        <div class="modal-content">
                            <div class="modal-header">
                                <h5 class="modal-title" id="exampleModalScrollableTitle">Nuevo Beneficio</h5>
                                <button class="close" type="button" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">×</span></button>
                            </div>
                            <div class="modal-body">
                                <form>
                                    <div class="form-group">
                                        <label for="ddlTipoBeneficio">Tipo Beneficio:</label>
                                        <select class="form-control" id="ddlTipoBeneficio" name="ddlTipoBeneficio">
                                            <option value="0">Seleccione Tipo Beneficio</option>
                                            <j:forEach var="ts" items="${listaTipoBeneficio}">
                                                <option value="${ts.getId_tipo()}">${ts.getNombre()}</option>
                                            </j:forEach>
                                        </select>
                                    </div>
                                    <div class="form-group">
                                        <label for="nombre">Nombre Beneficio:</label>
                                        <input class="form-control" id="nombre" name="nombre" type="text" placeholder="Nombre"/>
                                    </div>
                                    
                                </form>
                            </div>
                            <div class="modal-footer">
                                <button class="btn btn-secondary" type="button" data-dismiss="modal">Salir</button>
                                <button class="btn btn-primary" type="button" id="btnGrabar">Grabar</button></div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <script src="https://code.jquery.com/jquery-3.4.1.min.js" crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.bundle.min.js" crossorigin="anonymous"></script>
        <script src="../admin/js/scripts.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.8.0/Chart.min.js" crossorigin="anonymous"></script>
        <script src="../admin/assets/demo/chart-area-demo.js"></script>
        <script src="../admin/assets/demo/chart-bar-demo.js"></script>
        <script src="https://cdn.datatables.net/1.10.20/js/jquery.dataTables.min.js" crossorigin="anonymous"></script>
        <script src="https://cdn.datatables.net/1.10.20/js/dataTables.bootstrap4.min.js" crossorigin="anonymous"></script>
        <script src="../admin/assets/demo/datatables-demo.js"></script>
        <script src="util_mant_beneficios.js"></script>
        
    </body>
</html>
