package org.kevin.reactor.examples.fluxcreate;

import org.kevin.reactor.examples.common.Util;
import reactor.core.publisher.Flux;

public class TestConditionOperator {
    public static void main(String[] args) {
//        checkDefaultIfEmpty();
        checkSwitchIfEmpty();
    }

    private static void checkDefaultIfEmpty() {
        Flux.range(1,10)
                .filter(i-> i>11)
                .defaultIfEmpty(50)
                .subscribe(Util.subscriber());
    }
    private static void checkSwitchIfEmpty() {
        Flux.range(1,10)
                .filter(i-> i>11)
                .switchIfEmpty(fallback())
                .subscribe(Util.subscriber());
    }

    private static Flux<Integer> fallback(){
        return  Flux.range(100,3);
    }
}
