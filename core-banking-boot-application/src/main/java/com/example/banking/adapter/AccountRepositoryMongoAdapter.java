package com.example.banking.adapter;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import com.example.banking.entity.Account;
import com.example.banking.entity.Iban;
import com.example.banking.repository.AccountDocumentRepository;
import com.example.banking.repository.AccountRepository;

// Adapter: AccountRepository --> AccountDocumentRepository
@Repository
@ConditionalOnProperty(name = "persistenceStrategy", havingValue = "mongo")
@Scope("singleton") // default
public class AccountRepositoryMongoAdapter implements AccountRepository {
	private AccountDocumentRepository repo;
	private ModelMapper modelMapper;
	
	public AccountRepositoryMongoAdapter(AccountDocumentRepository repo, ModelMapper modelMapper) {
		this.repo = repo;
		this.modelMapper = modelMapper;
	}

	@Override
	public Optional<Account> findByIban(Iban from) {
		var accountDocument = repo.findById(from.getValue());
		if (accountDocument.isEmpty())
			return Optional.empty();
		// ACL: Anti-Corruption Layer
		return Optional.of(modelMapper.map(accountDocument.get(), Account.class));
	}

	@Override
	public void update(Account account) {
		//TODO
	}

}
