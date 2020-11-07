package com.todoapp.web.security;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.todoapp.web.entities.Role;
import com.todoapp.web.entities.User;

/**
 * Servlet Filter implementation class AclFilter
 */
@WebFilter("/*")
public class AclFilter implements Filter {
	private HttpServletRequest httpRequest;

	// List of Urls depending of the privileges
	private static final String[] InstructorURLs = { "/UserControllerServlet", "/EditTodoControllerServlet",
			"/ClassroomControllerServlet", "/editClassroomController" };
	private static final String[] StudentURLs = { "/UserControllerServlet", "/DoneTodoControllerServlet",
			"/AddDoneTodoControllerServlet" };

	/**
	 * Default constructor.
	 */
	public AclFilter() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		User u = null;
		httpRequest = (HttpServletRequest) request;
		String path = httpRequest.getRequestURI().substring(httpRequest.getContextPath().length());
		if (path.startsWith("/admin/")) {
			chain.doFilter(request, response);
			return;
		}
		HttpSession session = httpRequest.getSession(false);
		boolean isLoggedIn = (session != null && session.getAttribute("user") != null);
		try {
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}

		if (isLoggedIn) {
			u = (User) session.getAttribute("user");
			System.out.println(u.getIdrole().getLibelle().toUpperCase());
			boolean isStudent = u.getIdrole().getLibelle().toUpperCase().equals(Role.STUDENT);
			System.out.println(isStudent);
			if (isStudent && isInstructorRoleRequired())
				httpRequest.getRequestDispatcher("UserControllerServlet").forward(request, response);
			else if (!isStudent && isStudentRoleRequired())
				httpRequest.getRequestDispatcher("UserControllerServlet").forward(request, response);
			else
				chain.doFilter(request, response);
		} else {
			chain.doFilter(request, response);
		}
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

	/**
	 * Function that check if the url need to have a instructor privilege
	 * 
	 * @return
	 */
	private boolean isInstructorRoleRequired() {
		String requestURL = httpRequest.getRequestURL().toString();
		for (String loginRequiredURL : InstructorURLs) {
			if (requestURL.contains(loginRequiredURL)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Function that check if the url need to have a student privilege
	 * 
	 * @return
	 */
	private boolean isStudentRoleRequired() {
		String requestURL = httpRequest.getRequestURL().toString();
		for (String loginRequiredURL : StudentURLs) {
			if (requestURL.contains(loginRequiredURL)) {
				return true;
			}
		}
		return false;
	}

}
