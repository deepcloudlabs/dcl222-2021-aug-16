package com.example.banking.event;

import com.example.banking.entity.FiatCurrency;
import com.example.banking.entity.Iban;
import com.example.banking.entity.Money;

public class MoneyTransferedEvent extends DomainEvent {
	private final String fromIban;
	private final String toIban;
	private final double amount;
	private final FiatCurrency currency;

	public MoneyTransferedEvent(Iban from, Iban to, Money amount) {
		this.fromIban = from.getValue();
		this.toIban = to.getValue();
		this.amount = amount.getValue();
		this.currency = amount.getCurrency();
	}

	public String getFromIban() {
		return fromIban;
	}

	public String getToIban() {
		return toIban;
	}

	public double getAmount() {
		return amount;
	}

	public FiatCurrency getCurrency() {
		return currency;
	}

}
