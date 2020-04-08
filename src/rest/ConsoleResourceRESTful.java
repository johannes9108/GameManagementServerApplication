package rest;

import domain.Console;
import java.util.List;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import service.ConsoleService;

@Path("/consoles")
@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
public class ConsoleResourceRESTful {

    @Inject
    private ConsoleService service;

    @POST
    public Response createConsole(Console createConsole) {
        Console console = service.createConsole(createConsole);
        if (console != null) {
        	return Response.ok( service.createConsole(createConsole)).build();
        } else {
            return Response.status(Status.NOT_MODIFIED).build();
        }

    }

    @DELETE
    @Path("{name}")
    public Response removeConsole(@PathParam("name") String name
    ) {
        Console console = service.removeConsole(name);
        if (console == null) {
        	return Response.noContent().build();
        }
        return Response.ok(console).build();
    }

    @PUT
    public Response updateConsole(Console updateConsole
    ) {
        return Response.ok(service.updateConsole(updateConsole)).build();
    }

    @GET
    @Path("{name}")
    public Response findConsoleByName(@PathParam("name") String name
    ) {
        Console console = service.findConsoleByName(name);
        if (console == null) {
            return Response.noContent().build();
        }
        return Response.ok(console).build();
    }

    @GET
    public Response getAllConsoles() {
        List<Console> consoles = service.getAllConsoles();
        if (!consoles.isEmpty()) {
            return Response.ok(consoles).build();
        } else {
            return Response.noContent().build();
        }
    }
}
