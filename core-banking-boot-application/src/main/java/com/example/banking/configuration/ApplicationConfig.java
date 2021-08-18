package com.example.banking.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.banking.application.MoneyTransfer;
import com.example.banking.application.business.StandardMoneyTransferApplication;
import com.example.banking.infrastructure.EventPublisher;
import com.example.banking.repository.AccountRepository;

@Configuration
public class ApplicationConfig {

	@Bean // factory method -> Spring Bean -> Component
	public MoneyTransfer moneyTransfer(AccountRepository accountRepository, EventPublisher eventPublisher) {
		return new StandardMoneyTransferApplication(accountRepository, eventPublisher);
	}
}
