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
            <jsp:include page="/listaTipoSolicitud" flush="true"/>
            <jsp:include page="/listaTipoBeneficio" flush="true"/>
            <jsp:include page="/listaSolicitudesFinanzas" flush="true"/>
            <div id="layoutSidenav_content">
                <main>
                    <div class="page-header pb-10 page-header-dark bg-gradient-primary-to-secondary">
                        <div class="container-fluid">
                            <div class="page-header-content">
                                <h1 class="page-header-title">
                                    <div class="page-header-icon"><i data-feather="activity"></i></div>
                                    <span>Mantenedor de porcentajes</span>
                                </h1>
                                <div class="page-header-subtitle"></div>
                            </div>
                        </div>
                    </div>
                    <div class="container-fluid mt-n10">
                        <div class="row">
                            Mant Porcentajes
                        </div>
                    </div>
                </main>
                <footer class="footer mt-auto footer-light">
                    <div class="container-fluid">

                    </div>
                </footer>
                <!-- Modal -->
                <div id="modalSolicitud" class="modal fade" id="exampleModalScrollable" tabindex="-1" role="dialog" aria-labelledby="exampleModalScrollableTitle" aria-hidden="true">
                    <div class="modal-dialog modal-dialog-scrollable" role="document">
                        <div class="modal-content">
                            <div class="modal-header">
                                <h5 class="modal-title" id="exampleModalScrollableTitle">Editar Solicitud</h5>
                                <button class="close" type="button" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">×</span></button>
                            </div>
                            <div class="modal-body">
                                <form>
                                    <div class="form-group">
                                        <label for="rut">Rut Alumno:</label>
                                        <input type="hidden" id="idSolicitud" name="idSolicitud"/>
                                        <input type="hidden" id="hdnIdAlumno" name="hdnIdAlumno"/>
                                        <input type="hidden" id="hdnIdAlumno" name="hdnSemestre"/>
                                        <input type="hidden" id="hdnIdAlumno" name="hdnAnio"/>
                                        <input class="form-control" id="rut" name="rut" type="text" placeholder="99999999-9" readonly/>
                                    </div>
                                    <div class="form-group">
                                        <label for="nombre">Nombre:</label>
                                        <input class="form-control" id="nombre" name="nombre" type="text" placeholder="Nombre" readonly/>
                                    </div>
                                    <div class="form-group">
                                        <label for="email">Email:</label>
                                        <input class="form-control" id="email" name="email" type="email" placeholder="Email" readonly/>
                                    </div>
                                    <div class="form-group">
                                        <label for="carrera">Carrera:</label>
                                        <input type="hidden" id="hdnIdCarrera" name="hdnIdCarrera"/>
                                        <input class="form-control" id="carrera" name="carrera" type="text" placeholder="Carrera" readonly/>
                                    </div>
                                    <div class="form-group">
                                        <label for="beneficio">Beneficio</label>
                                        <input type="hidden" id="hdnIdBeneficio" name="hdnIdBeneficio"/>
                                        <input class="form-control" id="beneficio" name="beneficio" type="text" placeholder="Beneficio" readonly/>
                                    </div>
                                    <div class="form-group">
                                        <label for="ddlEstado">Estado</label>
                                        <select class="form-control" id="ddlEstado" name="ddlEstado">

                                        </select>
                                    </div>
                                    <div class="form-group">
                                        <label for="ddlPorcentaje">Porcentaje:</label>
                                        <select class="form-control" id="ddlPorcentaje" name="ddlPorcentaje">

                                        </select>
                                    </div>
                                    <div class="form-group">
                                        <label for="monto">Monto</label>
                                        <input class="form-control" id="monto" name="monto" type="text" placeholder="Monto" readonly/>
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
        <script src="utiles.js"></script>
    </body>
</html>
