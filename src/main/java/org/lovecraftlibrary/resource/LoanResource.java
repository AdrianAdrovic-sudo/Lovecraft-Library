package org.lovecraftlibrary.resource;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.lovecraftlibrary.model.Loan;
import org.lovecraftlibrary.service.LoanService;
import java.util.List;

@Path("/loans")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class LoanResource {

    @Inject
    LoanService loanService;

    @POST
    public Response create(Loan loan) {
        loanService.save(loan);
        return Response.status(Response.Status.CREATED).entity(loan).build();
    }

    @GET
    public List<Loan> getAll() {
        return loanService.findAll();
    }

    @GET
    @Path("/{id}")
    public Response getById(@PathParam("id") Long id) {
        Loan loan = loanService.findById(id);
        if (loan == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(loan).build();
    }
}