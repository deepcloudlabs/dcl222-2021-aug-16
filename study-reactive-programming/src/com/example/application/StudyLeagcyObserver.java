package com.example.application;

import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.concurrent.TimeUnit;

import com.example.event.TradeEvent;

@SuppressWarnings("deprecation")
public class StudyLeagcyObserver {

	public static void main(String[] args) {
		var tradeEvents = List.of(
				new TradeEvent("orcl", 100.0, 50),
				new TradeEvent("ibm", 102.0, 70),
				new TradeEvent("msft", 104.0, 80),
				new TradeEvent("gogle", 150.0, 250),
				new TradeEvent("orcl", 101.0, 150),
				new TradeEvent("ibm", 99.0, 80),
				new TradeEvent("msft", 102.0, 280)
		);
		var observable = new TradeEventObservable();
		Observer slowObserver = (o, tradeEvent) -> {
			try {TimeUnit.SECONDS.sleep(3);}catch (Exception e) { }
			System.err.println("Slow observer: "+tradeEvent);
		};
		Observer fastObserver = (o, tradeEvent) -> {
			System.err.println("Fast observer: "+tradeEvent);
		};
		observable.addObserver(slowObserver);
		observable.addObserver(fastObserver);
		tradeEvents.forEach(observable::notifyObservers);
	}

}

@SuppressWarnings("deprecation")
class TradeEventObservable extends Observable {

	@Override
	public void notifyObservers(Object event) {
		setChanged();
		super.notifyObservers(event);
	}
	
}