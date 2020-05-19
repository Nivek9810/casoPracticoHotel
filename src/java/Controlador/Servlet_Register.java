/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.Conexion;
import Modelo.DTO_Funcionario;
import Modelo.DTO_Genero;
import Modelo.DTO_Persona;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
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
public class Servlet_Register extends HttpServlet {

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
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Register</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Register at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
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

        try {
            Conexion con = new Conexion();
            Connection conexion = con.getConnection();
            DAO_Funcionario objDataFuncionario = new DAO_Funcionario(conexion);
            /* Get parameters*/
            String uid = request.getParameter("userId");

            if (request.getParameter("userId") != null) {
                request.setAttribute("editUser", objDataFuncionario.getFuncionarioByCedula(uid));

                RequestDispatcher req = request.getRequestDispatcher("register/register.jsp");
                req.forward(request, response);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Servlet_Register.class.getName()).log(Level.SEVERE, null, ex);
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
        try {
            Conexion con = new Conexion();
            Connection conexion = con.getConnection();

            DAO_Funcionario objDataFuncionario = new DAO_Funcionario(conexion);
            DAO_Persona objDataPersona = new DAO_Persona(conexion);
            DAO_Genero objDataGenre = new DAO_Genero(conexion);
            DAO_Rol objDataRol = new DAO_Rol(conexion);
            /* Get parameters*/

            String cedula = request.getParameter("cedula");

            DTO_Funcionario objCurrentFuncionario = objDataFuncionario.getFuncionarioByCedula(cedula);
            if (objCurrentFuncionario != null) {
                if (objDataFuncionario.updateUser(objCurrentFuncionario)) {
                    if (objDataPersona.updatePerson(objCurrentFuncionario.getCodigo_persona())) {
                        response.sendRedirect("home.jsp");
                    }
                }
            } else {
                DTO_Genero genero = objDataGenre.getGenero(Integer.parseInt(request.getParameter("genero")));
                String nombre = request.getParameter("name");
                String apellido_padre = request.getParameter("last_name_father");
                String apellido_madre = request.getParameter("last_name_mother");
                java.util.Date tempDate = new SimpleDateFormat("dd/MM/yyyy").parse(request.getParameter("birthdate"));
                Date birthdate = new Date(tempDate.getTime());
                String correo = request.getParameter("email");
                String telefono = request.getParameter("phone");
                String contrasena = request.getParameter("password");

                DTO_Persona objPersona = new DTO_Persona(
                        cedula,
                        genero,
                        nombre,
                        apellido_padre,
                        apellido_madre,
                        birthdate,
                        correo,
                        telefono);

                boolean responseQuery = objDataPersona.createPerson(objPersona);
                System.out.println("responseQuery" + responseQuery);
                if (responseQuery) {
                    DTO_Funcionario objFuncionario = new DTO_Funcionario(
                            objPersona,
                            objDataRol.getRol(3),
                            contrasena,
                            new Timestamp(System.currentTimeMillis()),
                            new Timestamp(System.currentTimeMillis())
                    );
                    if (objDataFuncionario.registerUser(objFuncionario)) {
                        HttpSession sesion = request.getSession();
                        DTO_Persona objCurrentSesion = (DTO_Persona) sesion.getAttribute("cliente");
                        if (objCurrentSesion != null) {
                            response.sendRedirect("main/home.jsp");
                        } else {
                            sesion.setAttribute("cliente", objFuncionario.getCodigo_persona());
                            sesion.setAttribute("rol", objFuncionario.getRol());
                            /*RequestDispatcher req = request.getRequestDispatcher("main/home.jsp");
                    req.include(request, response);*/
                            response.sendRedirect("main/home.jsp");
                        }
                    }
                }
            }

        } catch (SQLException | ParseException ex) {
            System.out.println("Controlador.Servlet_Register.doPost()" + ex);
            /*RequestDispatcher req = request.getRequestDispatcher("index.jsp");
            req.forward(request, response);*/
            response.sendRedirect("index.jsp");
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
