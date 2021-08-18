package com.example.service.business;

import com.example.service.Calculator;

// Stateless Class -> Singleton
public enum StandardCalculator implements Calculator {
	
	INSTANCE;

	@Override
	public double add(double x, double y) {
		return x + y;
	}

	@Override
	public double sub(double x, double y) {
		return x - y;
	}

	@Override
	public double div(double x, double y) {
		return x / y;
	}

	@Override
	public double mul(double x, double y) {
		return x * y;
	}

}
