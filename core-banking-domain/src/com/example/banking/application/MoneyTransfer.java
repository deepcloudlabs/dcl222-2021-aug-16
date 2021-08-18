package com.example.banking.application;

import com.example.banking.entity.Iban;
import com.example.banking.entity.Money;

// Use case(s)
public interface MoneyTransfer {
	void transfer(Iban from,Iban to,Money amount);
}
