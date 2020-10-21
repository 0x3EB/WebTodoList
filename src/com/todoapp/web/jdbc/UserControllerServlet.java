package com.todoapp.web.jdbc;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

/**
 * Servlet implementation class UserControllerServlet
 */
@WebServlet("/UserControllerServlet")
public class UserControllerServlet extends HttpServlet {
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
		try {
			HttpSession session = request.getSession();
			User u = (User) session.getAttribute("user");
			List<Todo> lst = new ArrayList<Todo>();
			String role = null;
			if (u.getIdrole().getLibelle().toUpperCase().equals(Role.INSTRUCTOR)) {
				lst = tododbutil.getInstructorTodo(u);
				role="instructor";
			} else if (u.getIdrole().getLibelle().toUpperCase().equals(Role.STUDENT)) {
				lst = tododbutil.getStudentTodo(u);
				role="student";
			}
			request.setAttribute("TODO_LIST", lst);
			request.setAttribute("role", role);
			request.setAttribute("name", u.getUsername());
			request.getRequestDispatcher("/user.jsp").forward(request, response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
			response.getWriter()
					.println("Pas d'utilisateur en session, ou probleme dans user.jsp 'The type Class is ambiguous'");
		}
	}
}
