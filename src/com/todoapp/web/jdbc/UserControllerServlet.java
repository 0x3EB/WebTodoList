package com.todoapp.web.jdbc;

import java.io.IOException;
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
	
	@Resource(name="jdbc/studentdb")
	private DataSource dataSource;
	

	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		super.init();
		tododbutil = new TododbUtil(dataSource);
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			display(request,response);
			} catch (Exception e) {
			// TODO Auto-generated catch block
				response.getWriter().println("Pas d'utilisateur en session, ou probleme dans user.jsp 'The type Class is ambiguous'");
			}		
	}
	
	private void display(HttpServletRequest request, HttpServletResponse response) throws Exception{
//			HttpSession session = request.getSession();
//			User user = (User) session.getAttribute("user");
//			if (user.getIdrole().getLibelle()=="student") {
//				List<Todo> todos = user.getIdclass().getTodos();
//				request.setAttribute("todos", todos);
//				request.setAttribute("role", user.getIdrole().getLibelle());
//			}
//			else if(user.getIdrole().getLibelle()=="instructor") {
//				List<Class> classes = user.getClasses();
//				request.setAttribute("classes", classes);
//				request.setAttribute("role", user.getIdrole().getLibelle());
//			}
			request.getRequestDispatcher("/user.jsp").forward(request, response);
			}


}
