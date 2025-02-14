package org.kevin.reactor.examples.flux;

import org.kevin.reactor.examples.common.ExternalServiceClient;
import org.kevin.reactor.examples.common.Util;

public class FluxExamples {
    public static void main(String[] args) {

        var client = new ExternalServiceClient();
            client.getstoreStream().subscribe(Util.subscriber());
        Util.sleepSeconds(20);
    }


}
