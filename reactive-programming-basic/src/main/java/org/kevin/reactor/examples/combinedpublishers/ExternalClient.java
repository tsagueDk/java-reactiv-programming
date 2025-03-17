package org.kevin.reactor.examples.combinedpublishers;

import lombok.extern.slf4j.Slf4j;
import org.kevin.reactor.examples.common.AbstractHttpClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.function.UnaryOperator;

@Slf4j
public class ExternalClient extends AbstractHttpClient {
	public Mono<String> getItems(String path){
		return  this.httpClient.get()
				.uri(AbstractHttpClient.BASE_URL+path)
				.responseContent()
				.asString()
				.next();
//				.transform(addDebuggerOption());

	}


	private <T> UnaryOperator<Flux<T>> addDebuggerOption(){
		return  mono -> mono .doOnNext(i -> log.info("received this {}",i))
				.doOnCancel(()-> log.info("Canceled"))
				.doOnError(ex -> log.info("caught this object error on ",ex));
	}
}
