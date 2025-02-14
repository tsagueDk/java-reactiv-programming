package org.kevin.reactor.examples.common;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class ExternalServiceClient extends AbstractHttpClient {
    public Mono<String> getProductName(int productId) {
        return this.httpClient.get()
                .uri("/demo01/product/" + productId)
                .responseContent()
                .aggregate()
                .asString()
                .doOnSubscribe(subscription -> System.out.println("HTTP request sent"))
                .doOnNext(response -> System.out.println("Response received: " + response))
                .doOnError(error -> System.err.println("HTTP request error: " + error.getMessage()))
                .doOnTerminate(() -> System.out.println("HTTP request terminated"));
    }
    public Flux<Integer> getstoreStream() {
        return this.httpClient.get()
                .uri("/demo02/stock/stream")
                .responseContent()
                .asString()
                .doOnSubscribe(subscription -> System.out.println("HTTP request sent"))
                .doOnNext(response -> System.out.println("Response received: " + response))
                .doOnError(error -> System.err.println("HTTP request error: " + error.getMessage()))
                .doOnTerminate(() -> System.out.println("HTTP request terminated"))
                .map(Integer::parseInt);
    }
}


