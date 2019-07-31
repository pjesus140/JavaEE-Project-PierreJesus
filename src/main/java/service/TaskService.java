package service;

import javax.inject.Inject;

import persistence.domain.ListsTodo;
import persistence.domain.Tasks;
import persistence.repo.TaskRepo;
import util.JSONUtil;

public class TaskService {

	@Inject
	private JSONUtil gson;
	
	@Inject
	private TaskRepo repo;
	
	public String CreateTask(Long listId, String taskText) {
		Tasks aTask = this.gson.getObjectForJSON(taskText, Tasks.class);
		if(aTask.getTaskText().isEmpty()) {
			return "{\"Success\":\"False\"}";
		}
		
		return this.repo.CreateTask(listId,taskText);
		
	}
	
	public String getAllTasks(Long listId) {
		return this.repo.getAllTasks(listId);
	}
	
	public String updateTask(long taskId, String taskText) {

		return this.repo.updateTask(taskId, taskText);

	}
	
	
	public String deleteTask(long taskId) {
		return this.repo.deleteTask(taskId);
	}
}
