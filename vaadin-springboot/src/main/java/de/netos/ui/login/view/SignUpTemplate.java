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
import com.vaadin.flow.data.binder.ValidationResult;
import com.vaadin.flow.data.binder.ValueContext;
import com.vaadin.flow.data.validator.AbstractValidator;
import com.vaadin.flow.data.validator.EmailValidator;
import com.vaadin.flow.templatemodel.TemplateModel;

import de.netos.auth.signup.SignUpRequest;

@Tag("sign-up-template")
@HtmlImport("frontend://src/auth/SignUpTemplate.html")
public class SignUpTemplate extends PolymerTemplate<TemplateModel> {
	
	private Binder<SignUpRequest> binder;
	private final Consumer<SignUpRequest> signUpConsumer;
	
	@Id("firstname")
	private TextField firstnameField;
	
	@Id("lastname")
	private TextField lastnameField;
	
	@Id("username")
	private TextField usernameField;
	
	@Id("password")
	private PasswordField passwordField;
	
	@Id("confirm_password")
	private PasswordField confirmPasswordField;
	
	public SignUpTemplate(Consumer<SignUpRequest> signUpConsumer) {
		this.signUpConsumer = signUpConsumer;
		
		initBinder();
	}
	
	private void initBinder() {
		binder = new Binder<>();
		
		binder.forField(firstnameField)
			.bind(SignUpRequest::getFirstname, SignUpRequest::setFirstname);
		binder.forField(lastnameField)
			.bind(SignUpRequest::getLastname, SignUpRequest::setLastname);
		binder.forField(usernameField)
			.withValidator(new EmailValidator("EMail address not valid"))
			.bind(SignUpRequest::getEmail, SignUpRequest::setEmail);
		binder.forField(passwordField)
			.withValidator(new PasswordEqualValidator("Not equal"))
			.bind(SignUpRequest::getPassword, SignUpRequest::setPassword);
	}
	
	@EventHandler
	private void onClick() {
		SignUpRequest signUpRequest = new SignUpRequest();
		if (binder.writeBeanIfValid(signUpRequest)) {
			signUpConsumer.accept(signUpRequest);
		}
	}
	
	private class PasswordEqualValidator extends AbstractValidator<String> {

		protected PasswordEqualValidator(String errorMessage) {
			super(errorMessage);
		}

		@Override
		public ValidationResult apply(String value, ValueContext context) {
			String confirmPassword = confirmPasswordField.getValue();
			
			return toResult(value, confirmPassword.equals(value));
		}		
	}
}
