package service;

import javax.inject.Inject;


import persistence.repo.TaskRepo;

public class TaskService {

	
	@Inject
	private TaskRepo repo;
	
	public String CreateTask(Long listId, String taskText) {
		
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
