package com.example.service.business;

import com.example.service.ArithmeticCalculator;
import com.example.service.Calculator;
import com.example.service.UnitCalculator;

// Interface: 
// 1. ✔ OOP (LC, OCP, DIP, ISP)(Since its inception)
// 2. ✔ Functional Programming (Java 8/9)
// 3. ✔ Modular Programming (Java 9)
// 4. ✔ Asynchronous Programming (Java 8)
// 5. ✔ Reactive Programming (Java 9) -> Cloud-native/MSA/Serverless Architecture
// 6.    Aspect-Oriented Programming (Java 6)
public class SimpleCalculatorApplication {

	public static void main(String[] args) {
		B b = new B();
		System.err.println(b instanceof A);
		C c = new C();
		// Error: System.err.println(c instanceof A);
		System.err.println(c instanceof ArithmeticCalculator);
		System.err.println(c instanceof UnitCalculator);
		System.err.println(c instanceof Calculator);
	}

}

class A {}
class B extends A {}
class C {}