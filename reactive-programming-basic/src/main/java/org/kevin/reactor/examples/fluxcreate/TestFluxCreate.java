package org.kevin.reactor.examples.fluxcreate;

import org.kevin.reactor.examples.common.Util;
import org.kevin.reactor.examples.fluxcreate.helper.NameGenerator;
import reactor.core.publisher.Flux;

public class TestFluxCreate {
	public static void main(String[] args) {

		//emitting values
//		Flux.create(sink ->{
//			sink.next(1);
//			sink.next(20);
//			sink.next(30);
//			sink.complete();
//		}).subscribe(Util.subscriber());


		// emitting with custom FluxSink
		var generator = new NameGenerator();
		var flux = Flux.create(generator);
		flux.subscribe(Util.subscriber());

		generator.generated();

	}


}