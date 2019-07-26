package persistence.repo;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

import persistence.domain.ListsTodo;
import persistence.domain.User;
import util.JSONUtil;

@Transactional(value = TxType.REQUIRED)
public class ListTodoRepo {

	@PersistenceContext(unitName = "primary")
	private EntityManager manager;

	@Inject
	private JSONUtil gson;
	
	

	public String CreateList(Long userId,String listsTodo) {
		User listOwner = this.manager.find(User.class, userId);
		ListsTodo newList = this.gson.getObjectForJSON(listsTodo, ListsTodo.class);
		newList.setUser(listOwner);
		this.manager.persist(newList);
		
		return "{\"Success\":\"True\"}";
	}

	@Transactional(value = TxType.SUPPORTS)
	public String getAllLists(Long userId) {
		TypedQuery<ListsTodo> query = this.manager.createQuery("SELECT l FROM ListsTodo l WHERE User_userId='" + userId + "'", ListsTodo.class);
		return this.gson.getJSONForObject(query.getResultList());
	}
	
	
	public String updateList(long listId, String listsTodo) {
		ListsTodo current = this.manager.find(ListsTodo.class, listId);
		ListsTodo newList = this.gson.getObjectForJSON(listsTodo, ListsTodo.class);
		current.setListName(newList.getListName());
		
		this.manager.persist(current);
		return "Success for: "+ current.getListName();
	}
	
	public String deleteList(long listId) {
		this.manager.remove(this.manager.find(ListsTodo.class, listId));
		return "Deleted List";
	}

}
