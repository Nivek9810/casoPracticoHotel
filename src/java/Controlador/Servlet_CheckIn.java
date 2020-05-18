/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.DTO_Pago;
import Modelo.DTO_Persona;
import Modelo.DTO_Reservacion;
import Modelo.DTO_Rol;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author user
 */
public class Servlet_CheckIn extends HttpServlet {

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
            out.println("<title>Servlet CheckIn_Servlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet CheckIn_Servlet at " + request.getContextPath() + "</h1>");
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
        //Here we recieved all the params
        try {
            String cliente = request.getParameter("cliente");
            int habitacion = Integer.parseInt(request.getParameter("habitacion"));
            Date tempDateIngreso = new SimpleDateFormat("dd/MM/yyyy").parse(request.getParameter("fecha_ingreso"));
            Date tempDateSalida = new SimpleDateFormat("dd/MM/yyyy").parse(request.getParameter("fecha_salida"));
            Timestamp fecha_ingreso = new Timestamp(tempDateIngreso.getTime());
            Timestamp fecha_salida = new Timestamp(tempDateSalida.getTime());

            DAO_Reservacion objDataReservacion = new DAO_Reservacion();
            DAO_Habitacion objDataHabitacion = new DAO_Habitacion();
            DAO_Persona objDataPersona = new DAO_Persona();

            HttpSession sesion = request.getSession();
            DTO_Rol rol = (DTO_Rol) sesion.getAttribute("rol");
            DTO_Persona user = (cliente == null) ? (DTO_Persona) sesion.getAttribute("cliente") : objDataPersona.getPersona(cliente);

            DTO_Reservacion objReservacion = new DTO_Reservacion(0,
                    user,
                    objDataHabitacion.getHabitacion(habitacion),
                    fecha_ingreso,
                    fecha_salida);

            int codigoReservacion = objDataReservacion.createReservacion(objReservacion);
            objReservacion.setCodigo_reservacion(codigoReservacion);

            if (codigoReservacion > 0) {

                if (rol.getCodigo_rol() != 1) {
                    DAO_Forma_pago objDataFormaPago = new DAO_Forma_pago();
                    DAO_Pago objDataPago = new DAO_Pago();
                    int paymentMethod = Integer.parseInt(request.getParameter("payment"));

                    double amount = 0, helpAmount = 0;

                    /* It's just for emergency */
                    String[] cost = objReservacion.getHabitacion().getValor().split(" ");
                    for (String element : cost) {
                        String[] nextValue = element.split(",");
                        for (String number : nextValue) {
                            if (this.isNumeric(number)) {
                                helpAmount = Double.parseDouble(number);
                                if (helpAmount > 0) {
                                    amount = (helpAmount * 0.3);
                                }
                            }
                        }
                    }
                    System.out.println("Controlador.Servlet_CheckIn.doPost()" + amount);
                    /* It's just for emergency */

                    objDataPago.generatePayment(new DTO_Pago(
                            0,
                            objReservacion,
                            objDataFormaPago.getPaymentMethod(paymentMethod),
                            new Timestamp(System.currentTimeMillis()),
                            "" + amount
                    ));

                }
                response.sendRedirect("main/home.jsp");
            }

        } catch (ParseException | SQLException ex) {
            Logger.getLogger(Servlet_CheckIn.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public boolean isNumeric(String strNum) {
        if (strNum == null) {
            return false;
        }
        try {
            double d = Double.parseDouble(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
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
