package com.example.lottery.service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public interface LotteryService {
	List<Integer> draw(int size, int max);
	
	default List<List<Integer>> draw(int column, int size, int max){
		return IntStream.range(0, column)
				        .mapToObj( i -> this.draw(size, max))
				        .collect(Collectors.toList());
	}
}
