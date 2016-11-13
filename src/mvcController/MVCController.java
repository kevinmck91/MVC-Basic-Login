package mvcController;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import database.Account;
import beans.User;

public class MVCController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private DataSource ds;

	public MVCController() {
		super();
	}

	public void init(ServletConfig config) throws ServletException {
		try {
			InitialContext initContext = new InitialContext();
			Context env = (Context)initContext.lookup("java:comp/env");

			ds = (DataSource)env.lookup("jdbc/login");

		} catch (NamingException e) {
			throw new ServletException();
		}
	}

	public void destroy() {

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//-------JDBC DATABASE CONNECTION
		/*		
  		PrintWriter out = response.getWriter();

		try {
			Class.forName("com.mysql.jdbc.Driver");

		} catch (ClassNotFoundException e) {
			out.print("cant load driver");
			e.printStackTrace();
		}

		Connection conn = null;

		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/myDatabase", "root", "Data2326");} catch (SQLException e) {
			out.print("cant connect to data base");
			return;
		}

		out.print("connected to database");

		try {
			conn.close();
		} catch (SQLException e) {
			out.print("cant close data base");
			return;
		}
		 */

		String action = request.getParameter("action");

		if (action == null) {
			request.getRequestDispatcher("/index.jsp").forward(request,
					response);

		} else if (action.equals("login")) {

			request.setAttribute("email", "");
			request.setAttribute("password", "");
			request.setAttribute("validationmessage", "");
			request.setAttribute("message", "");

			request.getRequestDispatcher("/login.jsp").forward(request,
					response);
		}


	}	

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");

		//-------JNDI DATABASE CONNECTION OPEN

		Connection conn = null;
		PrintWriter out = response.getWriter();

		try {
			conn = ds.getConnection();
		} catch (SQLException e) {
			out.println("Connection Failure");
			e.printStackTrace();
			return;
		}

		out.println("Connection Succesful");
		
	//////	Action Controller

		if (action == null) {
			request.getRequestDispatcher("/index.jsp").forward(request,response);

		} 
		else if (action.equals("dologin")) {
			String email = request.getParameter("email");
			String password = request.getParameter("password");
			String message = request.getParameter("message");

			request.setAttribute("email", email);
			request.setAttribute("password", password);

			User user = new User(email, password);
			
			Account account = new Account(conn);
			
			if(account.login(email, password)){
				request.getRequestDispatcher("/loginsuccess.jsp").forward(request, response);
			}else{
				request.setAttribute("message",  Account.getMessage());
				request.getRequestDispatcher("/login.jsp").forward(request,response);
			}
			
			/*
			if (user.validate()) {
				request.getRequestDispatcher("/loginsuccess.jsp").forward(request, response);
			} else {
				request.setAttribute("validationmessage", user.getMessage());
				request.getRequestDispatcher("/login.jsp").forward(request,response);
			}
			*/
		}


	//-------JNDI DATABASE CONNECTION CLOSE
		try {
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	

	}
}
