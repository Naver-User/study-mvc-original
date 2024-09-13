package org.zerock.myapp.listener;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionActivationListener;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionIdListener;
import javax.servlet.http.HttpSessionListener;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;



@Log4j2
@NoArgsConstructor

@WebListener
public class SessionListener implements HttpSessionListener, HttpSessionActivationListener, HttpSessionBindingListener, HttpSessionIdListener {

    
	/**
	 * Receives notification that a session has been created.
     * @see HttpSessionListener#sessionCreated(HttpSessionEvent)
     */
	@Override
    public void sessionCreated(HttpSessionEvent event)  {
    	log.info("====================================");
        log.trace("sessionCreated(event) invoked.");
    	log.info("====================================");
    } // sessionCreated

	/**
	 * Notifies the object that it is being bound to a session and identifies the session.
     * @see HttpSessionBindingListener#valueBound(HttpSessionBindingEvent)
     */
	@Override
    public void valueBound(HttpSessionBindingEvent event)  { 
    	log.info("====================================");
        log.trace("valueBound(event) invoked.");
    	log.info("====================================");
    } // valueBound

	/**
	 * Receives notification that session id has been changed in a session.
     * @see HttpSessionIdListener#sessionIdChanged(HttpSessionEvent, String)
     */
	@Override
    public void sessionIdChanged(HttpSessionEvent event, String oldSessionId)  { 
    	log.info("====================================");
        log.trace("sessionIdChanged(event, oldSessionId: {}) invoked.", oldSessionId);
    	log.info("====================================");
    } // sessionIdChanged

	/**
	 * Receives notification that a session is about to be invalidated.
     * @see HttpSessionListener#sessionDestroyed(HttpSessionEvent)
     */
	@Override
    public void sessionDestroyed(HttpSessionEvent event)  { 
    	log.info("====================================");
        log.trace("sessionDestroyed(event) invoked.");
    	log.info("====================================");
    } // sessionDestroyed

	/**
	 * Notification that the session has just been activated.
     * @see HttpSessionActivationListener#sessionDidActivate(HttpSessionEvent)
     */
	@Override
    public void sessionDidActivate(HttpSessionEvent event)  { 
    	log.info("====================================");
        log.trace("sessionDidActivate(event) invoked.");
    	log.info("====================================");
    } // sessionDidActivate

	/**
	 * Notification that the session is about to be passivated.
     * @see HttpSessionActivationListener#sessionWillPassivate(HttpSessionEvent)
     */
	@Override
    public void sessionWillPassivate(HttpSessionEvent event)  { 
    	log.info("====================================");
        log.trace("sessionWillPassivate(event) invoked.");
    	log.info("====================================");
    } // sessionWillPassivate

	/**
	 * Notifies the object that it is being unbound from a session and identifies the session.
     * @see HttpSessionBindingListener#valueUnbound(HttpSessionBindingEvent)
     */
	@Override
    public void valueUnbound(HttpSessionBindingEvent event)  { 
    	log.info("====================================");
    	log.trace("valueUnbound(event) invoked.");
     	log.info("====================================");
    } // valueUnbound
	
} // end class

