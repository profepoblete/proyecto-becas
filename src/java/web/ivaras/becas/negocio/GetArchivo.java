/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web.ivaras.becas.negocio;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import web.ivaras.becas.datos.DAOSolicitud;
import web.ivaras.becas.entidades.Solicitud;

/**
 *
 * @author cesar
 */
@WebServlet(name = "GetArchivo", urlPatterns = {"/GetArchivo"})
public class GetArchivo extends HttpServlet {

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
        response.setContentType("application/force-download");
            byte[] b = null;
            String nombre_documento = null;
            try {
                int id = Integer.parseInt(request.getParameter("id"));
                Solicitud sol = new Solicitud();
                DAOSolicitud datSol = new DAOSolicitud();
                sol = datSol.buscarPorId(id);
                b = sol.getArchivo_bytes();
                nombre_documento = sol.getNombre_archivo();
                
                InputStream bos = new ByteArrayInputStream(b);

                int tamanoInput = bos.available();
                byte[] datosPDF = new byte[tamanoInput];

                response.setHeader("Content-Disposition", "attachment;filename=" + nombre_documento);

                bos.read(datosPDF, 0, tamanoInput);

                response.getOutputStream().write(datosPDF);
                bos.close();
                

            } catch (Exception ex) {
                System.out.println(">>> Excepcion al enviar archivo en contenedor.BuscarArchivo \n" + ex.getMessage());
            }
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
