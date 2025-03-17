package org.kevin.reactor.examples.combinedpublishers;

import org.kevin.reactor.examples.common.Util;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class CombinedPublishers {
	static ExternalClient client = new ExternalClient();

	public static void main(String[] args) {

		for (int i = 1; i < 11; i++) {
			getMonoProduct(i);

		}
		Util.sleepSeconds(10);
	}

	public static void getMonoProduct(int id) {
		Mono.zip(getProduct(id), getPrice(id), getReview(id)).map(t -> new Product(t.getT2(), t.getT3(), t.getT1()))
				.subscribe(Util.subscriber());
	}

	public static Mono<String> getPrice(int id) {
		return client.getItems("/demo05/price/" + id);
	}

	public static Mono<String> getProduct(int id) {
		return client.getItems("/demo05/product/" + id);
		//		return Flux.error(new RuntimeException("oops"));

		//		return  Flux.range(1,5)
		//				.map(i -> "product-"+i )
		//				.delayElements(Duration.ofMillis(100));
	}

	public static Mono<String> getReview(int id) {
		return client.getItems("/demo05/review/" + id);
	}

	record Product(String name, String review, String price) {
	}

}
