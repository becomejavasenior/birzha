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
import database.dao.WalletDao;
import database.impl.FactoryDaoImpl;


@WebServlet(name = "LoginServlet", urlPatterns = { "/login" })
public class NewOrderServlet extends HttpServlet {

	FactoryDao factory = new FactoryDaoImpl();
	WalletDao walletDao = null; 
	private ServletConfig config;  
	private ServletContext sc;
	private static final Logger logger=Logger.getLogger(LoginServlet.class);
		
    public void init(ServletConfig config) throws ServletException {
      	this.config = config;  
		sc = config.getServletContext();
	}
    
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html; charset=cp1251");
     	walletDao = factory.getWalletDao();
     	HttpSession session = req.getSession();
     	String curr1 = req.getParameter("curr1");
     	String curr2 = req.getParameter("curr2");
     	String amountcurr1 = req.getParameter("sum1");
     	String amountcurr2 = req.getParameter("sum2");
     	String errorMsg = "";
     	int amount1 = 0;
     	int amount2 = 0;
     	if(amountcurr1 != null) {
         	try {
         		amount1 = Integer.parseInt(amountcurr1);
         		//check if user has such amount of currency
             	String email = (String)session.getAttribute("email");
         		int totalamount1 = walletDao.getWalletBalance(email);
             	if(totalamount1 < amount1){
             		errorMsg = "На Вашем счету недостаточно денег для данной операции.";
             	}
          	}
         	catch(NumberFormatException e) {
         		errorMsg = "Поле Сумма должно быть заполнено!";
         	}
     	}
     	
     	else if(amountcurr2 != null) {
         	try {
         		amount2 = Integer.parseInt(amountcurr2);
          	}
         	catch(NumberFormatException e) {
         		errorMsg = "Поле Сумма должно быть заполнено!";
         	}
     	}
     	
     	 if(errorMsg != null){
	            RequestDispatcher rd = sc.getRequestDispatcher("/neworder.jsp");
	            PrintWriter out= resp.getWriter();
	            out.println("<font color=red>"+errorMsg+"</font>");
	            rd.include(req, resp);
	        }
     	 else {
     		 //record to DB
     		 
     	 }
	
}
}
