package org.kevin.reactor.examples.mono;

import org.kevin.reactor.examples.common.Util;
import reactor.core.publisher.Mono;

import java.util.List;

public class FromCallable {
	public static void main(String[] args) {
		var listItems = List.of(1,2,3,4,5);
		//		Mono.just(sumInterger(listItems)).subscribe();
		Mono.fromCallable(()-> {
			return sumInterger(listItems);
		}).subscribe(Util.subscriber(""));
	}

	private static int sumInterger (List<Integer> items){
		return items.stream().mapToInt(Integer::intValue).sum();
	}
}
