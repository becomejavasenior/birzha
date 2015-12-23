package com.birzha.servlets;


import java.io.IOException;

import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import database.dao.CurrencyDao;
import database.dao.FactoryDao;
import database.impl.FactoryDaoImpl;
import database.model.Currency;

	@WebServlet(name = "FillCurrencies", urlPatterns = { "/fillcurrencies" })
	public class FillCurrenciesServlet extends HttpServlet {

		FactoryDao factory = new FactoryDaoImpl();
		Currency currency = new Currency();
		CurrencyDao currdao = null; 
		private ServletConfig config;  
		private ServletContext sc;
			
	    public void init(ServletConfig config) throws ServletException {
	      	this.config = config;  
			sc = config.getServletContext();
		}
	    
		protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			resp.setContentType("text/html; charset=cp1251");
	     	currdao = factory.getCurrencyDao();
	     	ArrayList<Currency> currList = currdao.getCurrList();
	     	req.setAttribute("currList", currList);
	     	
	     	RequestDispatcher rd = sc.getRequestDispatcher("/neworder.jsp");
	     	rd.include(req, resp);
	}

}
