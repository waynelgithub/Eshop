package main.helper;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;

public class UserHelper {
	
	
	/**
	 * for every role, check if the user has it
	 * @param roleStrings a Collection of role full names 
	 * @param request a HttpServletRequest from current user
	 * @return
	 * true: has one of these roles;
	 */
	public static boolean hasAnyManagementRoleByHttpServletRequest(Collection<String> roleStrings, HttpServletRequest request) {
		// return false if the user has not been authenticated
		if (request.getUserPrincipal().equals(null)) return false;

		//1. for every role, check if the user has it
//		boolean hasRole = false;
//		for(String roleString : roleStrings) {			
//			hasRole = request.isUserInRole(roleString);
//			if (hasRole) break;			
//		}


		//2. for every role, check if the user has it
		//use stream() and anyMatch() to reach break in a loop
		boolean hasRole = roleStrings.stream().anyMatch(role -> request.isUserInRole(role));
		
		return hasRole;		

	}

}
