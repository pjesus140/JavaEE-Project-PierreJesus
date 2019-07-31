package persistence.repo;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

import persistence.domain.ListsTodo;
import persistence.domain.Tasks;
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
				return "{\"Success\":\"True\",\"userId\":\"" + query.getResultList().get(0).getUserId() + "\",\"username\":\"" + query.getResultList().get(0).getUsername()+"\"}";
			}
		} catch (IndexOutOfBoundsException iobe) {
			return "{\"Success\":\"False\"}";
		}

		// return this.gson.getJSONForObject(query.getResultList());

	}

	public boolean checkUsername(String user) {
		User aUser = this.gson.getObjectForJSON(user, User.class);
		String username = aUser.getUsername();
		if(username.isEmpty()) {
			return false;
		}
		TypedQuery<User> query = this.manager.createQuery("SELECT u FROM User u WHERE username='" + username + "'",
				User.class);

		if (query.getResultList().isEmpty()) {
			return true;
		} else {
			return false;
		}

	}

	public String checkPass(long userId, String userPass) {

		User current = this.manager.find(User.class, userId);

		if (current.getPass().equals(this.gson.getObjectForJSON(userPass, User.class).getPass())) {
			return "{\"Success\":\"True\"}";
		} else {
			return "{\"Success\":\"False\"}";

		}

	}

	public String updateUsername(long userId, String user) {
		User current = this.manager.find(User.class, userId);
		User newUser = this.gson.getObjectForJSON(user, User.class);
		current.setUsername(newUser.getUsername());
		this.manager.persist(current);
		return "{\"Success\":\"True\"}";
	}

	public String updatePass(long userId, String user) {
		User current = this.manager.find(User.class, userId);
		User newUser = this.gson.getObjectForJSON(user, User.class);

		current.setPass(newUser.getPass());
		this.manager.persist(current);
		return "{\"Success\":\"True\"}";
	}

	public String deleteUser(long userId) {
		int listId = 0;
		TypedQuery<Tasks> queryTasks = this.manager.createQuery("SELECT t FROM Tasks t WHERE t.listsTodo.user.userId='" + userId + "'", Tasks.class);

		List<Tasks> queryTasksList = queryTasks.getResultList();
		for (Tasks x : queryTasksList) {
			this.manager.remove(this.manager.find(Tasks.class, x.getTaskId()));
		}
		
		TypedQuery<ListsTodo> query = this.manager.createQuery("SELECT l FROM ListsTodo l WHERE User_userId='" + userId + "'", ListsTodo.class);
		
		List<ListsTodo> queryList = query.getResultList();
		for(ListsTodo x: queryList) {
			this.manager.remove(this.manager.find(ListsTodo.class,x.getListId()));
		}
		this.manager.remove(this.manager.find(User.class, userId));
		return "{\"Success\":\"True\"}";

	}

}
