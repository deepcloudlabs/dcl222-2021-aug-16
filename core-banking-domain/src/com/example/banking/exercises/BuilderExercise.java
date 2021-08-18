package com.example.banking.exercises;

import static com.example.banking.entity.AccountStatus.CLOSED;
import static com.example.banking.entity.FiatCurrency.USD;
import static com.example.banking.entity.Iban.of;
import static com.example.banking.entity.Money.valueOf;

import com.example.banking.entity.Account;

public class BuilderExercise {

	@SuppressWarnings("unused")
	public static void main(String[] args) {
		var acc1 = new Account(of("tr1"),valueOf(100, USD),CLOSED);
		var acc2 = new Account.Builder(of("tr1"))
				              .status("CLOSED")
				              .balance(100, USD)
				              .build();
	}

}
