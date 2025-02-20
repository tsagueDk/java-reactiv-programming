package org.kevin.reactor.examples.fluxcreate.assignment.client;

import lombok.extern.slf4j.Slf4j;
import org.kevin.reactor.examples.common.AbstractHttpClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.function.UnaryOperator;

@Slf4j
public class ExternalServiceClient extends AbstractHttpClient {

    public Flux<String> getNames() {
        return this.httpClient.get()
                .uri("/demo02/name/stream")
                .responseContent()
                .asString();
    }

    private Mono<String> getProductsName(int productId, String path) {
        return this.httpClient.get()
                .uri(path + productId)
                .responseContent()
                .asString()
//                .transform(addDebuggerOption())
                .next();
    }

    public Mono<String> getProductName(int id){
        var pathEmpty = "/demo03/empty-fallback/product/";
        var pathRoot = "/demo03/product/";
        var pathTimeOut = "/demo03/timeout-fallback/product/";

         return getProductsName(id,pathRoot)
                .timeout(Duration.ofSeconds(2),getProductsName(id,pathTimeOut))
                .switchIfEmpty(getProductsName(id,pathEmpty))
                .doOnError(error -> log.info("caught an error {}",error));

    }

    private <T> UnaryOperator<Flux<T>> addDebuggerOption(){
        return  mono -> mono .doOnNext(i -> log.info("received this {}",i))
                .doOnCancel(()-> log.info("Canceled"))
                .doOnError(ex -> log.info("caught this object {} error on getProductsFallbackForTimeOut {}",ex));
    }

}