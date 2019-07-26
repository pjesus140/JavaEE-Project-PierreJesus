package persistence.repo;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

import persistence.domain.ListsTodo;
import persistence.domain.Tasks;
import util.JSONUtil;



@Transactional(value = TxType.REQUIRED)
public class TaskRepo {
	
	
	@PersistenceContext(unitName = "primary")
	private EntityManager manager;

	@Inject
	private JSONUtil gson;
	
	public String CreateTask(Long listId, String taskText) {
		ListsTodo taskOwner = this.manager.find(ListsTodo.class, listId);
		Tasks newTask = this.gson.getObjectForJSON(taskText, Tasks.class);
		newTask.setListsTodo(taskOwner);
		this.manager.persist(newTask);		
		return "{\"Success\":\"True\"}";
	}
	
	@Transactional(value = TxType.SUPPORTS)
	public String getAllTasks() {
		TypedQuery<Tasks> query = this.manager.createQuery("SELECT t FROM Tasks t", Tasks.class);
		return this.gson.getJSONForObject(query.getResultList());
	}
	
	public String updateTask(long taskId, String taskText) {
		Tasks current = this.manager.find(Tasks.class, taskId);
		Tasks newTask = this.gson.getObjectForJSON(taskText, Tasks.class);
		current.setTaskText(newTask.getTaskText());
		
		this.manager.persist(current);
		return "Success for new text: "+ current.getTaskText();
	}
	
	public String deleteTask(long taskId) {
		this.manager.remove(this.manager.find(Tasks.class, taskId));
		return "Deleted Task";
	}
	
	
	
	
	

}
