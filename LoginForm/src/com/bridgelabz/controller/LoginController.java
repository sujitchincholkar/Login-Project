package com.bridgelabz.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import com.bridgelabz.DAO.UserDao;
import com.bridgelabz.model.User;

/**
 * Servlet implementation class LoginController
 */
//@WebServlet("/LoginC")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	static  Logger logger=Logger.getLogger(LoginController.class);
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		response.setContentType("text/html");
		String email=request.getParameter("email");
		String password=request.getParameter("password");
		User user=UserDao.getUser(email, password);
		System.out.println("In the login section");
		if(user==null){
			logger.info("log in failed");
			HttpSession session=request.getSession();
			session.setAttribute("error", "Invalid username or password");
			response.sendRedirect("Login");
		}else{
			logger.info("log in successful");
			HttpSession session=request.getSession();
			session.setAttribute("id", user.getId());
			session.setAttribute("name",user.getName());
			response.sendRedirect(response.encodeRedirectURL("Home"));

			//request.getRequestDispatcher("/Home.jsp").forward(request,response);
		
		}
		
	}

}
