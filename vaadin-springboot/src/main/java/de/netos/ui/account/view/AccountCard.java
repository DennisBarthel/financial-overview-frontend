package de.netos.ui.account.view;

import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.dependency.HtmlImport;
import com.vaadin.flow.component.polymertemplate.EventHandler;
import com.vaadin.flow.component.polymertemplate.PolymerTemplate;
import com.vaadin.flow.data.renderer.TemplateRenderer;
import com.vaadin.flow.templatemodel.TemplateModel;

import de.netos.account.AccountOverviewDTO;
import de.netos.ui.account.view.AccountCard.AccountCardModel;

@Tag("account-card")
@HtmlImport("frontend://src/account/AccountCard.html")
public class AccountCard extends PolymerTemplate<AccountCardModel> {

	public static TemplateRenderer<AccountOverviewDTO> getTemplate() {
		return TemplateRenderer.of("<account-card"
				+ " account-card='[[item.accountCard]]'"
				+ " on-card-click='cardClick'>"
				+ "</account-card>");
	}
	
	public static AccountCard create(AccountOverviewDTO account) {
		return new AccountCard(account);
	}
	
	private AccountCard(AccountOverviewDTO account) {
		getModel().setAccountNumber(account.getAccount().getNumber());
	}
	
	@EventHandler
	private void cardClick() {
		System.out.println("CardClick");
	}
	
	public interface AccountCardModel extends TemplateModel {
		String getAccountNumber();
		void setAccountNumber(String accountNumber);
	}
}
