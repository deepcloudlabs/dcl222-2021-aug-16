package com.example.banking.entity;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "accounts")
public class AccountEntity {
	@Id
	private String iban;
	@Column(name = "bakiye")
	private double balance;
	@Enumerated(EnumType.STRING)
	private FiatCurrency currency;
	@Enumerated(EnumType.STRING)
	private AccountStatus status;

	public AccountEntity() {
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
		AccountEntity other = (AccountEntity) obj;
		return Objects.equals(iban, other.iban);
	}

	@Override
	public String toString() {
		return "AccountEntity [iban=" + iban + ", balance=" + balance + ", currency=" + currency + ", status=" + status
				+ "]";
	}

}
