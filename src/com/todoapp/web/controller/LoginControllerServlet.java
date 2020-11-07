package com.todoapp.web.controller;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import com.todoapp.web.entities.User;
import com.todoapp.web.jdbc.TododbUtil;
import com.todoapp.web.security.Keys;
import com.todoapp.web.security.Security;

/**
 * Servlet implementation class LoginControllerServlet
 */
@WebServlet("/LoginControllerServlet")
public class LoginControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private TododbUtil tododbutil;

	@Resource(name = "jdbc/todolist")
	private DataSource dataSource;

	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		super.init();
		tododbutil = new TododbUtil(dataSource);
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// get the last displayUsername variable in the cookie to set in username label
		// of the login form
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals("displayUsername")) {
					request.setAttribute("displayUsername", cookie.getValue());
				}
			}
		}
		request.getRequestDispatcher("/signin.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		try {
			Keys keys = new Keys(); // generate key pair at each connection to have a best security aspect
			Security.storeKeysSession(session, keys); // store the key pair in session
		} catch (NoSuchAlgorithmException e) {
			System.err.println("erreur clefs");
			e.printStackTrace();
		}
		String password = null;
		try {
			password = Security.sha1(request.getParameter("password")); // pass to the SHA1 algorithm the password
																		// written in the label form to compare it after
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		System.out.println(password);
		// check if the user exists in base
		if (tododbutil.checkUser(request.getParameter("usernameOrEmail"), password)) {
			tododbutil.getUser(request.getParameter("usernameOrEmail"), password,
					(PublicKey) session.getAttribute("publicKey"));
			User user = tododbutil.getUser(request.getParameter("usernameOrEmail"), password,
					(PublicKey) session.getAttribute("publicKey"));
			Cookie cookie = new Cookie("displayUsername", request.getParameter("usernameOrEmail"));
			cookie.setMaxAge(60 * 60 * 24 * 30); // set cookie age 1 year
			response.addCookie(cookie);
			Security.storeLoggedUser(request.getSession(), user); // store the user in session
			response.sendRedirect(request.getContextPath() + "/UserControllerServlet");
		} else {
			// display error message
			request.setAttribute("errorMessage", "Invalid user or password");
			request.getRequestDispatcher("/signin.jsp").forward(request, response);
		}
	}

}
