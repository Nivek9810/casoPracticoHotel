/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.Conexion;
import Modelo.DTO_Pago;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author user
 */
public class Servlet_CheckOut extends HttpServlet {

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
            out.println("<title>Servlet Servlet_CheckOut</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Servlet_CheckOut at " + request.getContextPath() + "</h1>");
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
        int code_reservation = Integer.parseInt(request.getParameter("reservation"));
        if (code_reservation > 0) {
            try {
                Conexion con = new Conexion();
                Connection conexion = con.getConnection();

                DAO_Pago objDataPago = new DAO_Pago(conexion);
                DAO_Reservacion objDataReservacion = new DAO_Reservacion(conexion);
                DAO_Forma_pago objDataMetodoPago = new DAO_Forma_pago(conexion);

                String total_a_pagar = "";

                /**
                 * Validate user state
                 */
                DTO_Pago objUserPayment = objDataPago.checkUserPayment(code_reservation);
                if (objUserPayment != null) {
                    request.setAttribute("userPayment", objUserPayment);
                    RequestDispatcher req = request.getRequestDispatcher("main/bill.jsp");
                    req.forward(request, response);
                } else {
                    /**
                     * Generate payment
                     */
                    objDataPago.generatePayment(new DTO_Pago(0,
                            objDataReservacion.getReservation(code_reservation),
                            objDataMetodoPago.getPaymentMethod(code_reservation),
                            new Timestamp(System.currentTimeMillis()),
                            total_a_pagar));
                    response.sendRedirect("main/home.jsp");
                }

            } catch (SQLException ex) {
                System.out.println("Controlador.Servlet_CheckOut.doPost()" + ex);
            }

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
