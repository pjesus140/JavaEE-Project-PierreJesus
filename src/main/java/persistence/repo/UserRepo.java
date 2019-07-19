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
		return "Success for:"+this.gson.getObjectForJSON(user, User.class).getUsername();
	}
	
	@Transactional(value = TxType.SUPPORTS)
	public String CheckLogIn(String user) {
		TypedQuery<User> query = this.manager.createQuery("SELECT m FROM Movie m", User.class);
		return this.gson.getJSONForObject(query.getResultList());
	}
	
	public String updateUsers(long userId, String user) {
		User current = this.manager.find(User.class, userId);
		User newUser = this.gson.getObjectForJSON(user, User.class);
		current.setUsername(newUser.getUsername());
		this.manager.persist(current);
		return "Success for: "+ current.getUsername();
	}
	
	public String deleteUser(long userId) {
		this.manager.remove(this.manager.find(User.class, userId));
		return "Success for:"+this.manager.find(User.class, userId).getUsername();
	}
	

}
