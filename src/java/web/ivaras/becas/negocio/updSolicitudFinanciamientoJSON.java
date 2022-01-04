/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web.ivaras.becas.negocio;

import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import web.ivaras.becas.datos.DAOSolicitud;
import web.ivaras.becas.entidades.Respuesta;
import web.ivaras.becas.entidades.Solicitud;
import web.ivaras.becas.util.Letter;
import web.ivaras.becas.util.Postman;

/**
 *
 * @author cesar
 */
@WebServlet(name = "updSolicitudFinanciamientoJSON", urlPatterns = {"/updSolicitudFinanciamientoJSON"})
public class updSolicitudFinanciamientoJSON extends HttpServlet {
    private Gson gson = new Gson();
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
        Letter letter = new Letter();
        Solicitud sol = new Solicitud();
        DAOSolicitud datSol = new DAOSolicitud();
        Respuesta resp = new Respuesta();
        sol = datSol.buscarPorId(Integer.parseInt(request.getParameter("idsolicitud")));
        
        sol.setId_estado(Integer.parseInt(request.getParameter("idestado")));
        sol.setEstado(request.getParameter("estado"));
        sol.setId_porcentaje(Integer.parseInt(request.getParameter("porcentaje")));
        sol.setMonto_beneficio(Integer.parseInt(request.getParameter("monto")));
        if(datSol.modificar(sol)==1)
        {
                letter.emailTo = request.getParameter("email");
                letter.text = "Formulario en estado " + sol.getEstado();
                letter.name = request.getParameter("nombre");
                boolean sendCorrect = Postman.send(letter);
                if (sendCorrect) {
                    System.out.println(">>> Envio de correo notificacion correcto");
                } else {
                    System.out.println(">>> Error, no se pudo enviar el correo de notificacion");
                }
            resp.setCodigo(1);
            resp.setSuccess(1);
            resp.setMensaje("Solicitud modificada con éxito");
        }
        else
        {
            resp.setCodigo(1);
            resp.setSuccess(1);
            resp.setMensaje("Solicitud modificada con éxito");
        }
        String respJsonString = this.gson.toJson(resp);

        PrintWriter out = response.getWriter();
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        out.print(respJsonString);
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
