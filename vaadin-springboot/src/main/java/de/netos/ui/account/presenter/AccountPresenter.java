package de.netos.ui.account.presenter;

import org.springframework.context.event.EventListener;

import com.vaadin.flow.spring.annotation.SpringComponent;
import com.vaadin.flow.spring.annotation.UIScope;

import de.netos.account.CreateAccountDTO;
import de.netos.events.ViewEvent;
import de.netos.ui.account.view.AccountView;
import de.netos.ui.account.view.AccountViewListener;

@SpringComponent
@UIScope
public class AccountPresenter implements AccountViewListener {
	
	public static final String CREATE_ACCOUNT = "create_account";

	private AccountView view;
	
	@Override
	public void init(AccountView accountView) {
		this.view = accountView;
	}
	
	@EventListener(condition = "#event.methodIdentifier == '" + CREATE_ACCOUNT + "'")
	public void handleCreateAccount(ViewEvent event) {
		CreateAccountDTO dto = event.getParameters().getFirstParameter(CreateAccountDTO.class);
		
		System.out.println("Presenter");
	}
}
