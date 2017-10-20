package com.bridgelabz.controller;

import java.io.IOException;
import java.io.PrintWriter;

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
 * Servlet implementation class SignUpController
 */
//@WebServlet("/SignUp")
public class SignUpController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	static  Logger logger=Logger.getLogger(SignUpController.class);
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SignUpController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		User user=new User();
		PrintWriter out =response.getWriter();
		response.setContentType("text/html");
		
		user.setEmail(request.getParameter("email"));
		user.setPassword(request.getParameter("password"));
		user.setMobileno(request.getParameter("mobileno"));
		user.setName(request.getParameter("username"));
		if(Validator.signUpValidate(user)){
		int status=UserDao.insertUser(user);
		if(status>0){
			logger.info("Log in Successful");
			out.println("Signup successful!");
			response.sendRedirect("Login");
		}else{
			logger.warn("Signup .failed");
			out.println("ReEnter data some error occured");
			request.getRequestDispatcher("Signup.html").include(request,response);
		}
		}else{
			out.println("<font color='red'>Enter valid data</font>");
			request.getRequestDispatcher("Signup.html").include(request,response);
		}
	}

}
