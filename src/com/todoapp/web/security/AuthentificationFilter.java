package com.todoapp.web.security;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Servlet Filter implementation class AuthentificationFilter
 */
@WebFilter("/*")
public class AuthentificationFilter implements Filter {
	private HttpServletRequest httpRequest;

	private static final String[] loginRequiredURLs = { "/UserControllerServlet", "/EditTodoControllerServlet" };

	/**
	 * Default constructor.
	 */
	public AuthentificationFilter() {
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
		httpRequest = (HttpServletRequest) request;
		String path = httpRequest.getRequestURI().substring(httpRequest.getContextPath().length());

		if (path.startsWith("/admin/")) {
			chain.doFilter(request, response);
			return;
		}
		HttpSession session = httpRequest.getSession(false);

		boolean isLoggedIn = (session != null && session.getAttribute("user") != null);

		String loginURI = httpRequest.getContextPath() + "/";
		boolean isLoginPage1 = httpRequest.getRequestURI().endsWith("WebTodoList");
		boolean isLoginPage2 = httpRequest.getRequestURI().endsWith("LoginControllerServlet");

		if (isLoggedIn && (isLoginPage1 || isLoginPage2)) {
			httpRequest.getRequestDispatcher("UserControllerServlet").forward(request, response);

		} else if (!isLoggedIn && isLoginRequired()) {
			// the user is not logged in, and the requested page requires
			// authentication, then forward to the login page
			String loginPage = "/";
			RequestDispatcher dispatcher = httpRequest.getRequestDispatcher(loginPage);
			dispatcher.forward(request, response);
		} else {
			chain.doFilter(request, response);
		}
	}

	private boolean isLoginRequired() {
		String requestURL = httpRequest.getRequestURL().toString();

		for (String loginRequiredURL : loginRequiredURLs) {
			if (requestURL.contains(loginRequiredURL)) {
				return true;
			}
		}

		return false;
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
