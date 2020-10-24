package com.todoapp.web.security;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class SecurityConfig {
	public static final String ROLE_STUDENT = "STUDENT";
	public static final String ROLE_INSTRUCTOR = "INSTRUCTOR";

	// String: Role
	// List<String>: urlPatterns.
	private static final Map<String, List<String>> mapConfig = new HashMap<String, List<String>>();

	static {
		init();
	}

	private static void init() {

		// Configure For "ROLE_STUDENT" Role.
		List<String> urlPatterns1 = new ArrayList<String>();

		urlPatterns1.add("/userInfo");

		mapConfig.put(ROLE_STUDENT, urlPatterns1);

		// Configure For "ROLE_INSTRUCTOR" Role.
		List<String> urlPatterns2 = new ArrayList<String>();

		urlPatterns2.add("/EditTodoControllerServlet");
		urlPatterns2.add("/DeleteTodoControllerServlet");
		urlPatterns2.add("/ClassroomControllerServlet");

		mapConfig.put(ROLE_INSTRUCTOR, urlPatterns2);
	}

	public static Set<String> getAllAppRoles() {
		return mapConfig.keySet();
	}

	public static List<String> getUrlPatternsForRole(String role) {
		return mapConfig.get(role);
	}
}
