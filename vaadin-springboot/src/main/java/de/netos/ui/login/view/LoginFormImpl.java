package de.netos.ui.login.view;

import java.util.Collection;
import java.util.function.Consumer;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;

import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.component.tabs.Tabs.SelectedChangeEvent;
import com.vaadin.flow.spring.annotation.SpringComponent;
import com.vaadin.flow.spring.annotation.UIScope;

import de.netos.auth.login.LoginRequest;
import de.netos.auth.signup.SignUpRequest;

@SpringComponent
@UIScope
public class LoginFormImpl extends VerticalLayout implements LoginForm {

	@Autowired
	private transient Collection<LoginViewListener> loginViewListeners;
	
	private Tabs tabs = new Tabs();
	private Div content = new Div();
	
	private Consumer<LoginRequest> loginConsumer;
	private Consumer<SignUpRequest> signUpConsumer;
	
	@PostConstruct
	private void init() {
		loginViewListeners.forEach(listener -> listener.viewInit(this));
		
		tabs.addSelectedChangeListener(this::changeSelection);
		tabs.setFlexGrowForEnclosedTabs(1);
		
		Tab signInTab = new Tab("Sign In");
		Tab signUpTab = new Tab("Sign Up");
		
		tabs.add(signInTab, signUpTab);
		
		add(tabs);
		add(content);
	}
	
	private void changeSelection(SelectedChangeEvent event) {
		int selectedIndex = event.getSource().getSelectedIndex();
		
		content.removeAll();
		if (selectedIndex == 0) {
			content.add(new SignInTemplate(loginConsumer));
		} else if (selectedIndex == 1) {
			content.add(new SignUpTemplate(signUpConsumer));
		} else {
			throw new IllegalStateException("Unknown selected tab: " + selectedIndex);
		}
	}
	
	@Override
	public void onLoginRequest(Consumer<LoginRequest> loginConsumer) {
		this.loginConsumer = loginConsumer;
	}
	
	@Override
	public void onSignUpRequest(Consumer<SignUpRequest> signUpConsumer) {
		this.signUpConsumer = signUpConsumer;
	}
}
