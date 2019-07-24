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
	public String checkLogIn(String user) {
		User aUser = this.gson.getObjectForJSON(user, User.class);
		String username = aUser.getUsername();
		String pass = aUser.getPass();
		TypedQuery<User> query = this.manager.createQuery(
				"SELECT u FROM User u WHERE username='" + username + "'AND pass='" + pass + "'", User.class);
		try {
			if (query == null) {
				return "{\"Success\":\"False\"}";
			} else {
				return "{\"Success\":\"True\",\"userId\":\"" + query.getResultList().get(0).getUserId() + "\"}";
			}
		} catch (IndexOutOfBoundsException iobe) {
			return "{\"Success\":\"False\"}";
		}

		// return this.gson.getJSONForObject(query.getResultList());

	}

	public boolean checkUsername(String user) {
		User aUser = this.gson.getObjectForJSON(user, User.class);
		String username = aUser.getUsername();
		TypedQuery<User> query = this.manager.createQuery("SELECT u FROM User u WHERE username='" + username + "'",
				User.class);

		if (query.getResultList().isEmpty()) {
			return true;
		} else {
			return false;
		}

	}
	
	

	public String updateUsers(long userId, String user) {
		User current = this.manager.find(User.class, userId);
		User newUser = this.gson.getObjectForJSON(user, User.class);
		current.setUsername(newUser.getUsername());
		current.setPass(newUser.getPass());
		this.manager.persist(current);
		return "Success for: " + current.getUsername();
	}

	public String deleteUser(long userId,String userPass) {
		User current = this.manager.find(User.class, userId);
		if(current.getPass().equals(this.gson.getObjectForJSON(userPass, User.class).getPass())) {
			
			this.manager.remove(this.manager.find(User.class, userId));
			return "{\"Success\":\"True\"}";
		}
		return "{\"Success\":\"False\"}";
	}

}
