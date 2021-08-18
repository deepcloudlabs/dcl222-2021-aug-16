package com.example.banking.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.banking.entity.AccountEntity;

public interface AccountEntityRepository extends JpaRepository<AccountEntity, String>{

}
