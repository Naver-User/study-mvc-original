package org.zerock.myapp.listener;

import javax.servlet.ServletRequest;
import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;


@Log4j2
@NoArgsConstructor

@WebListener
public class RequestListener implements ServletRequestListener {

	@Override
    public void requestInitialized(ServletRequestEvent event)  {
		ServletRequest req = event.getServletRequest();
		HttpServletRequest request = (HttpServletRequest) req;
		
		String requestURI = request.getRequestURI();
		
		String queryString = request.getQueryString();
		queryString = (queryString == null) ? "" : "?"+queryString;
		
		HttpSession session = request.getSession(false);
		String sessionId = (session != null) ? session.getId() : "";
		
        log.trace("*** NEW REQUEST INITIALIZED : %s%s from (%s) **********".formatted(
        		requestURI,  queryString, sessionId) );
        log.trace("");
    } // requestInitialized

	
	@Override
    public void requestDestroyed(ServletRequestEvent event)  { 
		String requestURI =  ((HttpServletRequest) event.getServletRequest()).getRequestURI();
		
		String queryString =  ((HttpServletRequest) event.getServletRequest()).getQueryString();
		queryString = (queryString == null) ? "" : "?"+queryString;
		
		HttpSession session = ((HttpServletRequest) event.getServletRequest()).getSession(false);
		String sessionId = (session != null) ? session.getId() : "";
		
        log.trace("");
        log.trace("  *** NEW REQUEST DESTROYED   : %s%s from (%s) **********".formatted(requestURI,  queryString, sessionId));
        log.trace("");
    } // requestDestroyed
	
} // end class

