package com.birzha.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import database.dao.FactoryDao;
import database.dao.UserDao;
import database.impl.FactoryDaoImpl;

/**
 Servlet for verifying entered user login and password 
 */
@WebServlet(name = "LoginServlet", urlPatterns = { "/login" })
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserDao user = null;
	FactoryDao factory = new FactoryDaoImpl();
	private ServletConfig config;  
	private ServletContext sc;
	private static final int LOGIN_PASS_OK=1;
	private static final int ONLY_LOGIN_OK=0;
	private static final int LOGIN_WRONG=-1;
	private static final Logger logger=Logger.getLogger(LoginServlet.class);
		
    public void init(ServletConfig config) throws ServletException {
      	this.config = config;  
		sc = config.getServletContext();
	}
    
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html; charset=cp1251");
     	user = factory.getUserDao();
		String password = req.getParameter("password");
		String errorMsg = null;
		String email = req.getParameter("email");
		String ip = req.getRemoteHost();
		
		if(password == null || password.equals("")){
	            errorMsg = "���� ������ �� ����� ���� ������";
	        }
		
		if(email == null || password.equals("")){
            errorMsg = "���� ����� �� ����� ���� ������";
        }
	
	    if(errorMsg != null){
	            RequestDispatcher rd = sc.getRequestDispatcher("/login.jsp");
	            PrintWriter out= resp.getWriter();
	            out.println("<font color=red>"+errorMsg+"</font>");
	            rd.include(req, resp);
	        }
	    else{
			   if (user.auth(email, password)==LOGIN_PASS_OK){
				   logger.info("User entered: "+email);
				   String ipaddress = user.getIP(email, ip);
				   HttpSession session = req.getSession();
				   session.setAttribute("email", email);
				   session.setMaxInactiveInterval(30*60);		          
		           resp.sendRedirect("personalpage.jsp");
			   }
			   else if (user.auth(email, password) == ONLY_LOGIN_OK) {
				   logger.error(email + ": wrong password");
				   RequestDispatcher rd = sc.getRequestDispatcher("/login.jsp");
		           PrintWriter out = resp.getWriter();
		           out.println("<font color=red>������ ��������.</font>");
		           rd.include(req, resp);
			   }
			   else if (user.auth(email, password) == LOGIN_WRONG) {
				   logger.error("not found: "+email);
				   RequestDispatcher rd = sc.getRequestDispatcher("/login.jsp");
		           PrintWriter out = resp.getWriter();
		           out.println("<font color=red>������ ������ �� ����������.</font>");
		           rd.include(req, resp);
			   }
	        }
	}
	
	

}
