package de.netos.ui;

import static java.util.Locale.ENGLISH;
import static java.util.Locale.GERMAN;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

import com.vaadin.flow.i18n.I18NProvider;
import com.vaadin.flow.spring.annotation.SpringComponent;

@SpringComponent
public class VaadinI18NProvider implements I18NProvider {
	
	private static final String RESOURCE_BUNDLE_NAME = "messages";
	
	private final List<Locale> providedLocales = Arrays.asList(ENGLISH, GERMAN);
	
	private Map<Locale, ResourceBundle> resourceBundleMap = new HashMap<>();

	public VaadinI18NProvider() {
		resourceBundleMap.put(ENGLISH, ResourceBundle.getBundle(RESOURCE_BUNDLE_NAME, ENGLISH));
		resourceBundleMap.put(GERMAN, ResourceBundle.getBundle(RESOURCE_BUNDLE_NAME, GERMAN));
	}
	
	@Override
	public List<Locale> getProvidedLocales() {
		return providedLocales;
	}

	@Override
	public String getTranslation(String key, Locale locale, Object... params) {
		String transformedKey = transformKey(key);
		
		ResourceBundle bundle = resourceBundleMap.get(locale);
		
		if (bundle.containsKey(transformedKey)) {
			return bundle.getString(transformedKey);
		} else {
			return transformedKey;
		}		
	}

	private String transformKey(String key) {
		if (key.contains(" ")) {
			return key.replaceAll(" ", ".").toLowerCase();
		}
		if (key.contains("_")) {
			return key.replaceAll("_", ".").toLowerCase();
		}
		return key.toLowerCase();
	}
}
