package com.example.banking.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.annotation.RequestScope;

import com.example.banking.application.MoneyTransfer;
import com.example.banking.dto.TransferRequest;
import com.example.banking.dto.TransferResponse;
import com.example.banking.entity.Iban;
import com.example.banking.entity.Money;

@RestController
@RequestMapping("/accounts")
@RequestScope
@CrossOrigin
public class BankController {
	private MoneyTransfer moneyTransfer;
	
	public BankController(MoneyTransfer moneyTransfer) {
		this.moneyTransfer = moneyTransfer;
	}

	@PostMapping
	public TransferResponse transfer(@RequestBody TransferRequest request) {
		moneyTransfer.transfer(Iban.of(request.getFromIban()), Iban.of(request.getToIban()), Money.valueOf(request.getAmount()));
        return new TransferResponse("success");
	}
}
