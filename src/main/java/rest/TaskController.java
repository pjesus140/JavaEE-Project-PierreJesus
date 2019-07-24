package rest;

import javax.inject.Inject;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import service.TaskService;


@Path("/Tasks")
public class TaskController {

	@Inject
	private TaskService serv;
	
	
	@POST
	@Path("/create/{listId}")
	public String CreateTask(@PathParam("listId")Long listId, String taskText) {

		return this.serv.CreateTask(listId, taskText);

	}
	
	@GET
	@Path("/getAll")
	public String getAllTasks() {
		return this.serv.getAllTasks();
	}
	
	@POST
	@Path("/update/{taskId}")
	public String updateTask(@PathParam("taskId")long taskId, String taskText) {

		return this.serv.updateTask(taskId, taskText);

	}
	
	
	@DELETE
	@Path("/delete/{taskId}")
	public String deleteTask(@PathParam("taskId")long taskId) {
		return this.serv.deleteTask(taskId);
	}
	

}
