package de.netos;

import java.util.Locale;

import javax.annotation.PostConstruct;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.applayout.AbstractAppRouterLayout;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.AppLayoutMenu;
import com.vaadin.flow.component.applayout.AppLayoutMenuItem;
import com.vaadin.flow.component.html.Span;

public class MainView extends AbstractAppRouterLayout {
	
	@PostConstruct
	private void init() {
		UI.getCurrent().setLocale(Locale.ENGLISH);
	}
	
	@Override
	protected void configure(AppLayout appLayout, AppLayoutMenu appLayoutMenu) {
		appLayout.setBranding(new Span("Financial Overview"));
		
		appLayoutMenu.addMenuItem(new AppLayoutMenuItem("Accounts", "account"));
	}
}
