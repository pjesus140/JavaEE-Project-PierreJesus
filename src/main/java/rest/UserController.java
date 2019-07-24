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
	@Path("/updateName/{userId}")
	public String updateUsername(@PathParam("userId") long userId, String user) {
		return this.serv.updateUsername(userId, user);
	}
	
	@POST
	@Path("/updatePass/{userId}")
	public String updatePass(@PathParam("userId") long userId, String user) {
		return this.serv.updatePass(userId, user);
	}
	
	@POST
	@Path("/CheckDel/{userId}")
	public String CheckPass(@PathParam("userId") long userId, String userPass) {
		return this.serv.CheckPass(userId, userPass);
	}
	
	@DELETE
	@Path("/delete/{userId}")
	public String deleteUser(@PathParam("userId") long userId) {
		return this.serv.deleteUser(userId);
	}
	

}
