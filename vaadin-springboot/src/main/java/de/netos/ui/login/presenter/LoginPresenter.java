package de.netos.ui.login.presenter;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.HttpClientErrorException;

import com.vaadin.flow.i18n.I18NProvider;
import com.vaadin.flow.spring.annotation.SpringComponent;
import com.vaadin.flow.spring.annotation.UIScope;

import de.netos.auth.login.LoginRequest;
import de.netos.auth.signup.SignUpRequest;
import de.netos.rest.auth.AuthService;
import de.netos.ui.login.view.LoginForm;
import de.netos.ui.login.view.LoginViewListener;

@SpringComponent
@UIScope
public class LoginPresenter implements LoginViewListener {
	
	@Autowired
	private AuthService authService;
	
	@Autowired
	private I18NProvider translationProvider;

	private LoginForm form;
	
	@Override
	public void viewInit(LoginForm loginForm) {
		this.form = loginForm;
		
		form.onLoginRequest(this::login);
		form.onSignUpRequest(this::signUp);
	}
	
	private void login(LoginRequest loginRequest) {
		try {
			authService.login(loginRequest);
		} catch (HttpClientErrorException e) {
			String key = get(e.getStatusText());
			String translation = translationProvider.getTranslation(key, Locale.getDefault());
			form.setErrorMessage(translation);
		}
	}
	
	private String get(String key) {
		return key.replaceAll(" ", ".").toLowerCase();
	}
	
	private void signUp(SignUpRequest signUpRequest) {
		System.out.println(signUpRequest.getFirstname() + " " + signUpRequest.getLastname());
	}
}
