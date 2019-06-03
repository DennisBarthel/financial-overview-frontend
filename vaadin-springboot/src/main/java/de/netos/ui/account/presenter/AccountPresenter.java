package de.netos.ui.account.presenter;

import com.vaadin.flow.spring.annotation.SpringComponent;
import com.vaadin.flow.spring.annotation.UIScope;

import de.netos.ui.account.view.AccountView;
import de.netos.ui.account.view.AccountViewListener;

@SpringComponent
@UIScope
public class AccountPresenter implements AccountViewListener {

	private AccountView view;
	
	@Override
	public void init(AccountView accountView) {
		this.view = accountView;
	}
}
