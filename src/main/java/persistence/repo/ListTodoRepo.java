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
	
	@Inject
	private UserRepo repo;

	public String CreateList(Long userId,String listsTodo) {
		
		ListsTodo newList = this.gson.getObjectForJSON(listsTodo, ListsTodo.class);
		newList.setUser(userId);
		this.manager.persist(newList);
		
		return "Success for:" + this.gson.getObjectForJSON(listsTodo, ListsTodo.class).getListName();
	}

	@Transactional(value = TxType.SUPPORTS)
	public String getAllLists() {
		TypedQuery<ListsTodo> query = this.manager.createQuery("SELECT l FROM ListsTodo l", ListsTodo.class);
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
