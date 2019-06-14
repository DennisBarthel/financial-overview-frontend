package de.netos.ui.login.view;

import java.util.Collection;
import java.util.function.Consumer;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.component.tabs.Tabs.SelectedChangeEvent;
import com.vaadin.flow.spring.annotation.SpringComponent;
import com.vaadin.flow.spring.annotation.UIScope;

import de.netos.auth.login.LoginRequest;
import de.netos.auth.signup.SignUpRequest;
import de.netos.ui.login.TemplateType;
import de.netos.util.Callback;

@SpringComponent
@UIScope
public class LoginDialog extends Dialog implements Login {

	@Autowired
	private transient Collection<LoginViewListener> loginViewListeners;
	
	private Tabs tabs = new Tabs();
	private Div content = new Div();
	
	private Consumer<LoginRequest> loginConsumer;
	private Consumer<SignUpRequest> signUpConsumer;
	
	private Callback callback;
	
	private AuthTemplate authTemplate;
	
	@PostConstruct
	private void init() {
		this.setCloseOnOutsideClick(false);
		this.setCloseOnEsc(false);
		
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
			authTemplate = new SignInTemplate(loginConsumer);
		} else if (selectedIndex == 1) {
			authTemplate = new SignUpTemplate(signUpConsumer);
		} else {
			throw new IllegalStateException("Unknown selected tab: " + selectedIndex);
		}
		content.add((Component) authTemplate);
	}
	
	public void onClose(Callback callback) {
		this.callback = callback;
	}
	
	@Override
	public void onLoginRequest(Consumer<LoginRequest> loginConsumer) {
		this.loginConsumer = loginConsumer;
	}
	
	@Override
	public void onSignUpRequest(Consumer<SignUpRequest> signUpConsumer) {
		this.signUpConsumer = signUpConsumer;
	}
	
	@Override
	public void setErrorMessage(String errorMessage) {
		authTemplate.setErrorMessage(errorMessage);
	}
	
	@Override
	public void setLoginSuccessful() {
		this.close();
		callback.action();
	}
	
	@Override
	public void changeTemplate(TemplateType templateType) {
		if (templateType == TemplateType.LOGIN) {
			tabs.setSelectedIndex(0);
		} else if (templateType == TemplateType.REGISTER) {
			tabs.setSelectedIndex(1);
		}
	}
}
