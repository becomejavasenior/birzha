package com.birzha.servlets;

import java.io.IOException;
import  org.apache.log4j.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "Logout", urlPatterns = { "/logout" })
public class LogoutServlet extends HttpServlet  {

	private static final long serialVersionUID = 9079696067100512060L;

	private static final Logger logger = Logger.getLogger(LogoutServlet.class);


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");

        HttpSession session = request.getSession(false);
        logger.info("User with login = "+session.getAttribute("login")+" is logout");
        if(session != null){
            session.invalidate();
        }
        response.sendRedirect("logout.jsp");
    }
 
}

