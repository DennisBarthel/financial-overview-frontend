package de.netos.ui.login.presenter;

import com.vaadin.flow.spring.annotation.SpringComponent;
import com.vaadin.flow.spring.annotation.UIScope;

import de.netos.auth.login.LoginRequest;
import de.netos.auth.signup.SignUpRequest;
import de.netos.ui.login.view.LoginForm;
import de.netos.ui.login.view.LoginViewListener;

@SpringComponent
@UIScope
public class LoginPresenter implements LoginViewListener {

	private LoginForm form;
	
	@Override
	public void viewInit(LoginForm loginForm) {
		this.form = loginForm;
		
		form.onLoginRequest(this::login);
		form.onSignUpRequest(this::signUp);
	}
	
	private void login(LoginRequest loginRequest) {
		System.out.println(loginRequest.getEmail() + " " + loginRequest.getPassword());
	}
	
	private void signUp(SignUpRequest signUpRequest) {
		System.out.println(signUpRequest.getFirstname() + " " + signUpRequest.getLastname());
	}
}
