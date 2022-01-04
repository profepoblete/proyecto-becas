/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web.ivaras.becas.negocio;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import web.ivaras.becas.datos.DAOUsuario;
import web.ivaras.becas.entidades.Usuario;

/**
 *
 * @author cesar
 */
@WebServlet(name = "login", urlPatterns = {"/login"})
public class login extends HttpServlet {

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
         DAOUsuario dUsuario = new DAOUsuario();
         String email = request.getParameter("email");
         String password = request.getParameter("password");
         Usuario usu = dUsuario.buscarPorLogin(email);
         HttpSession session = request.getSession();
         if(usu!=null)
         {
             if(usu.getPass().equals(password))
             {
                 session.setAttribute("usu", usu);
                 if(usu.getId_tipo_usuario()==1)
                 {
                     
                     response.sendRedirect("alumno/home.jsp");
                 }
                 else
                 {
                     response.sendRedirect("financiamiento/home.jsp");
                 }
             }
             else
             {
                 session.setAttribute("msg", "contraseña incorrecta");
                 response.sendRedirect("login.jsp");
             }
         }
         else
         {
            session.setAttribute("msg", "usuario incorrecto");
            response.sendRedirect("login.jsp");
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
