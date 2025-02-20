package org.kevin.reactor.examples.fluxcreate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.kevin.reactor.examples.common.Util;
import reactor.core.publisher.Flux;

import java.util.function.Function;
import java.util.function.UnaryOperator;

@Slf4j
public class TestTransformOperator {
    public static void main(String[] args) {

        var isConditionEnabled = false;

        // as condition is false, addDebugger methode won't call, Function::identidy --> return at is it
        getCustomers()
                .transform(isConditionEnabled? addDebugger():Function.identity()).subscribe(Util.subscriber());
        getPurchaseOrders()
                .transform(addDebugger()).subscribe(Util.subscriber());

//        getCustomers()
//                .doOnNext(i -> log.info("received this {}",i))
//                .doOnComplete(()-> log.info("Complete"))
//                .doOnNext(i -> log.info("caught this error {}",i))
//                .subscribe(Util.subscriber());
//        getPurchaseOrders()
//                .doOnNext(i -> log.info("received this {}",i))
//                .doOnComplete(()-> log.info("Complete"))
//                .doOnNext(i -> log.info("caught this error {}",i))
//                .subscribe(Util.subscriber());

    }
    private  static Flux<Customer> getCustomers(){
       return Flux.range(1,3)
                .map(i-> new Customer(i,Util.faker().commerce().productName()));
    }
    private  static Flux<PurchaseOrder> getPurchaseOrders(){
       return Flux.range(1,5)
                .map(i-> new PurchaseOrder(Util.faker().commerce().productName(),10.0,i));
    }

    private static <T> UnaryOperator<Flux<T>> addDebugger(){
        return  flux ->flux.doOnNext(i -> log.info("received this {}",i))
                .doOnComplete(()-> log.info("Complete"))
                .doOnNext(i -> log.info("caught this error {}",i));
    }

    record Customer(int id,String name) {

    }
    record PurchaseOrder(String name, double price,int quantity) {

    }
}
