package de.netos.ui;

import static java.util.Locale.ENGLISH;
import static java.util.ResourceBundle.getBundle;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import com.vaadin.flow.i18n.I18NProvider;
import com.vaadin.flow.spring.annotation.SpringComponent;

@SpringComponent
public class VaadinI18NProvider implements I18NProvider {
	
	private static final String RESOURCE_BUNDLE_NAME = "messages";
	
	private static final ResourceBundle RESOURCE_BUNDLE_EN = getBundle(RESOURCE_BUNDLE_NAME , ENGLISH);
	
	private static final List<Locale> providedLocales = Arrays.asList(Locale.ENGLISH, Locale.GERMAN);

	@Override
	public List<Locale> getProvidedLocales() {
		return providedLocales;
	}

	@Override
	public String getTranslation(String key, Locale locale, Object... params) {
		ResourceBundle bundle = null;
		
		if (Locale.ENGLISH.equals(locale)) {
			bundle = RESOURCE_BUNDLE_EN;
		}
		
		if (bundle.containsKey(key)) {
			return bundle.getString(key);
		} else {
			return key + "_" + locale;
		}		
	}

}
