package rest;

import javax.inject.Inject;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import service.ListTodoService;

@Path("/Lists")
public class ListTodoController {
	
	@Inject
	private ListTodoService serv;
	
	@POST
	@Path("/create/{userId}")
	public String CreateList(@PathParam("userId") Long userId,String listsTodo) {
		return this.serv.CreateList(userId,listsTodo);
	}
	
	@GET
	@Path("/getAll/{userId}")
	public String getAllLists(@PathParam("userId") Long userId) {
		return this.serv.getAllLists(userId);
	}
	
	@POST
	@Path("/update/{listId}")
	public String updateList(@PathParam("listId") long listId, String listsTodo) {
		return this.serv.updateList(listId, listsTodo);
	}
	
	@DELETE
	@Path("/delete/{listId}")
	public String deleteList(@PathParam("listId") long listId) {
		return this.serv.deleteList(listId);
	}

}
