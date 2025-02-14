package org.kevin.reactor.examples.mono;

import org.kevin.reactor.examples.common.Util;
import org.kevin.reactor.examples.common.ExternalServiceClient;

public class MonoNonBlocking {
    public static void main(String[] args) {

        var client = new ExternalServiceClient();

        for (int i = 1; i <= 100; i++) {
            client.getProductName(i)
                    .subscribe(Util.subscriber());
        }

        Util.sleepSeconds(5);
    }


}
