package projet.aos.resource.json;

import projet.aos.dao.VehiculeDAO;
import projet.aos.models.Vehicule;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/json/vehicules")
public class VehiculeResourceJson {

    private final VehiculeDAO vehiculeDAO = new VehiculeDAO();

    @GET
    @Produces({ MediaType.APPLICATION_JSON })
    public Response getVehicules() {
        List<Vehicule> vehicules = vehiculeDAO.getVehicules();
        return Response.ok(vehicules).build();
    }

    @GET
    @Path("/{immatriculation}")
    @Produces({ MediaType.APPLICATION_JSON })
    public Response getVehiculeById(@PathParam("immatriculation") String immatriculation) {
        Vehicule vehicule = vehiculeDAO.getVehiculeByImmatriculation(immatriculation);
        if (vehicule != null) {
            return Response.ok(vehicule).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).entity("Vehicule non trouvé.").build();
        }
    }

    @POST
    @Consumes({ MediaType.APPLICATION_JSON })
    public Response addVehicule(Vehicule vehicule) {
        vehiculeDAO.addVehicule(vehicule);
        return Response.status(Response.Status.CREATED).entity("Vehicule ajouté avec succès.").build();
    }

    @PUT
    @Path("/{immatriculation}")
    @Consumes({ MediaType.APPLICATION_JSON })
    public Response updateVehicule(@PathParam("immatriculation") String immatriculation, Vehicule updatedVehicule) {
        vehiculeDAO.updateVehicule(immatriculation, updatedVehicule);
        return Response.status(Response.Status.OK).entity("Vehicule mis à jour avec succès.").build();
    }

    @DELETE
    @Path("/{immatriculation}")
    public Response deleteVehicule(@PathParam("immatriculation") String immatriculation) {
        vehiculeDAO.deleteVehicule(immatriculation);
        return Response.status(Response.Status.OK).entity("Vehicule supprimé avec succès.").build();
    }
}
