package de.netos.ui.login.view;

import java.util.function.Consumer;

import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.dependency.HtmlImport;
import com.vaadin.flow.component.polymertemplate.EventHandler;
import com.vaadin.flow.component.polymertemplate.Id;
import com.vaadin.flow.component.polymertemplate.PolymerTemplate;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.validator.EmailValidator;
import com.vaadin.flow.data.validator.StringLengthValidator;
import com.vaadin.flow.templatemodel.TemplateModel;

import de.netos.auth.login.LoginRequest;
import de.netos.ui.login.view.SignInTemplate.SignInTemplateModel;

@Tag("sign-in-template")
@HtmlImport("frontend://src/auth/SignInTemplate.html")
public class SignInTemplate extends PolymerTemplate<SignInTemplateModel> implements AuthTemplate {
	
	private Binder<LoginRequest> signUpBinder;
	private final Consumer<LoginRequest> loginConsumer;
	
	@Id("username")
	private TextField usernameField;
	
	@Id("password")
	private PasswordField passwordField;

	public SignInTemplate(Consumer<LoginRequest> loginConsumer) {
		this.loginConsumer = loginConsumer;
		initBinder();
	}
	
	private void initBinder() {
		signUpBinder = new Binder<>();
		
		signUpBinder.forField(usernameField)
			.withValidator(new EmailValidator("Not a valid email address"))
			.bind(LoginRequest::getEmail, LoginRequest::setEmail);
		
		signUpBinder.forField(passwordField)
			.withValidator(new StringLengthValidator("Password is too short", 8, null))
			.bind(LoginRequest::getPassword, LoginRequest::setPassword);
	}
	
	@EventHandler
	private void onClick() {
		LoginRequest loginRequest = new LoginRequest();
		if (signUpBinder.writeBeanIfValid(loginRequest)) {
			loginConsumer.accept(loginRequest);
		}
	}
	
	@Override
	public void setErrorMessage(String errorMessage) {
		getModel().setShowError(true);
		getModel().setError(errorMessage);
	}
	
	public interface SignInTemplateModel extends TemplateModel {
		void setShowError(boolean showError);
		void setError(String error);
	}
}
