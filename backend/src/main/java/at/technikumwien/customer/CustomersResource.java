package at.technikumwien.customer;

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
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

@Path("/customers")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class CustomersResource {
	@Inject
	private CustomersService customersService;
	@Context
	private UriInfo uriInfo;
	
	@POST
	public Response create(Customers customers) {
		customers.setId(null);	//better safe than sorry
		customers = customersService.save(customers);
		
		var location = uriInfo.getAbsolutePathBuilder().path(customers.getId().toString()).build();
		return Response.created(location).build();
	}
	
	@GET
	@Path("/{id}")
	public Customers retrieve(@PathParam("id") long id) {
		return customersService.findById(id);
	}
	
	@GET
	@Path("/{id}")
	@Produces(MediaType.TEXT_PLAIN)
	public String retrieveAsString(@PathParam("id") long id) {
		var customers = customersService.findById(id);
		return customers.toString();
	}
	
	@PUT
	@Path("/{id}")
	public void update(@PathParam("id") long id, Customers customers) {
		customers.setId(id);	//better safe than sorry
		customersService.save(customers);
	}
	
	@DELETE
	@Path("/{id}")
	public void delete(@PathParam("id") long id) {
		customersService.removeById(id);
	}
	
	@GET
	public List<Customers> retrieveAll() {
		return customersService.findAll();
	}
	
	
}
