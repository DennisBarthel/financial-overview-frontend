package de.netos.ui.login.view;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.router.Route;

import de.netos.util.Security;

@Route("login")
public class LoginView extends Div {

	@Autowired
	private LoginDialog dialog;
	
	@PostConstruct
	private void init() {
		dialog.open();
		dialog.onClose(this::onClose);
//		Button button = new Button("Text");
//		button.addClickListener(event -> onClose());
//		
//		this.add(button);
	}
	
	private void onClose() {
		System.out.println("Login successful");
		Security.setL();
		UI.getCurrent().navigate("");
	}
}
