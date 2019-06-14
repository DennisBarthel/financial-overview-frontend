package de.netos.events;

import org.springframework.context.ApplicationEvent;

public class ViewEvent extends ApplicationEvent {

	private final String methodIdentifier; 
	private final ParameterDTO parameters;
	
	public ViewEvent(Object source, String methodIdentifier, Object... parameters) {
		super(source);
		this.methodIdentifier = methodIdentifier;
		this.parameters = new ParameterDTO(parameters);
	}
	
	public String getMethodIdentifier() {
		return methodIdentifier;
	}
	
	public ParameterDTO getParameters() {
		return parameters;
	}
}
