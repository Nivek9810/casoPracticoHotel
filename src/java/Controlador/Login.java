/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.Conexion;
import Modelo.DTO_Funcionario;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author user
 */
public class Login extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

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
        processRequest(request, response);
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

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        HttpSession sesion = request.getSession();

        if (username.isEmpty() || password.isEmpty()) {
            RequestDispatcher req = this.setHelper(request, sesion, "Lo sentimos, las credenciales no fueron aportadas.");
            req.forward(request, response);
        } else {
            try {
                Conexion con = new Conexion();
                Connection conexion = con.getConnection(); 
                
                DAO_Funcionario objDataFuncionario = new DAO_Funcionario(conexion);
                DTO_Funcionario objFuncionario;
                objFuncionario = objDataFuncionario.userSesion(username, password);

                if (objFuncionario != null) {
                    sesion.setAttribute("cliente", objFuncionario.getCodigo_persona());
                    sesion.setAttribute("rol", objFuncionario.getRol());
                    //RequestDispatcher req = request.getRequestDispatcher("main/home.jsp");
                    //req.forward(request, response);
                    response.sendRedirect("main/home.jsp");
                } else {
                    RequestDispatcher req = this.setHelper(request, sesion, "Lo sentimos, no encontramos ninguna coincidencia con las credenciales aportadas.");
                    req.forward(request, response);

                    req.forward(request, response);
                }
            } catch (SQLException e) {
                System.out.println("Controlador.Login.doPost()-->Error" + e);
                RequestDispatcher req = this.setHelper(request, sesion, "Lo sentimos, la sesion fue expirada.");
                req.forward(request, response);
            }
        }

    }

    private RequestDispatcher setHelper(HttpServletRequest request, HttpSession sesion, String message) {
        request.setAttribute("error", message);
        RequestDispatcher req = request.getRequestDispatcher("helper/error.jsp");
        sesion.invalidate();
        return req;
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
