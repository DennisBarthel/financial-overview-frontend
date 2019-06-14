package de.netos.ui.account.view;

import java.util.Collection;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;

import de.netos.MainView;
import de.netos.account.AccountOverviewDTO;
import de.netos.ui.login.view.LoginView;
import de.netos.util.Security;

@Route(value = "account", layout = MainView.class)
@RouteAlias(value = "", layout = MainView.class)
public class AccountViewImpl extends Div implements AccountView, BeforeEnterObserver {

	@Autowired
	private transient Collection<AccountViewListener> accountViewListeners;
	
	@Autowired
	private BeanFactory beanFactory;
	
	private AccountList accountList;
	
	@PostConstruct
	private void init() {
 		accountViewListeners.forEach(listener -> listener.init(this));
		
 		accountList = beanFactory.getBean(AccountList.COMPONENT_NAME, AccountList.class);
		add(accountList);
	}
	
	@Override
	public void setData(Collection<AccountOverviewDTO> data) {
		accountList.setData(data);
		
		this.add(accountList);
	}
	
	@Override
	public void beforeEnter(BeforeEnterEvent event) {
		if (!Security.getL()) {
			event.rerouteTo(LoginView.class);
		}
	}
}
