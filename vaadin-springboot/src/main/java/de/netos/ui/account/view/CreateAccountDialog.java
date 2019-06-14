package de.netos.ui.account.view;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.spring.annotation.SpringComponent;

import de.netos.account.CreateAccountDTO;
import de.netos.events.EventSender;
import de.netos.ui.account.presenter.AccountPresenter;

@SpringComponent(CreateAccountDialog.COMPONENT_NAME)
@Scope(scopeName = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class CreateAccountDialog extends Dialog {
	
	public static final String COMPONENT_NAME = "create_account_dialog";

	private Div content = new Div();
	private AccountForm accountForm = new AccountForm();
	
	private Binder<CreateAccountDTO> binder = new Binder<>();
	
	@Autowired
	private EventSender eventSender;
	
	@PostConstruct
	private void init() {
		initAccountForm();
		initBinder();
		
		content.add(accountForm);
		add(content);
	}
	
	private void initAccountForm() {
		accountForm.getModel().setCancelText(UI.getCurrent().getTranslation("button.cancel"));
		accountForm.getModel().setSaveText(UI.getCurrent().getTranslation("button.create"));
		accountForm.getModel().setTitleText(UI.getCurrent().getTranslation("create.title"));
		
		accountForm.getCancelButton().addClickListener(event -> this.close());
		accountForm.getSaveButton().addClickListener(event -> {
			CreateAccountDTO dto = new CreateAccountDTO();
			if (binder.writeBeanIfValid(dto)) {
				eventSender.sendEvent(AccountPresenter.CREATE_ACCOUNT, dto);
			}
		});
	}
	
	private void initBinder() {
		binder.forField(accountForm.getNameTextField())
			.bind(CreateAccountDTO::getName, CreateAccountDTO::setName);
		
		binder.forField(accountForm.getNumberTextField())
			.bind(CreateAccountDTO::getNumber, CreateAccountDTO::setNumber);
		
		binder.forField(accountForm.getTypeComboBox())
			.bind(CreateAccountDTO::getType, CreateAccountDTO::setType);
		
		binder.forField(accountForm.getCurrencyComboBox())
			.bind(CreateAccountDTO::getCurrency, CreateAccountDTO::setCurrency);
	}
}
