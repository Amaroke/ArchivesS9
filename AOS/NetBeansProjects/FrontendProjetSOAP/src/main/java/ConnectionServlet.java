import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.WebServiceRef;
import ws.soap.connection.ConnectionService;

public class ConnectionServlet extends HttpServlet {

    @WebServiceRef(wsdlLocation = "WEB-INF/wsdl/localhost_8080/ProjetSOAP/connectionservice.wsdl")
    private ConnectionService connectionService;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        String trainNumber = request.getParameter("trainNumber");

        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ConnectionServlet</title>");
            out.println("</head>");
            out.println("<body>");

            out.println("<form method='post' action='ConnectionServlet'>");
            out.println("<input type='hidden' name='trainNumber' value='" + trainNumber + "'>");
            out.println("Email: <input type='text' name='email'><br>");
            out.println("Mot de passe: <input type='password' name='password'><br>");
            out.println("<input type='submit' value='Connexion' name='connectButton'>");
            out.println("</form>");

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
        response.setContentType("text/html;charset=UTF-8");

        String trainNumber = request.getParameter("trainNumber");
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ConnectionServlet</title>");
            out.println("</head>");
            out.println("<body>");

            boolean result;
            try {
                ws.soap.connection.ConnectionServiceImpl port = connectionService.getConnectionServiceImplPort();
                result = port.login(email, password);
            } catch (Exception ex) {
                result = false;
            }

            boolean result2;
            try {
                ws.soap.connection.ConnectionServiceImpl port = connectionService.getConnectionServiceImplPort();
                result2 = port.createReservation(trainNumber, email);
            } catch (Exception ex) {
                result2 = false;
            }

            if (result) {
                if (result2) {
                    out.println("<p>Réservation créée avec succès pour le train numéro " + trainNumber + ".</p>");
                } else {
                    out.println("<p>Erreur lors de la création de la réservation.</p>");
                }
            } else {
                out.println("<p>Échec de la connexion. Vérifiez vos informations d'identification.</p>");
            }

            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
