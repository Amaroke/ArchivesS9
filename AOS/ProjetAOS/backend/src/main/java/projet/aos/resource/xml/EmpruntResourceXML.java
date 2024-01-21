package projet.aos.resource.xml;

import projet.aos.dao.EmpruntDAO;
import projet.aos.models.Emprunt;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/xml/emprunts")
public class EmpruntResourceXML {

    private final EmpruntDAO empruntDAO = new EmpruntDAO();

    @GET
    @Produces({ MediaType.APPLICATION_XML })
    public Response getEmprunts() {
        List<Emprunt> emprunts = empruntDAO.getEmprunts();
        StringBuilder xml = new StringBuilder("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                "<emprunts>\n");
        for (Emprunt e : emprunts) {
            xml.append("<emprunt>\n" +
                    "<id>").append(e.getId()).append("</id>\n")
                    .append("<immatriculation>").append(e.getImmatriculation()).append("</immatriculation>\n")
                    .append("<numeroMembre>").append(e.getNumeroMembre()).append("</numeroMembre>\n")
                    .append("<datePret>").append(e.getDatePret()).append("</datePret>\n")
                    .append("<etat>").append(e.getEtat()).append("</etat>\n")
                    .append("</emprunt>\n");
        }
        xml.append("</emprunts>\n");
        return Response.ok(xml.toString()).build();
    }

    @GET
    @Path("/{id}")
    @Produces({ MediaType.APPLICATION_XML })
    public Response getEmpruntById(@PathParam("id") int id) {
        Emprunt emprunt = empruntDAO.getEmpruntById(id);
        if (emprunt != null) {
            String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                    "<emprunt>\n" +
                    "<id>" + emprunt.getId() + "</id>\n" +
                    "<immatriculation>" + emprunt.getImmatriculation() + "</immatriculation>\n" +
                    "<numeroMembre>" + emprunt.getNumeroMembre() + "</numeroMembre>\n" +
                    "<datePret>" + emprunt.getDatePret() + "</datePret>\n" +
                    "<etat>" + emprunt.getEtat() + "</etat>\n" +
                    "</emprunt>\n";
            return Response.ok(xml).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).entity("Emprunt non trouvé.").build();
        }
    }
}
