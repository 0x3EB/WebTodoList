package com.todoapp.web.controller;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.sql.SQLException;

import javax.annotation.Resource;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import com.todoapp.web.entities.User;
import com.todoapp.web.jdbc.TododbUtil;

/**
 * Servlet implementation class doneTodoControllerServlet
 */
@WebServlet("/doneTodoControllerServlet")
public class doneTodoControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private TododbUtil tododbutil;
	private String id;

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
		id = request.getParameter("todoId");
		HttpSession session = request.getSession();
		try {
			tododbutil.addTodoDone(id, (User) session.getAttribute("user"),
					(PrivateKey) session.getAttribute("privatekey"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		response.sendRedirect("UserControllerServlet");
	}

}
