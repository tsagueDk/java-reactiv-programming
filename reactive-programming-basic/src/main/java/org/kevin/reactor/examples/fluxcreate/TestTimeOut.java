package org.kevin.reactor.examples.fluxcreate;

import org.kevin.reactor.examples.common.Util;
import reactor.core.publisher.Mono;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.time.Duration;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class TestTimeOut {

    public static void main(String[] args) {
        getProductName()
                .timeout(Duration.ofSeconds(1),fallback())
//                .timeout(Duration.ofSeconds(2),fallback())
                .subscribe(Util.subscriber());
        Util.sleepSeconds(5);
//        getProductName()
//                .timeout(Duration.ofSeconds(1))
//                .onErrorReturn("fallback")
//                .subscribe(Util.subscriber());
//        Util.sleepSeconds(4);

    }


    private static Mono<String> getProductName(){
        return Mono.fromSupplier(()-> "service-"+Util.faker().commerce().productName())
                .delayElement(Duration.ofMillis(1900));
    }
    private static Mono<String> fallback(){
        return Mono.fromSupplier(()-> "fallback-"+Util.faker().commerce().productName())
                .delayElement(Duration.ofMillis(300));
    }
}
