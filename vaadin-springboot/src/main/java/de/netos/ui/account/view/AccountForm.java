package de.netos.ui.account.view;

import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.dependency.HtmlImport;
import com.vaadin.flow.component.polymertemplate.PolymerTemplate;
import com.vaadin.flow.templatemodel.TemplateModel;

@Tag("account-form")
@HtmlImport("frontend://src/account/AccountForm.html")
public class AccountForm extends PolymerTemplate<TemplateModel> {
	
	public AccountForm() {
		System.out.println("Test");
	}
}
