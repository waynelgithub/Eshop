package main.service;

import main.model.Role;

public interface RoleService {
	
	public Role findRoleByLogin(String login);

}
