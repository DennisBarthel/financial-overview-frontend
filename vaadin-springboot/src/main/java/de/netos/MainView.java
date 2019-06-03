package de.netos;

import java.util.Locale;

import javax.annotation.PostConstruct;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.applayout.AbstractAppRouterLayout;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.AppLayoutMenu;
import com.vaadin.flow.component.applayout.AppLayoutMenuItem;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.router.Route;

@Route("/")
public class MainView extends AbstractAppRouterLayout {
	
//	@Autowired
//	private LoginDialog loginForm;

	@PostConstruct
	private void init() {
		Locale.setDefault(Locale.ENGLISH);
		
//		loginForm.open();
//		loginForm.onClose(this::onClose);
	}
	
	private void onClose() {
		System.out.println("Login successful");
		UI.getCurrent().navigate("account");
	}
	
	@Override
	protected void configure(AppLayout appLayout, AppLayoutMenu appLayoutMenu) {
		appLayout.setBranding(new Span("Financial Overview"));
		
		appLayoutMenu.addMenuItem(new AppLayoutMenuItem("Accounts", "account"));
		appLayoutMenu.addMenuItem(new AppLayoutMenuItem("Item2", "chart"));
		
	}
}
