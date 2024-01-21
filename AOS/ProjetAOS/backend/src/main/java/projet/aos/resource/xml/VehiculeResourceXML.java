package projet.aos.resource.xml;

import projet.aos.dao.VehiculeDAO;
import projet.aos.models.Vehicule;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/xml/vehicules")
public class VehiculeResourceXML {

    private final VehiculeDAO vehiculeDAO = new VehiculeDAO();

    @GET
    @Produces({ MediaType.APPLICATION_XML })
    public Response getVehicules() {
        List<Vehicule> listeVehicules = vehiculeDAO.getVehicules();
        StringBuilder xml = new StringBuilder("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                "<vehicules>\n");
        for (Vehicule v : listeVehicules) {
            xml.append("<vehicule>\n" +
                    "<immatriculation>").append(v.getImmatriculation()).append("</immatriculation>\n")
                    .append("<type>").append(v.getType()).append("</type>\n")
                    .append("<marque>").append(v.getMarque()).append("</marque>\n")
                    .append("<modele>").append(v.getModele()).append("</modele>\n")
                    .append("<categorie>").append(v.getCategorie()).append("</categorie>\n")
                    .append("<boiteDeVitesse>").append(v.getBoiteDeVitesse()).append("</boiteDeVitesse>\n")
                    .append("<nbDePlaces>").append(v.getNbDePlaces()).append("</nbDePlaces>\n")
                    .append("<description>").append(v.getDescription()).append("</description>\n")
                    .append("</vehicule>\n");
        }
        xml.append("</vehicules>\n");
        return Response.ok(xml.toString()).build();
    }

    @GET
    @Path("/{immatriculation}")
    @Produces({ MediaType.APPLICATION_XML })
    public Response getVehiculeById(@PathParam("immatriculation") String immatriculation) {
        Vehicule vehicule = vehiculeDAO.getVehiculeByImmatriculation(immatriculation);
        if (vehicule != null) {
            String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                    "<vehicule>\n" +
                    "<immatriculation>" +
                    vehicule.getImmatriculation() + "</immatriculation>\n" +
                    "<type>" + vehicule.getType() + "</type>\n" +
                    "<marque>" + vehicule.getMarque() + "</marque>\n" +
                    "<modele>" + vehicule.getModele() + "</modele>\n" +
                    "<categorie>" + vehicule.getCategorie() + "</categorie>\n" +
                    "<boiteDeVitesse>" + vehicule.getBoiteDeVitesse() + "</boiteDeVitesse>\n" +
                    "<nbDePlaces>" + vehicule.getNbDePlaces() + "</nbDePlaces>\n" +
                    "<description>" + vehicule.getDescription() + "</description>\n" +
                    "</vehicule>\n";
            return Response.ok(xml).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).entity("Vehicule non trouvé.").build();
        }
    }
}
