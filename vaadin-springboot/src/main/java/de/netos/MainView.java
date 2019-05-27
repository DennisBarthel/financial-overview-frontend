package de.netos;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;

import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

import de.netos.ui.login.view.LoginFormImpl;

@Route("")
public class MainView extends VerticalLayout {
	
	@Autowired
	private LoginFormImpl loginForm;

	@PostConstruct
	private void init() {
		Dialog dialog = new Dialog(loginForm);
		dialog.open();
		dialog.setCloseOnOutsideClick(false);
	}
}
