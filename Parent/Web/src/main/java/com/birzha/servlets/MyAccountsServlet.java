package com.birzha.servlets;

import java.io.IOException;

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

@WebServlet(name = "MyAccountsServlet", urlPatterns = { "/myaccounts" })
public class MyAccountsServlet extends HttpServlet {
	
	private UserDao user = null;
	FactoryDao factory = new FactoryDaoImpl();
	private ServletConfig config;  
	private ServletContext sc;
	private static final Logger logger=Logger.getLogger(LoginServlet.class);
		
    public void init(ServletConfig config) throws ServletException {
      	this.config = config;  
		sc = config.getServletContext();
	}
    
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html; charset=cp1251");
     	user = factory.getUserDao();
     	

	}
	
}