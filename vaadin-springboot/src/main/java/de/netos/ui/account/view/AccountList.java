package de.netos.ui.account.view;

import java.util.Collection;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;

import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.HtmlImport;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.polymertemplate.Id;
import com.vaadin.flow.component.polymertemplate.PolymerTemplate;
import com.vaadin.flow.data.provider.ListDataProvider;
import com.vaadin.flow.spring.annotation.SpringComponent;
import com.vaadin.flow.templatemodel.TemplateModel;

import de.netos.account.AccountOverviewDTO;

@SpringComponent(AccountList.COMPONENT_NAME)
@Scope(scopeName = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@Tag("account-list")
@HtmlImport("frontend://src/account/AccountList.html")
public class AccountList extends PolymerTemplate<TemplateModel> {

	public static final String COMPONENT_NAME = "account-list";
	
	@Id(value = "new")
	private Button newAccount;
	
	@Id(value = "grid")
	private Grid<AccountOverviewDTO> accountList;
	
	@Autowired
	private BeanFactory beanFactory;
	
	@PostConstruct
	private void init() {
		accountList.addColumn(
				AccountCard.getTemplate()
				.withProperty("account-card", AccountCard::create)
				.withEventHandler("edit", event -> System.out.println("edit"))
				.withEventHandler("detail", event -> System.out.println("detail"))
				.withEventHandler("delete", event -> System.out.println("delete")));
		
		newAccount.addClickListener(event -> {
			CreateAccountDialog createAccountDialog = beanFactory.getBean(CreateAccountDialog.COMPONENT_NAME, CreateAccountDialog.class);
			createAccountDialog.open();
		});
	}
	
	public void setData(Collection<AccountOverviewDTO> items) {
		accountList.setDataProvider(new ListDataProvider<>(items));
	}
}
