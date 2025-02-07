package org.kevin.reactor.examples.subscriber;

import lombok.extern.slf4j.Slf4j;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
@Slf4j
public class SubscriberImpl implements Subscriber<String> {

	private Subscription subscription;

	public Subscription getSubscription(){
		return subscription;
	}
	@Override
	public void onSubscribe(Subscription subscription) {
		this.subscription=subscription;
	}

	@Override
	public void onNext(String email) {
		log.info("received: {}", email);
	}

	@Override
	public void onError(Throwable throwable) {
		log.error("error", throwable);
	}

	@Override
	public void onComplete() {
		log.info("completed!");
	}
}
