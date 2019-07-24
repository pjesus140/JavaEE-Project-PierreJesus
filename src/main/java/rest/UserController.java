package rest;

import javax.inject.Inject;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import service.UserService;

@Path("/Users")
public class UserController {
	
	@Inject
	private UserService serv;
	
	@POST
	@Path("/create")
	public String createUser(String user) {
		
		return this.serv.createUser(user);
	}
	
	@POST
	@Path("/Check")
	public String checkLogIn(String user) {
		return this.serv.checkLogIn(user);
	}
	
	@POST
	@Path("/update/{userId}")
	public String updateUsers(@PathParam("userId") long userId, String user) {
		return this.serv.updateUsers(userId, user);
	}
	
	@DELETE
	@Path("/delete/{userId}")
	public String deleteUser(@PathParam("userId") long userId,String userPass) {
		return this.serv.deleteUser(userId,userPass);
	}
	

}
