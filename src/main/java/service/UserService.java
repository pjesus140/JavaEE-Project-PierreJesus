package service;

import javax.inject.Inject;

import persistence.domain.User;
import persistence.repo.UserRepo;
import util.JSONUtil;

public class UserService {
	
	@Inject
	private JSONUtil gson;

	@Inject
	private UserRepo repo;

	public String createUser(String user) {
		User aUser = this.gson.getObjectForJSON(user, User.class);
		if(aUser.getUsername().isEmpty()||aUser.getPass().isEmpty()) {
			return "{\"Success\":\"False\"}";
		}
		if (this.repo.checkUsername(user) == true) {
			return this.repo.createUser(user);

		} else {

			return "{\"Success\":\"False\"}";
		}
	}

	public String checkLogIn(String user) {

		return this.repo.checkLogIn(user);
	}

	public String CheckPass(long userId, String userPass) {

		return this.repo.checkPass(userId, userPass);
	}

	public String updateUsername(long userId, String user) {
		User aUser = this.gson.getObjectForJSON(user, User.class);
		if(aUser.getUsername().isEmpty()) {
			return "{\"Success\":\"False\"}";
		}
		if (this.repo.checkUsername(user) == true) {
			return this.repo.updateUsername(userId, user);

		} else {
			return "{\"Success\":\"False\"}";
		}

	}

	public String updatePass(long userId, String user) {
		User aUser = this.gson.getObjectForJSON(user, User.class);
		if(aUser.getPass().isEmpty()) {
			return "{\"Success\":\"False\"}";
		}

		return this.repo.updatePass(userId, user);

	}

	public String deleteUser(long userId) {
		return this.repo.deleteUser(userId);
	}

}
