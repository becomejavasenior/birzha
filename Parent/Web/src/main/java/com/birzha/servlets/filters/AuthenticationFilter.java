package com.birzha.servlets.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

@WebFilter("/AuthenticationFilter")
public class AuthenticationFilter implements Filter{
	
    private Logger logger = Logger.getLogger(AuthenticationFilter.class);
	private ServletContext context;
	
    public void init(FilterConfig fConfig) throws ServletException {
        logger.info("AuthenticationFilter initialized");
    	this.context = fConfig.getServletContext();
    }
     
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
 
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        response.setContentType("text/html; charset=cp1251");
        String uri = req.getRequestURI();
        logger.info("Requested Resource::"+uri);
        HttpSession session = req.getSession(false);
       // System.out.println("session = "+ session);
        if(session == null && 
        		!(uri.endsWith("html") 
        				|| uri.contains("registration") 
        				|| uri.contains("login")
        				|| !uri.contains("registered")
        				)) {
           
        	logger.error("Unauthorized access request");
            res.sendRedirect(req.getContextPath()+"/login.html");
        }
        
       else  if(session != null && (uri.contains("login") || uri.contains("registration"))) {
        	res.sendRedirect(req.getContextPath()+"/personalpage.jsp");
        }
        
       	else {
             chain.doFilter(request, response);
        }
    
       
        /*else{
        	
        	if(session!=null) {
        		String authstatus = (String)session.getAttribute("authstatus");
        	      	if((authstatus.equalsIgnoreCase("registered")&&(uri.contains("registered/activated")))
        	      			|| (authstatus.equalsIgnoreCase("activated")&&(uri.contains("registered/activated/exchange")))) {
        	      		logger.error("User tried to access unallowed resource");
        	      		res.sendRedirect(req.getContextPath()+"/accessdenied.html");
        	      	}
		        	else {
		            chain.doFilter(request, response);
		        	}
        	}
        	else {
                 chain.doFilter(request, response);
            	}
        }*/
    
    }
 
    public void destroy() {
        
    }
}
