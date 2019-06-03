package de.netos.ui.account.view;

import java.util.Collection;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;

import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.router.Route;

import de.netos.MainView;
import de.netos.account.AccountOverviewDTO;

@Route(value = "account", layout = MainView.class)
public class AccountViewImpl extends Div implements AccountView {

	@Autowired
	private transient Collection<AccountViewListener> accountViewListeners;
	
	@PostConstruct
	private void init() {
		accountViewListeners.forEach(listener -> listener.init(this));
		
		add(new AccountList());
	}
	
	@Override
	public void setData(Collection<AccountOverviewDTO> data) {
		AccountList accountList = new AccountList();
		accountList.setData(data);
		
		this.add(accountList);
	}
}
