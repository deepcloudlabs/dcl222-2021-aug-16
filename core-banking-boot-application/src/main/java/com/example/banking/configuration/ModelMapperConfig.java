package com.example.banking.configuration;

import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.banking.entity.Account;
import com.example.banking.entity.AccountDocument;
import com.example.banking.entity.AccountEntity;
import com.example.banking.entity.Iban;

@Configuration
public class ModelMapperConfig {
	private static final Converter<AccountDocument,Account> accountDocument2AccountConverter = context -> {
		var document = context.getSource();
		var account = new Account.Builder(Iban.of(document.getIban()))
				                  .balance(document.getBalance(), document.getCurrency())
				                  .status(document.getStatus())
				                  .build();
		return account;
	};
	
	private static final Converter<Account,AccountDocument> account2AccountDocumentConverter = context -> {
		var account = context.getSource();
		var accountDocument = new AccountDocument();
		accountDocument.setIban(account.getIban().getValue());
		accountDocument.setBalance(account.getBalance().getValue());
		accountDocument.setCurrency(account.getBalance().getCurrency());
		accountDocument.setStatus(account.getStatus());
		return accountDocument;
	};
	
	private static final Converter<AccountEntity,Account> accountEntity2AccountConverter = context -> {
		var accEntity = context.getSource();
		var account = new Account.Builder(Iban.of(accEntity.getIban()))
				.balance(accEntity.getBalance(), accEntity.getCurrency())
				.status(accEntity.getStatus())
				.build();
		return account;
	};
	
	@Bean
	public ModelMapper mapper() {
		var modelMapper = new ModelMapper();
		modelMapper.addConverter(accountDocument2AccountConverter, AccountDocument.class, Account.class);
		modelMapper.addConverter(account2AccountDocumentConverter, Account.class, AccountDocument.class);
		modelMapper.addConverter(accountEntity2AccountConverter, AccountEntity.class, Account.class);
		return modelMapper;
	}
}
