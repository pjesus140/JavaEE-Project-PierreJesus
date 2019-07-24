package service;

import javax.inject.Inject;

import persistence.repo.UserRepo;

public class UserService {
	
	@Inject
	private UserRepo repo;
	
	public String createUser(String user) {
		
		return this.repo.createUser(user);
	}
	
	public String CheckLogIn(String user) {
		return this.repo.CheckLogIn(user);
	}
	
	public String updateUsers(long userId, String user) {
		
		return this.repo.updateUsers(userId, user);
		
	}
	
	public String deleteUser(long userId) {
		return this.repo.deleteUser(userId);
	}

}
