package main.service;

import main.model.Tour;
import main.model.User;

public interface UserService {

	public void createNewAccount(User user);
	
	public boolean loginExists(String login);
	
	public User getById(long id);
}
