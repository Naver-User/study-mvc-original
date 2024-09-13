package org.zerock.myapp.listener;

import javax.servlet.ServletRequestAttributeEvent;
import javax.servlet.ServletRequestAttributeListener;
import javax.servlet.annotation.WebListener;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;


@Log4j2
@NoArgsConstructor

@WebListener
public class RequestScopeAttributeListener implements ServletRequestAttributeListener {

	@Override
    public void attributeAdded(ServletRequestAttributeEvent event) {
    	if(event.getName().startsWith("org.springframework") || 
			event.getName().startsWith("org.apache") || 
			event.getName().startsWith("javax.servlet") || 
			event.getName().endsWith(".FILTERED")) return;

    	log.trace("---------------------------------------------------");
    	log.trace("attributeAdded(event) invoked.");
    	
    	String name = event.getName();
    	Object value = event.getValue();	// If the attribute was added, this is the value of the attribute.
    	
    	log.trace("\t+ name(%s), new value(%s), type(%s)".formatted(name, value, value.getClass().getName()));
    	log.trace("");
    } // attributeAdded

	@Override
    public void attributeRemoved(ServletRequestAttributeEvent event) {
    	if(	event.getName().startsWith("org.springframework") || 
			event.getName().startsWith("org.apache") || 
			event.getName().startsWith("javax.servlet") || 
			event.getName().endsWith(".FILTERED")) return;

    	log.trace("---------------------------------------------------");
    	log.trace("attributeRemoved(event) invoked.");
    	
    	String name = event.getName();
    	Object value = event.getValue();		// If the attribute was removed, this is the value of the `removed` attribute.
    	
    	log.trace("\t+ name(%s), removed value(%s), type(%s)".formatted(name, value, value.getClass().getName()));
    	log.trace("");
    } // attributeRemoved

	@Override
    public void attributeReplaced(ServletRequestAttributeEvent event) {
    	if(event.getName().startsWith("org.springframework") || 
			event.getName().startsWith("org.apache") || 
			event.getName().startsWith("javax.servlet") || 
			event.getName().endsWith(".FILTERED")) return;

    	log.trace("---------------------------------------------------");
    	log.trace("attributeReplaced(event) invoked.");
    	
    	String name = event.getName();
    	Object value = event.getValue();		// If the attribute was replaced, this is the `old` value of the attribute.
    	
    	log.trace("\t+ name(%s), old value(%s), type(%s)".formatted(name, value, value.getClass().getName()));
    	log.trace("");
    } // attributeReplaced
	
} // end class
