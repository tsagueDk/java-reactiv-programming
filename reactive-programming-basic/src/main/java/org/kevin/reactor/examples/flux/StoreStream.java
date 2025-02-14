package org.kevin.reactor.examples.flux;

import org.kevin.reactor.examples.common.ExternalServiceClient;
import org.kevin.reactor.examples.common.Util;

public class StoreStream {
    public static void main(String[] args) {
        var client = new ExternalServiceClient();
        var subscriber = new StockPreisObserver();
        client.getstoreStream().subscribe(subscriber);
        Util.sleepSeconds(20);
    }
}
