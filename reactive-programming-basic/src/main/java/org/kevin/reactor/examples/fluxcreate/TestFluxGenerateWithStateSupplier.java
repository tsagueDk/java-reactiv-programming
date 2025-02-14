package org.kevin.reactor.examples.fluxcreate;

import org.kevin.reactor.examples.common.Util;
import reactor.core.publisher.Flux;

public class TestFluxGenerateWithStateSupplier {
    public static void main(String[] args) {
        Flux.generate(
                () -> 0,
                (counter, sink) -> {
                    var country = Util.faker().country().name();
                    sink.next(country);
                    counter++;
                    if (counter == 10 || country.equalsIgnoreCase("Canada")) {
                        sink.complete();
                    }

                    return counter;
                }
        ).subscribe(Util.subscriber());

    }
}
