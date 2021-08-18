package com.example.banking.application.business;

import com.example.banking.application.MoneyTransfer;
import com.example.banking.entity.Iban;
import com.example.banking.entity.Money;
import com.example.banking.event.MoneyTransferedEvent;
import com.example.banking.infrastructure.EventPublisher;
import com.example.banking.repository.AccountRepository;

public class StandardMoneyTransferApplication implements MoneyTransfer {
	private AccountRepository accountRepository;
	private EventPublisher eventPublisher;
	
	public StandardMoneyTransferApplication(AccountRepository accountRepository, EventPublisher eventPublisher) {
		this.accountRepository = accountRepository;
		this.eventPublisher = eventPublisher;
	}

	@Override
	public void transfer(Iban from, Iban to, Money amount) {
		var fromAccount = accountRepository.findByIban(from);
		var toAccount = accountRepository.findByIban(to);
		if (fromAccount.isEmpty() || toAccount.isEmpty())
			throw new IllegalArgumentException("Cannot find account(s) to transfer money.");
		var fromAcc = fromAccount.get();
		var toAcc = toAccount.get();
		if (fromAcc.getBalance().getCurrency() != amount.getCurrency())
			throw new IllegalArgumentException("Cannot transfer money between different currencies.");
		if (toAcc.getBalance().getCurrency() != amount.getCurrency())
			throw new IllegalArgumentException("Cannot transfer money between different currencies.");
        fromAcc.withdraw(amount);
        toAcc.deposit(amount);
        accountRepository.update(fromAcc);
        accountRepository.update(toAcc);
        eventPublisher.publish(new MoneyTransferedEvent(from, to, amount));
	}

}
