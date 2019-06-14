package de.netos.events;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;

import com.vaadin.flow.spring.annotation.SpringComponent;

@SpringComponent
public class EventSender {
	
	@Autowired
	private ApplicationEventPublisher eventPublisher;
	
	public void sendEvent(String methodIdentifier, Object... parameters) {
		eventPublisher.publishEvent(new ViewEvent(this, methodIdentifier, parameters));
	}
}
