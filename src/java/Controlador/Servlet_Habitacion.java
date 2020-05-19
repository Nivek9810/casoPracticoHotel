/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.Conexion;
import Modelo.DTO_Habitacion;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author user
 */
public class Servlet_Habitacion extends HttpServlet {

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
            out.println("<title>Servlet Servlet_Habitacion</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Servlet_Habitacion at " + request.getContextPath() + "</h1>");
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
        try {

            Conexion con = new Conexion();
            Connection conexion = con.getConnection();
            int cod_hotel = Integer.parseInt(request.getParameter("hotel"));
            int cod_tipo_habitacion = Integer.parseInt(request.getParameter("tipoHabitacion"));
            String cost = request.getParameter("costNight");

            DAO_Habitacion objDataHabitacion = new DAO_Habitacion(conexion);
            DAO_Hotel objDataHotel = new DAO_Hotel(conexion);
            DAO_Tipo_habitacion objDataTipoHabitacion = new DAO_Tipo_habitacion(conexion);

            int cod_habitacion = objDataHabitacion.createHabitacion(new DTO_Habitacion(0,
                    objDataHotel.getHotel(cod_hotel),
                    objDataTipoHabitacion.getTipoHabitacion(cod_tipo_habitacion),
                    cost));
            if (cod_habitacion > 0){
                response.sendRedirect("main/home.jsp");
            }
        } catch (SQLException ex) {
            Logger.getLogger(Servlet_Habitacion.class.getName()).log(Level.SEVERE, null, ex);
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
