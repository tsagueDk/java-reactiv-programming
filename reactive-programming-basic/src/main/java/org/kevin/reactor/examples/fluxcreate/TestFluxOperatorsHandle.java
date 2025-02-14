package org.kevin.reactor.examples.fluxcreate;

import lombok.extern.slf4j.Slf4j;
import org.kevin.reactor.examples.common.Util;
import reactor.core.publisher.Flux;

/*
	Handle behaves like filter + map

	1 => -2
	4 => don't send
	7 => error
	else => send as is it
 */
@Slf4j
public class TestFluxOperatorsHandle {
    public static void main(String[] args) {

//		fluxWithHandle();
        handleOperationWithFluxGenerate();

    }

    private static void fluxWithHandle() {
        Flux.range(1, 10)
                .handle((item, sink) -> {
                    switch (item) {
                        case 1 -> sink.next(-2);
                        case 4 -> {
                        }
                        case 7 -> sink.error(new RuntimeException("oops"));
                        default -> sink.next(item);
                    }
                }).subscribe(Util.subscriber());
    }

    private static void fluxGenerate() {
        Flux.generate(synchronousSink -> {
                    String country = Util.faker().country().name();
                    synchronousSink.next(country);

                })
                .cast(String.class)
                .takeUntil(("Cameroon")::equalsIgnoreCase)
                .subscribe(Util.subscriber());
    }

    private static void handleOperationWithFluxGenerate() {
        Flux.generate(synchronousSink -> {
                    String country = Util.faker().country().name();
                    synchronousSink.next(country);

                })
                .cast(String.class)
                .map(String::toUpperCase)
                .handle((countryName, sink) -> {
                    switch (countryName) {
                        case "CAMEROON" -> sink.next("Le Continent");
//                        case "Libya" -> sink.error(new RuntimeException("oops War"));
                        default -> sink.next(countryName);
                    }
                    if("UKRAINE".equalsIgnoreCase(countryName)){
                        sink.complete();
                    }
                })
                .subscribe(Util.subscriber());
    }

}