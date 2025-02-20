package org.kevin.reactor.examples.fluxcreate;

import lombok.extern.slf4j.Slf4j;
import org.kevin.reactor.examples.common.Util;
import reactor.core.publisher.Mono;
@Slf4j
public class ErrorHandlingDeep {
    public static void main(String[] args) {
//        Mono.just(5)
//                .map(i -> i==5? i/0: i)
//                .onErrorResume(ArithmeticException.class ,error -> onErrorResumeFallback())
//                .onErrorResume(error -> onErrorResume1())
//                .onErrorReturn(-1)
//                .subscribe(Util.subscriber());
        Mono.just(5)
                .map(i -> i==5? i/0: i)
                .onErrorReturn(-1)
                .onErrorContinue((ex,obj) -> log.error("error on => {}",obj,ex))
                .subscribe(Util.subscriber());

//        Mono.error(new RuntimeException("oops"))
//                .onErrorResume(ArithmeticException.class, ex -> onErrorResumeFallback())
//                .onErrorReturn(-1)
//                .subscribe(Util.subscriber());
    }

    private static Mono<Integer> onErrorResumeFallback() {
        return  Mono.fromCallable(()-> Util.faker().random().nextInt(50,100));
    }
    private static Mono<Integer> onErrorResume1() {
        return  Mono.fromCallable(()-> Util.faker().random().nextInt(1,10));
    }
}
