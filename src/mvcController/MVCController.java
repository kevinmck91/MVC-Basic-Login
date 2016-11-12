package mvcController;

import java.io.IOException;

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
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MVCController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Servlet#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
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
