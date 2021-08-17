package com.example.random.service;

public interface RandomNumberService {
	public abstract int generate(int min, int max);
	
	default int generate(int max) { // default implementation
		return generate(1, max);
	}
}
