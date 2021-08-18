package com.example.banking.entity;

import java.util.Objects;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "accounts")
public class AccountDocument {
	@Id
	private String iban;
	@Field("bakiye")
	private double balance;
	@Field("para_birimi")
	private FiatCurrency currency;
	@Field("durum")
	private AccountStatus status;

	public AccountDocument() {
	}

	public String getIban() {
		return iban;
	}

	public void setIban(String iban) {
		this.iban = iban;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public FiatCurrency getCurrency() {
		return currency;
	}

	public void setCurrency(FiatCurrency currency) {
		this.currency = currency;
	}

	public AccountStatus getStatus() {
		return status;
	}

	public void setStatus(AccountStatus status) {
		this.status = status;
	}

	@Override
	public int hashCode() {
		return Objects.hash(iban);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AccountDocument other = (AccountDocument) obj;
		return Objects.equals(iban, other.iban);
	}

	@Override
	public String toString() {
		return "AccountDocument [iban=" + iban + ", balance=" + balance + ", currency=" + currency + ", status="
				+ status + "]";
	}

}
