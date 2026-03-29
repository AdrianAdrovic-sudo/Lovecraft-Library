package org.lovecraftlibrary.resource;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.lovecraftlibrary.model.Author;
import org.lovecraftlibrary.model.Book;
import org.lovecraftlibrary.service.AuthorService;
import java.util.List;

@Path("/authors")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AuthorResource {

    @Inject
    AuthorService authorService;

    @POST
    public Response create(Author author) {
        authorService.save(author);
        return Response.status(Response.Status.CREATED).entity(author).build();
    }

    @GET
    public List<Author> getAll() {
        return authorService.findAll();
    }

    @GET
    @Path("/{id}")
    public Response getById(@PathParam("id") Long id) {
        Author author = authorService.findById(id);
        if (author == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(author).build();
    }

    @GET
    @Path("/search")
    public List<Author> searchByName(@QueryParam("name") String name) {
        return authorService.findByName(name);
    }

    @GET
    @Path("/{id}/books")
    public List<Book> getBooksByAuthor(@PathParam("id") Long id) {
        return authorService.findBooksByAuthor(id);
    }
}