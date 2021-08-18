package com.example.banking.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.banking.entity.AccountDocument;

public interface AccountDocumentRepository extends MongoRepository<AccountDocument, String>{

}
