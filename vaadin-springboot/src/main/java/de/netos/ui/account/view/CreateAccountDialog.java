package de.netos.ui.account.view;

import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.html.Div;

public class CreateAccountDialog extends Dialog {

	private Div content = new Div();
	
	public CreateAccountDialog() {
		add(content);
		
		content.add(new AccountForm());
	}
}
