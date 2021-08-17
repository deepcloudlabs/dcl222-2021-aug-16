package com.example.service;

public abstract interface ArithmeticCalculator {
	public static final int x=2; // global constant
	public abstract double add(double x, double y);
	public abstract double sub(double x, double y);
	public abstract double mul(double x, double y);
	public abstract double div(double x, double y);
}
