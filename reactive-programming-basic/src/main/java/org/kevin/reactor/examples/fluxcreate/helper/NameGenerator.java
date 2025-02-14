package org.kevin.reactor.examples.fluxcreate.helper;

import org.kevin.reactor.examples.common.Util;
import reactor.core.publisher.Flux;
import reactor.core.publisher.FluxSink;

import java.util.function.Consumer;

public class NameGenerator implements Consumer<FluxSink<String>> {

    private FluxSink<String> sink;

    @Override
    public void accept(FluxSink<String> stringFlux) {
        this.sink= stringFlux;
    }

    public void generated(){
        this.sink.next(Util.faker().name().firstName());
    }
}
