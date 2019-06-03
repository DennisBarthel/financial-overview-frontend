package de.netos.ui.account.view;

import java.util.Collection;

import de.netos.account.AccountOverviewDTO;

public interface AccountView {

	void setData(Collection<AccountOverviewDTO> data);
}
