package de.netos.ui.account.view;

import java.util.Collection;

import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.HtmlImport;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.polymertemplate.Id;
import com.vaadin.flow.component.polymertemplate.PolymerTemplate;
import com.vaadin.flow.data.provider.ListDataProvider;
import com.vaadin.flow.templatemodel.TemplateModel;

import de.netos.account.AccountOverviewDTO;

@Tag("account-list")
@HtmlImport("frontend://src/account/AccountList.html")
public class AccountList extends PolymerTemplate<TemplateModel> {

	@Id(value = "new")
	private Button newAccount;
	
	@Id(value = "grid")
	private Grid<AccountOverviewDTO> accountList;
	
	public AccountList() {
		accountList.addColumn(
				AccountCard.getTemplate()
					.withProperty("account-card", AccountCard::create)
					.withEventHandler("edit", event -> System.out.println("edit"))
					.withEventHandler("detail", event -> System.out.println("detail"))
					.withEventHandler("delete", event -> System.out.println("delete")));
		
		newAccount.addClickListener(event -> {
			CreateAccountDialog createAccountDialog = new CreateAccountDialog();
			createAccountDialog.open();
		});
	}
	
	public void setData(Collection<AccountOverviewDTO> items) {
		accountList.setDataProvider(new ListDataProvider<>(items));
	}
}
