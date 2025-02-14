package org.kevin.reactor.examples.fluxcreate;

import org.kevin.reactor.examples.common.Util;
import reactor.core.publisher.Flux;

public class TestFluxGenerate {
    public static void main(String[] args) {
        Flux.<String>generate(synchronousSink -> {
            var country = Util.faker().country().name();
            synchronousSink.next(country);
        }).takeUntil("Morocco"::equalsIgnoreCase)
                .subscribe(Util.subscriber());
    }
}
