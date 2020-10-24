package com.todoapp.web.security;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import com.todoapp.web.entities.Role;

public class WrapperRequest extends HttpServletRequestWrapper {
	private String user;
	private List<Role> roles = null;
	private HttpServletRequest realRequest;

	public WrapperRequest(String user, List<Role> roles2, HttpServletRequest request) {
		super(request);
		this.user = user;
		this.roles = roles2;
		this.realRequest = request;
	}

	@Override
	public boolean isUserInRole(String role) {
		if (roles == null) {
			return this.realRequest.isUserInRole(role);
		}
		return roles.contains(role);
	}

	@Override
	public Principal getUserPrincipal() {
		if (this.user == null) {
			return realRequest.getUserPrincipal();
		}

		// Make an anonymous implementation to just return our user
		return new Principal() {
			@Override
			public String getName() {
				return user;
			}
		};
	}
}
