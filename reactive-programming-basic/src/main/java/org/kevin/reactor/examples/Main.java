package org.kevin.reactor.examples;

import org.kevin.reactor.examples.publisher.PublisherImpl;
import org.kevin.reactor.examples.subscriber.SubscriberImpl;

public class Main {
	public static void main(String[] args) throws InterruptedException {
		demo1();
	}

	public static void demo1() throws InterruptedException {
		var publisher = new PublisherImpl();
		var subscriber = new SubscriberImpl();
		publisher.subscribe(subscriber);
		subscriber.getSubscription().request(3);
		Thread.sleep(5);
		subscriber.getSubscription().request(12);
	}
}