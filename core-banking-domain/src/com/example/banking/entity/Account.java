package com.example.banking.entity;

// Bounded-Context (Solution Space, IT System)-> Sub-domain (Problem Space-Business System)
// Ubiquitous Language: Account, Iban, Money
// DDD: Entity -> i) Persist ii) Identity iii) Mutable
// Effective Java (3ed)
public class Account {
	private final Iban iban; // identity
	private Money balance;
	private AccountStatus status;

	public Account(Iban iban) {
		this(iban, Money.valueOf(0));
	}

	public Account(Iban iban, Money balance) {
		this(iban, balance, AccountStatus.ACTIVE);
	}

	public Account(Iban iban, Money balance, AccountStatus status) {
		this.iban = iban;
		this.balance = balance;
		this.status = status;
	}

	public Account(Builder builder) {
		// we are pretty sure that builder object is always valid!
		this.iban = builder.iban;
		this.balance = builder.balance;
		this.status = builder.status;
	}

	public Iban getIban() {
		return iban;
	}

	public Money getBalance() {
		return balance;
	}

	public AccountStatus getStatus() {
		return status;
	}

	public void deposit(Money amount) {
	}

	public void withdraw(Money amount) {
	}
	
	public static class Builder {
		private Iban iban; // identity
		private Money balance;
		private AccountStatus status;
		
		public Builder(Iban iban) {
			this.iban = iban;
		}
		
		public Builder balance(double value) {
			return balance(value,FiatCurrency.TL);
		}
		
		public Builder balance(double value,FiatCurrency currency) {
			this.balance = Money.valueOf(value, currency);
			return this;
		}
		
		public Builder status(String status) {
			this.status = AccountStatus.valueOf(status); // factory method
			return this;
		}
		
		public Builder status(AccountStatus status) {
			this.status = status;
			return this;
		}
		
		public Account build() {
			// validation, business rule, constraint, pre/post-condition,...
			if (status==AccountStatus.CLOSED && balance.getValue() > 0.0)
				throw new IllegalArgumentException("Closed account cannot have positive balance");				
			return new Account(this);
		}
	}
}
