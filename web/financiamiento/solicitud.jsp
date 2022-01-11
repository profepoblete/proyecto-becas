<%@taglib prefix="j" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="es">
    <head>
        <meta charset="utf-8" />
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
            <div id="layoutSidenav_content">
                <main>
                    <div class="page-header pb-10 page-header-dark bg-gradient-primary-to-secondary">
                        <div class="container-fluid">
                            <div class="page-header-content">
                                <h1 class="page-header-title">
                                    <div class="page-header-icon"><i data-feather="activity"></i></div>
                                    <span>Solicitud</span>
                                </h1>
                                <div class="page-header-subtitle"></div>
                            </div>
                        </div>
                    </div>
                    <div class="container-fluid mt-n10">
                        <div class="card mb-4">
                            <div class="card-header">Solicitud</div>
                            <j:if test="${res==1}">
                                <div class="alert alert-success" role="alert">
                                                <button class="close" type="button" data-dismiss="alert" aria-label="Close">
                                                    <span aria-hidden="true">×</span>
                                                </button>
                                            <j:out value="${msg}"></j:out>
                                 </div>
                            </j:if>
                            <j:if test="${res==0}">
                                <div class="alert alert-danger" role="alert">
                                                <button class="close" type="button" data-dismiss="alert" aria-label="Close">
                                                    <span aria-hidden="true">×</span>
                                                </button>
                                            <j:out value="${msg}"></j:out>
                                </div>
                            </j:if>
                            <div class="card-body">
                                <form action="../addSolicitud" method="POST" id="solicitud" enctype="multipart/form-data">
                                    <div class="form-group">
                                        <label for="rut">Rut Alumno:</label>
                                        <input type="hidden" id="hdnIdAlumno" name="hdnIdAlumno"/>
                                        <input class="form-control" id="rut" name="rut" type="text" placeholder="99999999-9"/>
                                    </div>
                                    <div class="form-group">
                                        <label for="nombre">Nombre:</label>
                                        <input class="form-control" id="nombre" name="nombre" type="text" placeholder="Nombre"/>
                                    </div>
                                    <div class="form-group">
                                        <label for="email">Email:</label>
                                        <input class="form-control" id="email" name="email" type="email" placeholder="Email"/>
                                    </div>
                                    <div class="form-group">
                                        <label for="carrera">Carrera:</label>
                                        <input type="hidden" id="hdnIdCarrera" name="hdnIdCarrera"/>
                                        <input class="form-control" id="carrera" name="carrera" type="text" placeholder="Carrera"/>
                                    </div>
                                    <div class="form-group">
                                        <label for="telefono">Telefono:</label>
                                        <input class="form-control" id="telefono" name="telefono" type="text" placeholder="+5699999999"/>
                                    </div>
                                    <div class="form-group">
                                        <label for="ingreso">Año ingreso:</label>
                                        <input class="form-control" id="ingreso" name="ingreso" type="text" placeholder="2000"/>
                                    </div>
                                     <div class="form-group">
                                        <label for="semestre">Semestre:</label>
                                        <input class="form-control" id="semestre" name="semestre" type="text" placeholder="1"/>
                                    </div>
                                    <div class="form-group">
                                        <label for="ddlTipoSolilcitud">Tipo Solicitud:</label>
                                        <select class="form-control" id="ddlTipoSolilcitud" name="ddlTipoSolilcitud">
                                            <option value="0">Seleccione Tipo Solicitud</option>
                                            <j:forEach var="ts" items="${listaTipoSolicitud}">
                                                <option value="${ts.getId_tipo_solicitud()}">${ts.getTipo_solicitud()}</option>
                                            </j:forEach>
                                        </select>
                                    </div>
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
                                        <label for="ddlBeneficio">Beneficio:</label>
                                        <select class="form-control" id="ddlBeneficio" name="ddlBeneficio">
                                            
                                        </select>
                                    </div>
                                    <div id="divSegundoHermano" hidden="true">
                                        <div class="form-group">
                                            <label for="rutSegundoHermano">Rut Segundo Hermano:</label>
                                            <input class="form-control" id="rutSegundoHermano" name="rutSegundoHermano" type="text" placeholder="99999999-9"/>
                                        </div>
                                        
                                    </div>
                                    <div id="divTercerHermano" hidden="true">
                                        <div class="form-group">
                                            <label for="rutTercerHermano">Rut Tercer Hermano:</label>
                                            <input class="form-control" id="rutTercerHermano" name="rutTercerHermano" type="text" placeholder="99999999-9"/>
                                        </div>
                                        
                                    </div>
                                    <div id="divAnioEgreso" hidden="true">
                                        <div class="form-group">
                                            <label for="anioegreso">Año Egreso:</label>
                                            <input class="form-control" id="anioegreso" name="anioegreso" type="text" placeholder="9999"/>
                                        </div>
                                        
                                    </div>
                                    <div id="divFile" hidden="true">
                                        <div class="form-group">
                                            <label for="archivo">Archvo adjunto:</label>
                                            <input class="file-upload__input" id="archivo" name="file" type="file" accept=".pdf"/>
                                        </div>
                                        
                                    </div>
                                    <div class="form-group">
                                        <input type="submit" class="btn btn-primary" value="Enviar"/>
                                    </div>
                                </form>
                            </div>
                            
                        </div>
                    </div>
                </main>
                <footer class="footer mt-auto footer-light">
                    <div class="container-fluid">
                        <div class="row">
                           
                        </div>
                    </div>
                </footer>
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

