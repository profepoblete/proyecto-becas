/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web.ivaras.becas.negocio;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import web.ivaras.becas.datos.DAOBeneficio;
import web.ivaras.becas.entidades.Beneficio;

/**
 *
 * @author cesar
 */
@WebServlet(name = "listaBeneficio", urlPatterns = {"/listaBeneficio"})
public class listaBeneficio extends HttpServlet {

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
        response.setContentType("text/html; charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        DAOBeneficio dBeneficio = new DAOBeneficio();
        PrintWriter out = response.getWriter();
        int tipoBeneficio;
        tipoBeneficio = Integer.parseInt(request.getParameter("tipoBeneficio"));
        ArrayList<Beneficio> listaBeneficio = dBeneficio.listarTodoPorTipo(tipoBeneficio);
        out.println("<option value='0'>Seleccione una opción</option>");
        listaBeneficio.forEach(b -> {
            out.println("<option value='"+b.getId_beneficio()+"'>"+b.getNombre()+"</option>");
        });
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
