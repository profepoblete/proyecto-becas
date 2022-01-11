<%@page import="web.ivaras.becas.entidades.Usuario"%>
<%@taglib prefix="j" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
        <meta name="description" content="" />
        <meta name="author" content="" />
        <title>Becas - Alumnos</title>
        <link href="../ui/css/styles.css" rel="stylesheet" />
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/lity/2.4.0/lity.min.css" />
        <link rel="stylesheet" href="https://unpkg.com/aos@next/dist/aos.css" />
        <link rel="icon" type="image/x-icon" href="../img/favicon.png" />
        <script data-search-pseudo-elements defer src="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.11.2/js/all.min.js" crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/feather-icons/4.24.1/feather.min.js" crossorigin="anonymous"></script>
    </head>
    <body>
        <%
            HttpSession sesion = request.getSession();
            Usuario usu = (Usuario)sesion.getAttribute("usu");
        %>
        <div id="layoutDefault">
            <div id="layoutDefault_content">
                <main>
                    <%@include file="menu.jsp"%>
                    <jsp:include page="/listaTipoSolicitud" flush="true"/>
                    <jsp:include page="/listaTipoBeneficio" flush="true"/>
                    <jsp:include page="/listaSolicitudesFinanzas" flush="true"/>
                    <div class="page-header pb-10 page-header-dark bg-gradient-primary-to-secondary">
                        <div class="container-fluid">
                            <div class="page-header-content">
                                <h1 class="page-header-title">
                                    <div class="page-header-icon"><i data-feather="activity"></i></div>
                                    <span>Mis Solicitudes</span>
                                </h1>
                                <div class="page-header-subtitle"></div>
                            </div>
                        </div>
                    </div>
                    <div class="container-fluid mt-n10">
                         <div class="row">
                            <div class="card mb-4">
                            <div class="card-header">Lista de Solicitudes</div>
                            <div class="card-body">
                                <div class="datatable table-responsive">
                                    <table class="table table-bordered table-hover" id="dataTable" width="100%" cellspacing="0">
                                        <thead>
                                            <tr>
                                                <th>Rut Alumno</th>
                                                <th>Nombre alumno</th>
                                                <th>Carrera</th>
                                                <th>Solicitud</th>
                                                <th>Beneficio Solicitado</th>
                                                <th>Fecha Solicitud</th>
                                                <th>Fecha Modificada</th>
                                                <th>Estado</th>
                                                <th>Accion</th>
                                            </tr>
                                        </thead>
                                        <tfoot>
                                            <tr>
                                                <th>Rut Alumno</th>
                                                <th>Nombre alumno</th>
                                                <th>Carrera</th>
                                                <th>Solicitud</th>
                                                <th>Beneficio Solicitado</th>
                                                <th>Fecha Solicitud</th>
                                                <th>Fecha Modificada</th>
                                                <th>Estado</th>
                                                <th>Accion</th>
                                            </tr>
                                        </tfoot>
                                        <tbody>
                                            <j:forEach items="${listaSolicitudes}" var="s">
                                                
                                            <tr id="${s.getId_formulario()}">
                                                <td>${s.getRut_alumno()}</td>
                                                <td>${s.getAlumno()}v</td>
                                                <td>${s.getCarrera()}</td>
                                                <td>${s.getTipo_solicitud()}</td>
                                                <td>${s.getBeneficio()}</td>
                                                <td>${s.getFecha_solicitud()}</td>
                                                <td>${s.getFecha_update()}</td>
                                                <td><div class="badge badge-primary badge-pill">${s.getEstado()}</div></td>
                                                <td>
                                                    <button class="btn btn-datatable btn-icon btn-transparent-dark mr-2" onclick="editSolicitud(${s.getId_formulario()});"><i data-feather="edit"></i></button>
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
                </main>
            </div>
            <div id="layoutDefault_footer">
                <footer class="footer pt-10 pb-5 mt-auto bg-black footer-dark">
                    
                </footer>
            </div>
        </div>
        <script src="https://code.jquery.com/jquery-3.4.1.min.js" crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.bundle.min.js" crossorigin="anonymous"></script>
        <script src="../ui/js/scripts.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/lity/2.4.0/lity.min.js"></script>
        <script src="https://unpkg.com/aos@next/dist/aos.js"></script>
        <script src="utiles.js"></script>
        <script>
            AOS.init({
                disable: 'mobile',
                duration: 600,
                once: true
            });
        </script>
    </body>
</html>
