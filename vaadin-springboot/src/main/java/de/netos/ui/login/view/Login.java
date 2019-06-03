package de.netos.ui.login.view;

import java.util.function.Consumer;

import de.netos.auth.login.LoginRequest;
import de.netos.auth.signup.SignUpRequest;
import de.netos.ui.login.TemplateType;

public interface Login {
	
	void onLoginRequest(Consumer<LoginRequest> loginConsumer);
	void onSignUpRequest(Consumer<SignUpRequest> signUpConsumer);
	
	void setErrorMessage(String errorMessage);
	
	void setLoginSuccessful();
	
	void changeTemplate(TemplateType templateType);
}
