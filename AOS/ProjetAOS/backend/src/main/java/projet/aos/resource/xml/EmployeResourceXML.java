package projet.aos.resource.xml;

import projet.aos.dao.EmployeDAO;
import projet.aos.models.Employe;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/xml/employes")
public class EmployeResourceXML {

    private final EmployeDAO employeDAO = new EmployeDAO();

    @GET
    @Produces({MediaType.APPLICATION_XML})
    public Response getEmployes() {
        List<Employe> employes = employeDAO.getEmployes();
        StringBuilder xml = new StringBuilder("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                "<employes>\n");
        for (Employe e : employes) {
            xml.append("<employe>\n" +
                            "<numeroMembre>").append(e.getNumeroMembre()).append("</numeroMembre>\n")
                    .append("<motDePasse>").append(e.getMotDePasse()).append("</motDePasse>\n")
                    .append("<nom>").append(e.getNom()).append("</nom>\n")
                    .append("<prenom>").append(e.getPrenom()).append("</prenom>\n")
                    .append("<numeroSecuSociale>").append(e.getNumeroSecuSociale()).append("</numeroSecuSociale>\n")
                    .append("<numeroPermis>").append(e.getNumeroPermis()).append("</numeroPermis>\n")
                    .append("<adresseDomicile>").append(e.getAdresseDomicile()).append("</adresseDomicile>\n")
                    .append("</employe>\n");
        }
        xml.append("</employes>\n");
        return Response.ok(xml.toString()).build();
    }

    @GET
    @Path("/{numeroMembre}")
    @Produces({MediaType.APPLICATION_XML})
    public Response getEmployeByNumeroMembre(@PathParam("numeroMembre") String numeroMembre) {
        Employe employe = employeDAO.getEmployeByNumeroMembre(numeroMembre);
        if (employe != null) {
            String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                    "<employe>\n" +
                    "<numeroMembre>" + employe.getNumeroMembre() + "</numeroMembre>\n" +
                    "<motDePasse>" + employe.getMotDePasse() + "</motDePasse>\n" +
                    "<nom>" + employe.getNom() + "</nom>\n" +
                    "<prenom>" + employe.getPrenom() + "</prenom>\n" +
                    "<numeroSecuSociale>" + employe.getNumeroSecuSociale() + "</numeroSecuSociale>\n" +
                    "<numeroPermis>" + employe.getNumeroPermis() + "</numeroPermis>\n" +
                    "<adresseDomicile>" + employe.getAdresseDomicile() + "</adresseDomicile>\n" +
                    "</employe>\n";
            return Response.ok(xml).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).entity("Employé non trouvé.").build();
        }
    }

}
