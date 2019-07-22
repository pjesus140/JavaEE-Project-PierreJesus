package persistence.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Tasks {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long taskId;
	private String taskText;
	
	
	@ManyToOne
	private ListsTodo listsTodo;
	
	public Tasks(long taskId, String taskText, Long listId) {
		super();
		this.taskId = taskId;
		this.taskText = taskText;
		

	}

	public long getTaskId() {
		return taskId;
	}

	public void setTaskId(long taskId) {
		this.taskId = taskId;
	}

	public String getTaskText() {
		return taskText;
	}

	public void setTaskText(String taskText) {
		this.taskText = taskText;
	}

	public ListsTodo getListsTodo() {
		return listsTodo;
	}

	public void setListsTodo(ListsTodo listsTodo) {
		this.listsTodo = listsTodo;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
