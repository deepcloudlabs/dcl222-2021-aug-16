package com.example.banking.repository;

import java.util.Optional;

import com.example.banking.entity.Account;
import com.example.banking.entity.Iban;

public interface AccountRepository {

	Optional<Account> findByIban(Iban from);

	void update(Account account);

}
