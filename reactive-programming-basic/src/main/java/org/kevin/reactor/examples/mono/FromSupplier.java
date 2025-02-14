package org.kevin.reactor.examples.mono;

import org.kevin.reactor.examples.common.Util;
import org.kevin.reactor.examples.subscriber.SubscriberImpl;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.function.Consumer;

public class FromSupplier {
	public static void main(String[] args) {
		var listItems = List.of(1,2,3,4,5);
//		Mono.just(sumInterger(listItems)).subscribe();
		Mono.fromSupplier(()->sumInterger(listItems)).subscribe(Util.subscriber(""));
	}

	private static int sumInterger (List<Integer> items){
		return items.stream().mapToInt(Integer::intValue).sum();
	}
}
