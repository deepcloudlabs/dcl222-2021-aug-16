package com.example.banking.entity;

import java.util.Objects;

// Value Object
public final class Money {
	private final double value;
	private final FiatCurrency currency;

	public Money(double value) {
		this(value, FiatCurrency.TL);
	}

	private Money(double value, FiatCurrency currency) {
		this.value = value;
		this.currency = currency;
	}

	public double getValue() {
		return value;
	}

	public FiatCurrency getCurrency() {
		return currency;
	}

	public static Money valueOf(double value) { // factory method
		return valueOf(value, FiatCurrency.TL);
	}

	public static Money valueOf(double value, FiatCurrency currency) { // factory method
		// validation
		Objects.requireNonNull(currency);
		if (value < 0.0)
			throw new IllegalArgumentException("Money value must be positive or zero");
		return new Money(value, currency);
	}
}
