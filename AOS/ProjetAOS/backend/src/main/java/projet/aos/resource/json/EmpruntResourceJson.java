package projet.aos.resource.json;

import projet.aos.dao.EmpruntDAO;
import projet.aos.models.Emprunt;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/json/emprunts")
public class EmpruntResourceJson {

    private final EmpruntDAO empruntDAO = new EmpruntDAO();

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public Response getEmprunts() {
        List<Emprunt> emprunts = empruntDAO.getEmprunts();
        return Response.ok(emprunts).build();
    }

    @GET
    @Path("/{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public Response getEmpruntById(@PathParam("id") int id) {
        Emprunt emprunt = empruntDAO.getEmpruntById(id);
        if (emprunt != null) {
            return Response.ok(emprunt).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).entity("Emprunt non trouvé.").build();
        }
    }

    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    public Response addEmprunt(Emprunt emprunt) {
        empruntDAO.addEmprunt(emprunt);
        return Response.status(Response.Status.CREATED).entity("Emprunt ajouté avec succès.").build();
    }

    @PUT
    @Path("/{id}")
    @Consumes({MediaType.APPLICATION_JSON})
    public Response updateEmprunt(@PathParam("id") int id, Emprunt updatedEmprunt) {
        empruntDAO.updateEmprunt(id, updatedEmprunt);
        return Response.status(Response.Status.OK).entity("Emprunt mis à jour avec succès.").build();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteEmprunt(@PathParam("id") int id) {
        empruntDAO.deleteEmprunt(id);
        return Response.status(Response.Status.OK).entity("Emprunt supprimé avec succès.").build();
    }

    @PUT
    @Path("/{id}/valider")
    public Response validerEmprunt(@PathParam("id") int id) {
        empruntDAO.validerEmprunt(id);
        return Response.status(Response.Status.OK).entity("Emprunt validé avec succès.").build();
    }

    @PUT
    @Path("/{id}/annuler")
    public Response annulerEmprunt(@PathParam("id") int id) {
        empruntDAO.deleteEmprunt(id);
        return Response.status(Response.Status.OK).entity("Emprunt annulé avec succès.").build();
    }
}
