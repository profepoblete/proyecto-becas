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
import web.ivaras.becas.datos.DAOBeneficio;
import web.ivaras.becas.entidades.Beneficio;
import web.ivaras.becas.entidades.Respuesta;

/**
 *
 * @author cesar
 */
@WebServlet(name = "addBeneficioJSON", urlPatterns = {"/addBeneficioJSON"})
public class addBeneficioJSON extends HttpServlet {
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
        Beneficio ben = new Beneficio();
        DAOBeneficio dBeneficio = new DAOBeneficio();
        Respuesta resp = new Respuesta();
        String nombre = request.getParameter("nombreBeneficio");
        int idTipoBeneficio = Integer.parseInt(request.getParameter("idTipoBeneficio"));
        ben.setNombre(nombre);
        ben.setId_tipo_beneficio(idTipoBeneficio);
        ben.setVigente(true);
        if(dBeneficio.agregar(ben)==1)
        {
            resp.setCodigo(1);
            resp.setSuccess(1);
            resp.setMensaje("Beneficio creado con éxito");
        }
        else
        {
            resp.setCodigo(0);
            resp.setSuccess(0);
            resp.setMensaje("Error al modificar Solicitud");
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
