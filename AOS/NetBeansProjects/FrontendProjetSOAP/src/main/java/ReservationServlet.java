import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.WebServiceRef;
import ws.soap.reservation.Reservation;
import ws.soap.reservation.ReservationService;

public class ReservationServlet extends HttpServlet {

    @WebServiceRef(wsdlLocation = "WEB-INF/wsdl/localhost_8080/ProjetSOAP/reservationservice.wsdl")
    private ReservationService reservationService;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        // Get the email from the web page
        String userEmail = request.getParameter("myText");

        // Retrieve reservations based on the user's email
        ws.soap.reservation.ReservationsServiceImpl port = reservationService.getReservationsServiceImplPort();
        java.util.List<ws.soap.reservation.Reservation> userReservations = port.getAllReservations(userEmail);
        

        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ReservationServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Liste des réservations pour l'utilisateur avec l'email : " + userEmail + "</h1>");

            for (Reservation reservation : userReservations) {
                out.println("<p>Numéro de réservation : " + reservation.getNumeroReservation() + "</p>");
                out.println("<p>Numéro de train : " + reservation.getNumeroTrain() + "</p>");
                out.println("<p>Informations sur le train : " + reservation.getTrainInfo() + "</p>");
                out.println("<p>Numéro de client : " + reservation.getNumeroClient() + "</p>");
                out.println("<p>Numéro de place : " + reservation.getNumeroPlace() + "</p>");
                out.println("<hr>");
            }

            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
