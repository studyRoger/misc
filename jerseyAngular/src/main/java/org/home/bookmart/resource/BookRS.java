package org.home.bookmart.resource;

import org.home.bookmart.model.Book;
import org.home.bookmart.model.Request;

import javax.ws.rs.*;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.Response;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


/**
 * Created by roger on 9/13/15.
 */
@Path("/book")
public class BookRS {

    @GET
    @Path("/hello/{name}")
    @Produces("text/plain")
    public String hello(@PathParam("name")String name) {
        return "hello " + name;
    }

    @GET
    @Path("/{id}")
    @Produces("application/json")
    public Response getBook(@PathParam("id")int guid) {

        Book book1 = new Book(1, "book1", "book1 is ......");
        Book book2 = new Book(2, "book2", "book2 is ......");
        Book book3 = new Book(3, "book3", "book3 is ......");
        Book book4 = new Book(4, "book4", "book4 is ......");
        Book book5 = new Book(5, "book5", "book5 is ......");
        List<Book> books = Arrays.asList(book1, book2, book3, book4, book5);
        List<Book> result = null;
        if(guid == 0) {
            result = books;
        } else {
            result = books.stream().filter(book -> book.getGuid()==guid).collect(Collectors.toList());
        }

        Response response = Response.ok(new GenericEntity<List<Book>>(result){}).build();
        return response;
    }


    @POST
    @Path("/request")
    @Consumes("application/json")
    public Response submitRequest(Request request) {
        System.out.println(request.toString());
        return Response.accepted().build();
    }

}
