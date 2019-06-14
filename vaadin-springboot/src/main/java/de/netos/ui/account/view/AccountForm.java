package de.netos.ui.account.view;

import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.dependency.HtmlImport;
import com.vaadin.flow.component.polymertemplate.Id;
import com.vaadin.flow.component.polymertemplate.PolymerTemplate;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.templatemodel.TemplateModel;

import de.netos.account.AccountType;
import de.netos.account.Currency;
import de.netos.ui.account.view.AccountForm.AccountFormModel;

@Tag("account-form")
@HtmlImport("frontend://src/account/AccountForm.html")
public class AccountForm extends PolymerTemplate<AccountFormModel> {
	
	@Id("name")
	private TextField name;
	
	@Id("number")
	private TextField number;
	
	@Id("type")
	private ComboBox<AccountType> type;
	
	@Id("currency")
	private ComboBox<Currency> currency;
	
	@Id("cancelButton")
	private Button cancel;
	
	@Id("saveButton")
	private Button save;
	
	public AccountForm() {
		type.setItems(AccountType.values());
		type.setItemLabelGenerator(item -> UI.getCurrent().getTranslation(item.name()));
		
		currency.setItems(Currency.values());
		
		getModel().setCancelText(UI.getCurrent().getTranslation("button.cancel"));
		getModel().setSaveText(UI.getCurrent().getTranslation("button.create"));
	}
	
	public TextField getNameTextField() {
		return name;
	}
	
	public TextField getNumberTextField() {
		return number;
	}
	
	public ComboBox<AccountType> getTypeComboBox() {
		return type;
	}
	
	public ComboBox<Currency> getCurrencyComboBox() {
		return currency;
	}
	
	public Button getCancelButton() {
		return cancel;
	}
	
	public Button getSaveButton() {
		return save;
	}
	
	@Override
	public AccountFormModel getModel() {
		return super.getModel();
	}
	
	public interface AccountFormModel extends TemplateModel {
		void setTitleText(String text);
		
		void setCancelText(String text);
		void setSaveText(String text);
	}
}