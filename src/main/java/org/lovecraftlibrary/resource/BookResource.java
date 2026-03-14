package org.lovecraftlibrary.resource;

import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.lovecraftlibrary.model.Book;
import org.lovecraftlibrary.service.BookService;
import java.util.List;

@Path("/books")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class BookResource {

    @Inject
    BookService bookService;

    @POST
    public Response create(Book book) {
        bookService.save(book);
        return Response.status(Response.Status.CREATED).entity(book).build();
    }

    @GET
    public List<Book> getAll() {
        return bookService.findAll();
    }
}