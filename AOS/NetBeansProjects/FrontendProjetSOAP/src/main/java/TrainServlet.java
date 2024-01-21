/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.WebServiceRef;
import ws.soap.train.Train;
import ws.soap.train.Trainservice_Service;

/**
 *
 * @author hugoa
 */
@WebServiceRef(wsdlLocation = "http://localhost:8080/ProjetSOAP/TrainService?WSDL")
public class TrainServlet extends HttpServlet {

    @WebServiceRef(wsdlLocation = "WEB-INF/wsdl/localhost_8080/ProjetSOAP/trainservice.wsdl")
    private Trainservice_Service trainService;

    public List<Train> SearchTrains(String departure, String destination, String departureDate, int departureTime) {
        try { // Call Web Service Operation
            ws.soap.train.Trainservice port = trainService.getTrainServicePort();
            java.util.List<ws.soap.train.Train> result = port.searchTrains(departure, destination, departureDate,
                    departureTime);
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
        response.setContentType("text/html;charset=UTF-8");

        // Retrieve form parameters
        String departure = request.getParameter("departure");
        String destination = request.getParameter("destination");
        String departureDate = request.getParameter("departure-date");
        String departureTimeStr = request.getParameter("departure-time");

        String[] timeParts = departureTimeStr.split(":");
        int hours = Integer.parseInt(timeParts[0]);
        int minutes = Integer.parseInt(timeParts[1]);

        int departureTime = hours * 100 + minutes;

        List<Train> searchResults = SearchTrains(departure, destination, departureDate, departureTime);

        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet TrainServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Billets disponibles :</h1>");

            for (Train train : searchResults) {
                out.println("<p>Numéro du train : " + train.getNumber() + "</p>");
                out.println("<p>Ville de départ : " + train.getDepartureCity() + "</p>");
                out.println("<p>Vile d'arrivée : " + train.getArrivalCity() + "</p>");
                out.println("<p>Date de départ : " + train.getDate() + "</p>");
                String departureTimeString = Integer.toString(train.getDepartureTime());
                departureTimeString = departureTimeString.substring(0, departureTimeString.length() - 2) + ":"
                        + departureTimeString.substring(departureTimeString.length() - 2);
                out.println("<p>Heure de départ : " + departureTimeString + "</p>");
                out.println("<p>Prix du ticket : " + train.getTicketPrice() + "€</p>");
                out.println("<p>Nombre de places : " + train.getAvailableSeats() + "</p>");
                out.println("<form name='trainForm' method='get' action='ConnectionServlet'>");
                out.println("<input type='hidden' name='trainNumber' value='" + train.getNumber() + "'>");
                out.println("<input type='submit' value='Réserver' name='reserveButton'>");
                out.println("</form>");
                out.println("<hr>");
            }

            out.println("</body>");
            out.println("</html>");
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
