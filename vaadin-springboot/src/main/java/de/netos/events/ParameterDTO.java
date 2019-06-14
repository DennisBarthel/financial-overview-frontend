package de.netos.events;

@SuppressWarnings( "unchecked")
public class ParameterDTO {

	private final Object[] parameters;

	public ParameterDTO( final Object... parameters) {
		this.parameters = parameters;
	}

	public <T extends Object> T getFirstParameter( final Class<T> clazz) {
		return getParameterByIndex( clazz, 0);
	}

	public <T extends Object> T getSecondParameter( final Class<T> clazz) {
		return getParameterByIndex( clazz, 1);
	}

	public <T extends Object> T getThirdParameter( final Class<T> clazz) {
		return getParameterByIndex( clazz, 2);
	}

	public <T extends Object> T getParameterByIndex( final Class<T> clazz, int index) {
		T parameter = null;
		if ( parameters != null && index < parameters.length) {
			parameter = (T) parameters[index];
		}
		return parameter;
	}
}
