package org.zerock.myapp.listener;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;


@Log4j2
@NoArgsConstructor

@WebListener
public class SessionScopeAttributeListener implements HttpSessionAttributeListener {

	public void attributeAdded(HttpSessionBindingEvent event) {
    	if(event.getName().startsWith("org.springframework") || 
			event.getName().startsWith("org.apache") || 
			event.getName().startsWith("javax.servlet") || 
			event.getName().endsWith(".FILTERED")) return;

    	log.trace("---------------------------------------------------");
    	log.trace("attributeAdded(event) invoked.");
    	
    	HttpSession session = event.getSession();
    	String name = event.getName();
    	Object value = event.getValue();	// If the attribute was added, this is the value of the attribute.
    	
    	log.trace("\t+ session: " + session.getId());
    	log.trace("\t+ name(%s), new value(%s), type(%s)".formatted(name, value, value.getClass().getName()));
    	log.trace("");
    } // attributeAdded

	public void attributeRemoved(HttpSessionBindingEvent event) {
    	if(event.getName().startsWith("org.springframework") || 
			event.getName().startsWith("org.apache") || 
			event.getName().startsWith("javax.servlet") || 
			event.getName().endsWith(".FILTERED")) return;

    	log.trace("---------------------------------------------------");
    	log.trace("attributeRemoved(event) invoked.");
    	
    	HttpSession session = event.getSession();
    	String name = event.getName();
    	Object value = event.getValue();		// If the attribute was removed, this is the value of the `removed` attribute.
    	
    	log.trace("\t+ session: " + session.getId());
    	log.trace("\t+ name(%s), old value(%s), type(%s)".formatted(name, value, value.getClass().getName()));
    	log.trace("");
    } // attributeRemoved

	public void attributeReplaced(HttpSessionBindingEvent event) {
    	if(event.getName().startsWith("org.springframework") || 
			event.getName().startsWith("org.apache") || 
			event.getName().startsWith("javax.servlet") || 
			event.getName().endsWith(".FILTERED")) return;

    	log.trace("---------------------------------------------------");
    	log.trace("attributeReplaced(event) invoked.");
    	
    	HttpSession session = event.getSession();
    	String name = event.getName();
    	Object value = event.getValue();		// If the attribute was replaced, this is the `old` value of the attribute.

    	log.trace("\t+ session: " + session.getId());
    	log.trace("\t+ name(%s), old value(%s), type(%s)".formatted(name, value, value.getClass().getName()));
    	log.trace("");
    } // attributeReplaced
	
} // end class
