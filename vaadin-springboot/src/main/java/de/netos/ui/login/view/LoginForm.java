package de.netos.ui.login.view;

import java.util.function.Consumer;

import de.netos.auth.login.LoginRequest;
import de.netos.auth.signup.SignUpRequest;

public interface LoginForm {
	
	void onLoginRequest(Consumer<LoginRequest> loginConsumer);
	void onSignUpRequest(Consumer<SignUpRequest> signUpConsumer);
}
