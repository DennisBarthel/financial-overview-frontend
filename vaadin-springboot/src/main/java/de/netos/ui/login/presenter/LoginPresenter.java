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
import de.netos.ui.login.TemplateType;
import de.netos.ui.login.view.Login;
import de.netos.ui.login.view.LoginViewListener;

@SpringComponent
@UIScope
public class LoginPresenter implements LoginViewListener {
	
	@Autowired
	private AuthService authService;
	
	@Autowired
	private I18NProvider translationProvider;

	private Login form;
	
	@Override
	public void viewInit(Login loginForm) {
		this.form = loginForm;
		
		form.onLoginRequest(this::login);
		form.onSignUpRequest(this::signUp);
	}
	
	private void login(LoginRequest loginRequest) {
		try {
			authService.login(loginRequest);
			form.setLoginSuccessful();
		} catch (HttpClientErrorException e) {
			form.setErrorMessage(translationProvider.getTranslation(e.getStatusText(), Locale.getDefault()));
		}
	}
	
	private void signUp(SignUpRequest signUpRequest) {
		try {
			authService.register(signUpRequest);
			form.changeTemplate(TemplateType.LOGIN);
		} catch (HttpClientErrorException e) {
			form.setErrorMessage(translationProvider.getTranslation(e.getStatusText(), Locale.getDefault()));
		}
	}
}
