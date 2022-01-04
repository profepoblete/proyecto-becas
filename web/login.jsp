<%@taglib prefix="j" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
        <meta name="description" content="" />
        <meta name="author" content="" />
        <title>Login</title>
        <link href="ui/css/styles.css" rel="stylesheet" />
        <link rel="icon" type="image/x-icon" href="img/favicon.png" />
        <script data-search-pseudo-elements defer src="ui/js/all.min.js" crossorigin="anonymous"></script>
        <script src="ui/js/feather.min.js" crossorigin="anonymous"></script>
    </head>
    <body class="bg-img-cover overlay overlay-primary overlay-50" style='background-image: url("img/Sede.jpg")'">
        <div id="layoutAuthentication">
            <div id="layoutAuthentication_content">
                <main>
                    <div class="container">
                        <div class="row justify-content-center">
                            <div class="col-lg-5">
                                <div class="card shadow-lg border-0 rounded-lg mt-5">
                                    <div class="card-header justify-content-center"><h3 class="font-weight-light my-4">Login</h3></div>
                                    <div class="card-body">
                                        <%
                                        session = request.getSession();
                                        if(session.getAttribute("msg")!=null)
                                        {
                                        %>
                                            <div class="alert alert-danger" role="alert">
                                                <button class="close" type="button" data-dismiss="alert" aria-label="Close">
                                                    <span aria-hidden="true">×</span>
                                                </button>
                                            <j:out value="${msg}"></j:out>
                                            </div>
                                        <%
                                        }
                                        %>
                                        <form action="login" method="POST">
                                            <div class="form-group"><label class="small mb-1" for="inputEmailAddress">Email</label><input class="form-control py-4" id="inputEmailAddress" name="email" type="email" placeholder="Ingrese email" /></div>
                                            <div class="form-group"><label class="small mb-1" for="inputPassword">Contraseña</label><input class="form-control py-4" id="inputPassword" name="password" type="password" placeholder="Ingrese contraseña" /></div>
                                            <div class="form-group">
                                                <!--<div class="custom-control custom-checkbox"><input class="custom-control-input" id="rememberPasswordCheck" type="checkbox" /><label class="custom-control-label" for="rememberPasswordCheck">Remember password</label></div>-->
                                            </div>
                                            <div class="form-group d-flex align-items-center justify-content-between mt-4 mb-0"><input type="submit" class="btn btn-primary" value="Iniciar Sesión"/></div>
                                        </form>
                                    </div>
                                    <div class="card-footer text-center">
                                        
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </main>
            </div>
            <div id="layoutAuthentication_footer">
                <footer class="footer mt-auto footer-dark">
                   
                </footer>
            </div>
        </div>
        <script src="ui/js/jquery-3.4.1.min.js" crossorigin="anonymous"></script>
        <script src="ui/js/bootstrap.bundle.min.js" crossorigin="anonymous"></script>
        <script src="ui/js/scripts.js"></script>
    </body>
</html>
