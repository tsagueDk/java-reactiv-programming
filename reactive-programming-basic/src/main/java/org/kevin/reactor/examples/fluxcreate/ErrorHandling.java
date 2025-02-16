package org.kevin.reactor.examples.fluxcreate;

import org.kevin.reactor.examples.common.Util;
import reactor.core.publisher.Flux;

public class ErrorHandling {
    public static void main(String[] args) {
//        onErrorReturnHandling();
        onCustomErrorReturnHandling();
    }

    private static void onErrorReturnHandling() {
        Flux.range(1,10)
                .map(i -> i==5? 5/0: i)
                .onErrorReturn(-1)
                .subscribe(Util.subscriber());
    }
    private static void onCustomErrorReturnHandling() {
        Flux.range(1,10)
                .map(i -> i==5? 5/0: i)
                .onErrorReturn(IllegalArgumentException.class, -1)
                .onErrorReturn(ArithmeticException.class, 0)
                .onErrorReturn(11)
                .subscribe(Util.subscriber());
    }
}
