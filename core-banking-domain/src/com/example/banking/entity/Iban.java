package com.example.banking.entity;

import java.util.Map;
import java.util.Objects;
import java.util.WeakHashMap;

// DDD: Value Object i) Belongs Entity ii) Immutable
public final class Iban {
	private final String value;
	private static final Map<String, Iban> CACHE = new WeakHashMap<>();

	private Iban(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

	public static Iban of(String value) { // factory method naming conventions
		// validation
		Objects.requireNonNull(value);
		if (!isValid(value))
			throw new IllegalArgumentException("This is not a valid iban.");
		// caching -> object pooling -> immutable
		var iban = CACHE.get(value);
		if (Objects.isNull(iban)) {
			iban = new Iban(value);
			CACHE.put(value, iban);
		}
		return iban;
	}

	private static boolean isValid(String value) {
		return true;
	}
}
