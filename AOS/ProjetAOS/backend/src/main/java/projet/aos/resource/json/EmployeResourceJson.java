package projet.aos.resource.json;

import projet.aos.dao.EmployeDAO;
import projet.aos.models.Employe;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/json/employes")
public class EmployeResourceJson {

    private final EmployeDAO employeDAO = new EmployeDAO();

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public Response getEmployes() {
        List<Employe> employes = employeDAO.getEmployes();
        return Response.ok(employes).build();
    }

    @GET
    @Path("/{numeroMembre}")
    @Produces({MediaType.APPLICATION_JSON})
    public Response getEmployeByNumeroMembre(@PathParam("numeroMembre") String numeroMembre) {
        Employe employe = employeDAO.getEmployeByNumeroMembre(numeroMembre);
        if (employe != null) {
            return Response.ok(employe).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).entity("Employé non trouvé.").build();
        }
    }

    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    public Response addEmploye(Employe employe) {
        employeDAO.addEmploye(employe);
        return Response.status(Response.Status.CREATED).entity("Employé ajouté avec succès.").build();
    }

    @PUT
    @Path("/{numeroMembre}")
    @Consumes({MediaType.APPLICATION_JSON})
    public Response updateEmploye(@PathParam("numeroMembre") int numeroMembre, Employe updatedEmploye) {
        employeDAO.updateEmploye(numeroMembre, updatedEmploye);
        return Response.status(Response.Status.OK).entity("Employé mis à jour avec succès.").build();
    }

    @DELETE
    @Path("/{numeroMembre}")
    public Response deleteEmploye(@PathParam("numeroMembre") int numeroMembre) {
        employeDAO.deleteEmploye(numeroMembre);
        return Response.status(Response.Status.OK).entity("Employé supprimé avec succès.").build();
    }

    @PUT
    @Path("/login")
    @Consumes({MediaType.APPLICATION_JSON})
    public Boolean login(Employe employe) {
        Employe employeFromDB = employeDAO.getEmployeByNumeroMembre(employe.getNumeroMembre() + "");
        return employeFromDB != null && employeFromDB.getMotDePasse().equals(employe.getMotDePasse());
    }
}
