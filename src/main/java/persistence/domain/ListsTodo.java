package persistence.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class ListsTodo {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long listId;	
	private String listName;
	
	
	@ManyToOne
	private User user;
	
	
	public ListsTodo(long listId, String listName, User user) {
		super();
		this.listId = listId;
		this.listName = listName;
		this.user = user;
	}
	
	public ListsTodo() {
		
	}
	
	public long getListId() {
		return listId;
	}
	public void setListId(long listId) {
		this.listId = listId;
	}
	public String getListName() {
		return listName;
	}
	public void setListName(String listName) {
		this.listName = listName;
	}

	
	
	

}
