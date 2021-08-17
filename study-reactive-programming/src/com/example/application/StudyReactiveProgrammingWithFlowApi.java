package com.example.application;

import java.util.List;
import java.util.concurrent.Flow;
import java.util.concurrent.SubmissionPublisher;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.Flow.Subscription;

import com.example.event.TradeEvent;

public class StudyReactiveProgrammingWithFlowApi {

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
		SubmissionPublisher<TradeEvent> publisher = new SubmissionPublisher<>();
		publisher.subscribe(new AlgoTrader());
		publisher.subscribe(new SignalProcessor());
		System.err.println("Application has just started!");
		tradeEvents.forEach(publisher::submit);
		System.err.println("Loop is over!");
		try {TimeUnit.SECONDS.sleep(30);}catch (Exception e) { }		
		publisher.close();
		System.err.println("Application has completed!");
	}

}

class AlgoTrader implements Flow.Subscriber<TradeEvent> {

	private Subscription subscription;

	@Override
	public void onSubscribe(Subscription subscription) {
		this.subscription = subscription;
		this.subscription.request(1);
	}

	@Override
	public void onNext(TradeEvent event) {
		try {TimeUnit.SECONDS.sleep(3);}catch (Exception e) { }		
		System.err.println("AlgoTrader processing the event: "+event);
		this.subscription.request(1); // backpressure
	}

	@Override
	public void onError(Throwable throwable) {
		System.err.println("An error has occured while processing events: "+throwable.getMessage());
	}

	@Override
	public void onComplete() {
		System.err.println("AlgoTrader has completed!");
		
	}
	
}
class SignalProcessor implements Flow.Subscriber<TradeEvent> {
	
	private Subscription subscription;
	
	@Override
	public void onSubscribe(Subscription subscription) {
		this.subscription = subscription;
		this.subscription.request(1);		
	}
	
	@Override
	public void onNext(TradeEvent event) {
		System.err.println("SignalProcessor processing the event: "+event);
		try {TimeUnit.SECONDS.sleep(1);}catch (Exception e) { }		
		this.subscription.request(1);
	}
	
	@Override
	public void onError(Throwable throwable) {
		System.err.println("An error has occured while processing events: "+throwable.getMessage());
	}
	
	@Override
	public void onComplete() {
		System.err.println("SignalProcessor has completed!");
	}
	
}
