package org.kevin.reactor.examples.flux;

import lombok.extern.slf4j.Slf4j;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

@Slf4j
public class StockPreisObserver implements Subscriber<Integer> {

    private  int balance = 1000;
    private Subscription subscription;
    private int quantity = 0;


    @Override
    public void onSubscribe(Subscription subscription) {
        subscription.request(Long.MAX_VALUE);
        this.subscription=subscription;
    }

    @Override
    public void onNext(Integer price) {
        log.info("received: {}", price);
        if(price < 90 && balance >= price){
            quantity++;
            balance = balance - price;
            log.info("bought a stock at {}. total quantity: {}, remaining balance: {}", price, quantity, balance);
        }else if(price > 110){
          if(quantity > 0 ){
              log.info("selling {} quantities at {}", quantity, price);
              balance = balance + (quantity * price);
              log.info("new balance is {} ", balance);
          }
            quantity = 0;
            subscription.cancel();
            log.info("profit: {}", (balance - 1000));
        }
    }

    @Override
    public void onError(Throwable throwable) {
        log.error("error", throwable);
    }

    @Override
    public void onComplete() {
        log.info("completed!");
    }
}
