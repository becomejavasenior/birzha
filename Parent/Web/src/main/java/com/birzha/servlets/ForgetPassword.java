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

import org.apache.log4j.Logger;

import database.dao.FactoryDao;
import database.dao.UserDao;
import database.impl.FactoryDaoImpl;
import database.model.User;

@WebServlet(name = "ForgetPassword", urlPatterns = { "/forgetpassword" })
public class ForgetPassword extends HttpServlet {
	private UserDao userdao = null;
	private User user = new User();
	FactoryDao factory = new FactoryDaoImpl();;
	private ServletConfig config;  
	private ServletContext sc;
	static Logger logger=Logger.getLogger(RegistrationServlet.class);

    
	public void init(ServletConfig config) throws ServletException {
		 this.config = config;  
		 sc = config.getServletContext();
		}
	 
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	response.setContentType("text/html; charset=cp1251");
    	userdao = factory.getUserDao();
    	String email = request.getParameter("emailforpass");
      	
    	if(userdao.getPasswordByEmail(email)!=null){
            //send mail to user
        }
        
        else {
            RequestDispatcher rd = sc.getRequestDispatcher("/forgetpassword.jsp");
            PrintWriter out= response.getWriter();
            out.println("<font color=red>"+"Вы ввели некорректный электронный адрес. Попробуйте еще раз."+"</font>");
            rd.include(request, response);
        }
         
    }
    
   
}