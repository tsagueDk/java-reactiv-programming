package org.kevin.reactor.examples.mono;

import lombok.extern.slf4j.Slf4j;
import org.kevin.reactor.examples.common.Util;
import reactor.core.publisher.Mono;
@Slf4j
public class FromRunnable {
	public static void main(String[] args) {
		getProductName(1).subscribe(Util.subscriber(""));
		getProductName(2).subscribe(Util.subscriber(""));
	}

	private static Mono<String> getProductName(int productId){
		if(productId !=1){
			return Mono.fromRunnable(()-> notifyInCaseProductDontExist(productId));
		}
		return Mono.fromSupplier(()-> Util.faker().commerce().productName());
	}

	private static void notifyInCaseProductDontExist(int productId){
		log.info("Product with the id {} don't exist",productId);
	}
}
