package com.example.lottery.application;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.ServiceLoader;

import com.example.lottery.service.business.StandardLotteryService;
import com.example.random.service.QualityLevel;
import com.example.random.service.RandomNumberService;
import com.example.random.service.ServiceQuality;

public class LotteryApplication {

	public static void main(String[] args) throws FileNotFoundException, IOException {
		Properties props = new Properties();
		props.load(new FileInputStream(new File("src","application.properties")));
		var requestedQualityLevel = QualityLevel.valueOf(props.getProperty("random.number.service.quality"));				
		var lotteryService = new StandardLotteryService();
		ServiceLoader<RandomNumberService> randomNumberServices = ServiceLoader.load(RandomNumberService.class);
		for (var randomNumberService : randomNumberServices) {
			var clazz = randomNumberService.getClass();
			if(clazz.isAnnotationPresent(ServiceQuality.class)) {
				var qualityLevel = clazz.getAnnotation(ServiceQuality.class).value();
				if (qualityLevel==requestedQualityLevel)
					lotteryService.setRandomNumberService(randomNumberService);
			}
		}
		lotteryService.draw(3, 6, 60).forEach(System.out::println);			
		
	}

}
