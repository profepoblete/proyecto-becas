<div id="layoutSidenav_nav">
                <nav class="sidenav shadow-right sidenav-light">
                    <div class="sidenav-menu">
                        <div class="nav accordion" id="accordionSidenav">
                            <div class="sidenav-menu-heading">Principal</div>
                            <a class="nav-link collapsed" href="javascript:void(0);" data-toggle="collapse" data-target="#collapseLayouts" aria-expanded="false" aria-controls="collapseLayouts"
                                ><div class="nav-link-icon"><i data-feather="layout"></i></div>
                                Solicitudes
                                <div class="sidenav-collapse-arrow"><i class="fas fa-angle-down"></i></div
                            ></a>
                            <div class="collapse" id="collapseLayouts" data-parent="#accordionSidenav">
                                <nav class="sidenav-menu-nested nav accordion" id="accordionSidenavLayout">
                                    <a class="nav-link" href="solicitud.jsp">Solicitud Alumno</a>
                                    <a class="nav-link" href="gest_solicitudes.jsp">Gestionar Solicitudes</a>
                                    
                                    
                                </nav>
                            </div>
                            <a class="nav-link collapsed" href="javascript:void(0);" data-toggle="collapse" data-target="#collapseComponents" aria-expanded="false" aria-controls="collapseComponents"
                                ><div class="nav-link-icon"><i data-feather="package"></i></div>
                                Mantenedores
                                <div class="sidenav-collapse-arrow"><i class="fas fa-angle-down"></i></div
                            ></a>
                            <div class="collapse" id="collapseComponents" data-parent="#accordionSidenav">
                                <nav class="sidenav-menu-nested nav">
                                    <a class="nav-link" href="mant_beneficios.jsp">Beneficios</a>
                                    <a class="nav-link" href="mant_aranceles.jsp">Aranceles</a>
                                </nav>
                            </div>
                            <a class="nav-link collapsed" href="javascript:void(0);" data-toggle="collapse" data-target="#collapseUtilities" aria-expanded="false" aria-controls="collapseUtilities"
                                ><div class="nav-link-icon"><i data-feather="tool"></i></div>
                                Informes
                                <div class="sidenav-collapse-arrow"><i class="fas fa-angle-down"></i></div
                            ></a>
                            <div class="collapse" id="collapseUtilities" data-parent="#accordionSidenav">
                                <nav class="sidenav-menu-nested nav">
                                    <a class="nav-link" href="missolicitudes.jsp">Consolidado Solicitudes</a>
                                </nav>
                            </div>
                        </div>
                    </div>
                    <div class="sidenav-footer">
                        <div class="sidenav-footer-content">
                            <div class="sidenav-footer-subtitle">Usuario:</div>
                            <div class="sidenav-footer-title"><j:out value="${usu.getNombre_completo()}"></j:out></div>
                        </div>
                    </div>
                </nav>
            </div>