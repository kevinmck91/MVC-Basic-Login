package mvcController;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.User;

/**
 * Servlet implementation class MVCController
 */
public class MVCController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
    public MVCController() {
        super();
        // TODO Auto-generated constructor stub
    }

	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
	}

	public void destroy() {
		// TODO Auto-generated method stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	//-------DATABASE CONNECTION
		PrintWriter out = response.getWriter();
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
		
		} catch (ClassNotFoundException e) {
			out.print("cant load driver");
			e.printStackTrace();
		}
		
		Connection conn = null;
		
		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/myDatabase", "root", "Data326");} catch (SQLException e) {
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
		
		String action = request.getParameter("action");

		if (action == null) {
			request.getRequestDispatcher("/index.jsp").forward(request,
					response);
			
		} else if (action.equals("login")) {

			request.setAttribute("email", "");
			request.setAttribute("password", "");
			request.setAttribute("validationmessage", "");

			request.getRequestDispatcher("/login.jsp").forward(request,
					response);
		}
	}	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");

		if (action == null) {
			request.getRequestDispatcher("/index.jsp").forward(request,
					response);
		} else if (action.equals("dologin")) {
			String email = request.getParameter("email");
			String password = request.getParameter("password");

			request.setAttribute("email", email);
			request.setAttribute("password", password);

			User user = new User(email, password);
			
			if (user.validate()) {
				request.getRequestDispatcher("/loginsuccess.jsp").forward(
						request, response);
			} else {
				request.setAttribute("validationmessage", user.getMessage());
				request.getRequestDispatcher("/login.jsp").forward(request,
						response);
			}	
		}
	}
}
