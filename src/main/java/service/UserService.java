package service;

import javax.inject.Inject;

import persistence.repo.UserRepo;

public class UserService {

	@Inject
	private UserRepo repo;

	public String createUser(String user) {
		if (this.repo.checkUsername(user)== true) {
			return this.repo.createUser(user);

		} else {

			return "{\"Success\":\"False\"}";
		}
	}

	public String checkLogIn(String user) {

		return this.repo.checkLogIn(user);
	}

	public String updateUsers(long userId, String user) {

		return this.repo.updateUsers(userId, user);

	}

	public String deleteUser(long userId) {
		return this.repo.deleteUser(userId);
	}

}
