package service;

import javax.inject.Inject;

import persistence.repo.UserRepo;

public class UserService {

	@Inject
	private UserRepo repo;

	public String createUser(String user) {
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
		if (this.repo.checkUsername(user) == true) {
			return this.repo.updateUsername(userId, user);

		} else {
			return "{\"Success\":\"False\"}";
		}

	}

	public String updatePass(long userId, String user) {

		return this.repo.updatePass(userId, user);

	}

	public String deleteUser(long userId) {
		return this.repo.deleteUser(userId);
	}

}
