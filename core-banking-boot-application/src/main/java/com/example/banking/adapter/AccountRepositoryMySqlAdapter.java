package com.example.banking.adapter;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Repository;

import com.example.banking.entity.Account;
import com.example.banking.entity.Iban;
import com.example.banking.repository.AccountDocumentRepository;
import com.example.banking.repository.AccountEntityRepository;
import com.example.banking.repository.AccountRepository;

// Adapter: AccountRepository --> AccountDocumentRepository
@Repository
@ConditionalOnProperty(name = "persistenceStrategy", havingValue = "mysql")
public class AccountRepositoryMySqlAdapter implements AccountRepository {
	private AccountEntityRepository repo;
	private ModelMapper modelMapper;
	
	public AccountRepositoryMySqlAdapter(AccountEntityRepository repo, ModelMapper modelMapper) {
		this.repo = repo;
		this.modelMapper = modelMapper;
	}

	@Override
	public Optional<Account> findByIban(Iban from) {
		var accountEntity = repo.findById(from.getValue());
		if (accountEntity.isEmpty())
			return Optional.empty();
		// ACL: Anti-Corruption Layer
		return Optional.of(modelMapper.map(accountEntity.get(), Account.class));
	}

	@Override
	public void update(Account account) {
		//TODO
	}

}
