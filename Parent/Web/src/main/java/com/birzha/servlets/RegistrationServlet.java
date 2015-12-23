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


@WebServlet(name = "Registration", urlPatterns = { "/registration" })
public class RegistrationServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
	private UserDao userdao = null;
	private User user = new User();
	FactoryDao factory = new FactoryDaoImpl();;
	private ServletConfig config;  
	private ServletContext sc;
	private static final Logger logger=Logger.getLogger(RegistrationServlet.class);

    
	public void init(ServletConfig config) throws ServletException {
		 this.config = config;  
		 sc = config.getServletContext();
		}
	 
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	response.setContentType("text/html; charset=cp1251");
    	userdao = factory.getUserDao();
    	String nickname = request.getParameter("nickname");
      	String email = request.getParameter("email");
    	String password = request.getParameter("password");
    	String password2 = request.getParameter("password2");
     	String errorMsg = null;
    	String rulesOk = request.getParameter("rulesOk");
 	    if(nickname == null || nickname.equals("")){
            errorMsg = "Введите свое имя";
        }
        
        else if(!userdao.ifNicknameIsUnique(nickname)){
            errorMsg = "Ник "+nickname+" уже существует, выберите другой!";
        }
        
        else if(password == null || password.equals("")){
            errorMsg = "Введите пароль";
        }
        else  if(password2 == null || password.equals("")|| !password2.equals(password)){
            errorMsg = "Пароли не совпадают";
        }
               
        else if(email == null || email.equals("")){
            errorMsg = "Введите электронный адрес";
        }
           
        else if(!userdao.ifEmailIsUnique(email)){
            errorMsg = "E-mail "+email+" уже зарегистрирован в системе, выберите другой!";
        }
        
        else if(rulesOk == null) {
        	errorMsg = "Для регистрации необходимо ознакомиться с правилами ресурса!";
        }
        if(errorMsg != null){
            RequestDispatcher rd = sc.getRequestDispatcher("/registrationpage.jsp");
            PrintWriter out= response.getWriter();
            out.println("<font color=red>"+errorMsg+"</font>");
            rd.include(request, response);
        }else{
        	user.setNickname(nickname);
        	user.setEmail(email);
            user.setPassword(password);
 
            if(userdao.create(user)) {
            	 logger.info("User registered with parameters = "+user.toString());
            	 RequestDispatcher rd = sc.getRequestDispatcher("/registrationsuccess.jsp");
                 rd.include(request, response);
            }
            else{
            	logger.error("User couldn't be registered with parameters = "+user.toString());
            	response.sendRedirect(request.getContextPath()+"/dberror.html");
            }
        }
         
    }
    
   
}