/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.Conexion;
import Modelo.DTO_Hotel;
import Modelo.DTO_Sucursal;
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
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author user
 */
public class Servlet_Hotel extends HttpServlet {

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
            out.println("<title>Servlet Servlet_Hotel</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Servlet_Hotel at " + request.getContextPath() + "</h1>");
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

            String chief = request.getParameter("chief");
            String name = request.getParameter("name");
            String nit = request.getParameter("nit");
            String phone = request.getParameter("phone");

            java.util.Date tempDate = new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("date_founded"));
            Timestamp date_founded = new Timestamp(tempDate.getTime());

            String city = request.getParameter("city");
            String direction = request.getParameter("direction");
            String phoneSucursal = request.getParameter("phoneSucursal");

            java.util.Date tempDate2 = new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("dateFoundedSucursal"));
            Date dateFoundedSucursal = new Date(tempDate2.getTime());

            DAO_Hotel objDataHotel = new DAO_Hotel(conexion);
            DAO_Persona objDataPersona = new DAO_Persona(conexion);
            DAO_Sucursal objDataSucursal = new DAO_Sucursal(conexion);
            DAO_Ciudad objDataCiudad = new DAO_Ciudad(conexion);

            int hotel_id = objDataHotel.createHotel(new DTO_Hotel(0,
                    objDataPersona.getPersona(chief),
                    name,
                    nit,
                    date_founded,
                    phone));
            boolean resultSucursal = objDataSucursal.createSucursal(new DTO_Sucursal(0,
                    objDataHotel.getHotel(hotel_id),
                    objDataCiudad.getCity(Integer.parseInt(city)),
                    direction,
                    phoneSucursal,
                    dateFoundedSucursal));
            if (resultSucursal) {
                response.sendRedirect("main/home.jsp");
            }

        } catch (SQLException | ParseException ex) {
            Logger.getLogger(Servlet_Hotel.class.getName()).log(Level.SEVERE, null, ex);
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
