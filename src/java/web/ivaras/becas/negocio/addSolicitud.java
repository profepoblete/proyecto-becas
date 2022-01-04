/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web.ivaras.becas.negocio;

import web.ivaras.becas.util.Letter;
import web.ivaras.becas.util.Postman;
import java.io.*;
import java.nio.file.Paths;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;
import web.ivaras.becas.datos.DAOSolicitud;
import web.ivaras.becas.entidades.Solicitud;

/**
 *
 * @author cesar
 */
@MultipartConfig(maxFileSize = 16177215)
@WebServlet(name = "addSolicitud", urlPatterns = {"/addSolicitud"})
public class addSolicitud extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            Letter letter = new Letter();
            DAOSolicitud dSolicitud = new DAOSolicitud();
            Solicitud sol = new Solicitud();
            HttpSession session = request.getSession();
            Date fecha_solicitud = new Date();
            int annio_ingreso = Integer.parseInt(!request.getParameter("ingreso").equals("") ? request.getParameter("ingreso") : "0");
            int semestre = Integer.parseInt(!request.getParameter("semestre").equals("") ? request.getParameter("semestre") : "0");
            int anio_egreso = Integer.parseInt(!request.getParameter("anioegreso").equals("") ? request.getParameter("anioegreso") : "0");
            String r2_hermano = request.getParameter("rutSegundoHermano");
            String r3_hermano = request.getParameter("rutTercerHermano");
            Date fecha_update = new Date();

            InputStream archivo = null;
            byte[] archivo_bytes = null;
            String nombre_archivo = "";
            Part filePart = request.getPart("file");
            if (filePart.getSize() > 0) {

                for (String content : filePart.getHeader("content-disposition").split(";")) {
                    if (content.trim().startsWith("filename")) {
                        nombre_archivo = content.substring(content.indexOf("=") + 1).trim().replace("\"", "");
                    }
                }
                archivo = filePart.getInputStream();
            }

            int id_alumno = Integer.parseInt(request.getParameter("hdnIdAlumno") != null ? request.getParameter("hdnIdAlumno") : "0");

            int id_estado = 1;
            int id_tipo_solicitud = Integer.parseInt(!request.getParameter("ddlTipoSolilcitud").equals("") ? request.getParameter("ddlTipoSolilcitud") : "0");
            int id_beneficio = Integer.parseInt(!request.getParameter("ddlBeneficio").equals("") ? request.getParameter("ddlBeneficio") : "0");
            int id_carrera = Integer.parseInt(!request.getParameter("hdnIdCarrera").equals("") ? request.getParameter("hdnIdCarrera") : "0");
            sol.setAnio_egreso(anio_egreso);
            sol.setFecha_solicitud(fecha_solicitud);
            sol.setFecha_update(fecha_update);
            sol.setAnio_ingreso(annio_ingreso);
            sol.setSemestre(semestre);
            sol.setId_alumno(id_alumno);
            sol.setR2_hermano(r2_hermano);
            sol.setR3_hermano(r3_hermano);
            sol.setArchivo(archivo);
            sol.setNombre_archivo(nombre_archivo);
            sol.setId_estado(id_estado);
            sol.setId_tipo_solicitud(id_tipo_solicitud);
            sol.setId_beneficio(id_beneficio);
            sol.setId_carrera(id_carrera);
            if (dSolicitud.agregar(sol) == 1) {
                letter.emailTo = request.getParameter("email");
                letter.text = "Formulario enviado (Creado por funcionario)";
                letter.name = request.getParameter("nombre");
                boolean sendCorrect = Postman.send(letter);
                if (sendCorrect) {
                    System.out.println(">>> Envio de correo notificacion correcto");
                } else {
                    System.out.println(">>> Error, no se pudo enviar el correo de notificacion");
                }

                session.setAttribute("res", 1);
                session.setAttribute("msg", "solicitud creada");
                response.sendRedirect("financiamiento/solicitud.jsp");
            } else {
                session.setAttribute("res", 0);
                session.setAttribute("msg", "error al crear solicitud");
                response.sendRedirect("financiamiento/solicitud.jsp");

            }
        } catch (Exception ex) {
            HttpSession session = request.getSession();
            session.setAttribute("res", 0);
            session.setAttribute("msg", ex.toString());
            response.sendRedirect("financiamiento/solicitud.jsp");
            Logger.getLogger(addSolicitudAlumno.class.getName()).log(Level.SEVERE, null, ex);
            
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
