package persistence.repo;

public interface UserInterface {
	
	final String SUCCESS = "{\"Success\":\"True\"}";
	final String FAILURE = "{\"Success\":\"False\"}";
	
	String createUser(String user);
	String checkLogIn(String user);
	boolean checkUsername(String user);
	String checkPass(long userId, String userPass);
	String updateUsername(long userId, String user);
	String updatePass(long userId, String user);
	String deleteUser(long userId);
	
	
}
