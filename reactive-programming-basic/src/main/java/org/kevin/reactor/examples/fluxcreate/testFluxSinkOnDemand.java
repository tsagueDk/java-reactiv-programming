package org.kevin.reactor.examples.fluxcreate;

import lombok.extern.slf4j.Slf4j;
import org.kevin.reactor.examples.common.Util;
import org.kevin.reactor.examples.fluxcreate.subscriber.SubscriberImpl;
import reactor.core.publisher.Flux;

@Slf4j
public class testFluxSinkOnDemand {
    public static void main(String[] args) {
        produceOnDemand();
    }

    private static void produceOnDemand(){
    var subscriber = new SubscriberImpl();
        Flux.<String>create(fluxSink -> {
           fluxSink.onRequest(request ->{
               for (int i = 0; i < request && !fluxSink.isCancelled(); i++) {
                   var name= Util.faker().name().firstName();
                   log.info("generate {}",name);
                   fluxSink.next(name);
               }
           }) ;
        }).subscribe(subscriber);

        Util.sleepSeconds(2);
        subscriber.getSubscription().request(2);
        Util.sleepSeconds(2);
        subscriber.getSubscription().request(2);
        Util.sleepSeconds(2);
        subscriber.getSubscription().cancel();
        subscriber.getSubscription().request(2);

    }
}
