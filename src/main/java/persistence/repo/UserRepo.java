package persistence.repo;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;


import persistence.domain.User;
import util.JSONUtil;

@Transactional(value = TxType.REQUIRED)
public class UserRepo {
	
	@PersistenceContext(unitName = "primary")
	private EntityManager manager;
	
	@Inject
	private JSONUtil gson;
	
	public String createUser(String user) {
		this.manager.persist(this.gson.getObjectForJSON(user, User.class));
		return "{\"Success\":\"True\"}";
	}
	
	@Transactional(value = TxType.SUPPORTS)
	public String CheckLogIn(String user) {
		
		TypedQuery<User> query = this.manager.createQuery("SELECT u FROM User u", User.class);
//		return this.gson.getJSONForObject(query.getResultList());
		if(query == null) {
			return "{\"Success\":\"Fail\"}";
		}else
			return "{\"Success\":\"True\"}";
			
	}
	
	public String updateUsers(long userId, String user) {
		User current = this.manager.find(User.class, userId);
		User newUser = this.gson.getObjectForJSON(user, User.class);
		current.setUsername(newUser.getUsername());
		current.setPass(newUser.getPass());
		this.manager.persist(current);
		return "Success for: "+ current.getUsername();
	}
	
	public String deleteUser(long userId) {
		this.manager.remove(this.manager.find(User.class, userId));
		return "Deleted User";
	}
	

}
