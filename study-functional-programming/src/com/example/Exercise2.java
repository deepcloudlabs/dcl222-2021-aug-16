package com.example;

import java.util.List;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Predicate;

public class Exercise2 {
	// Functional Programming
	// 1. Predicate -> Functional Interface -> Single Abstract Method
	// 2. Lambda Expression -> Function
	@SuppressWarnings("unused")
	public static void main(String[] args) {
		var numbers = List.of(4, 8, 15, 16, 23, 42);
		// Lambda Expression, High-Order Function -> No Side-effect
		Predicate<Integer> even = value -> value % 2 == 0;
		Function<Integer, Integer> squared = value -> value * value; // Lambda Expression
		Function<Integer, Integer> squared2 = Sample::squared; // Method Reference
		BinaryOperator<Integer> accumulate = (acc, value) -> acc + value; // Lambda Expression
		var sum = numbers.parallelStream() // internal loop
				// .parallel() // multi-core programming -> 8-core --> 64-core
				// Hadoop: HDFS + MapReduce
				.filter(Sample::even) // 4 , 8, 16, 42
				.map(Sample::squared) // 16, 64, 256, 1764
				// 0 -> (0,16) -> 16 -> (16,64) -> 80 -> (80,256) -> 336 -> (336,1764) -> 2100
				.reduce(0, Sample::accumulate); // 2100
		System.out.println(String.format("sum: %4d", sum));
	}

}

interface Sample {
	public static boolean even(Integer value) {
		return value % 2 == 0;
	}

	public static Integer squared(Integer value) {
		return value * value;
	}

	public static Integer accumulate(Integer acc, Integer value) {
		return acc + value;
	}
}