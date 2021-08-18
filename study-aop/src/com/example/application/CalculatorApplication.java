package com.example.application;

import java.lang.reflect.Proxy;

import com.example.handler.AuditingHandler;
import com.example.handler.ProfilingHandler;
import com.example.service.Calculator;
import com.example.service.business.StandardCalculator;

// AOP:  i) Java SE 6+ -> reflection api
//      ii) AOP Libraries: Ex: AspectJ
//     iii) Frameworks: Spring AOP -> Java SE6+, Java EE 6: CDI AOP, ...
// Implementations: 1) Dynamic Proxy 2) Instrumentation API 3) Compile-time Weaving    
public class CalculatorApplication {

	public static void main(String[] args) {
		StandardCalculator standardCalculator = new StandardCalculator();
		Calculator calc = standardCalculator;
		var clazz = calc.getClass();
		var auditingHandler = new AuditingHandler(calc);
		calc = (Calculator) Proxy.newProxyInstance(clazz.getClassLoader(), clazz.getInterfaces(), auditingHandler);
		var profilingHandler = new ProfilingHandler(calc);
		calc = (Calculator) Proxy.newProxyInstance(clazz.getClassLoader(), clazz.getInterfaces(), profilingHandler);
		standardCalculator.setSelf(calc);
		System.out.println("2*3="+calc.mul(2, 3));
//		System.out.println("2/3="+calc.div(2, 3));
//		System.out.println("2-3="+calc.sub(2, 3));
//		System.out.println("2+3="+calc.add(2, 3));
	}

}
