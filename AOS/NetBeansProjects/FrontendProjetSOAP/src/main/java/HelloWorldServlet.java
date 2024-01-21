/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.WebServiceRef;
import ws.soap.helloworld.Helloworld_Service;

/**
 *
 * @author hugoa
 */
@WebServiceRef(wsdlLocation = "http://localhost:8080/ProjetSOAP/HelloWorld?WSDL")
public class HelloWorldServlet extends HttpServlet {

    @WebServiceRef(wsdlLocation = "WEB-INF/wsdl/localhost_8080/ProjetSOAP/helloworld.wsdl")
    private Helloworld_Service service;

    public String HelloWorldSimple() {
        try {
            ws.soap.helloworld.Helloworld port = service.getHelloWorldPort();
            java.lang.String result = port.helloWorldSimple();
            return result;
        } catch (Exception ex) {
            return null;
        }
    }

    public String HelloWorldParam(String param) {
        try {
            ws.soap.helloworld.Helloworld port = service.getHelloWorldPort();
            java.lang.String result = port.helloWorldParam(param);
            return result;
        } catch (Exception ex) {
            return null;
        }
    }

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request  servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException      if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Get the TextArea from the web page
        String text = request.getParameter("myText");
        // Initialize WS operation arguments
        java.lang.String bodyText = text;
        // Call the service’s invocation
        String res = HelloWorldParam(bodyText);
        // Generate html page
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet HelloWorldServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h5> Service helloWorldSimple : " + HelloWorldSimple() + "</h5>");
            out.println("<h5> Service helloWorldParams : " + res + "</h5>");
            out.println("</body>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the
    // + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request  servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException      if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request  servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException      if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
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
