package com.example;

import java.util.List;

public class Exercise1 {

	public static void main(String[] args) {
		var numbers = List.of(4,8,15,16,23,42);
		var sum = 0;
		for (var number : numbers) { // external loop
			if (number%2 == 0) {
				var squared = number * number ;
				sum = sum + squared;
			}
		}
		System.out.println(String.format("sum: %4d",sum));
	}

}
