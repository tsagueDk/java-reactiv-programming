package org.kevin.reactor.examples.fluxcreate.assignment;

import org.kevin.reactor.examples.common.Util;
import org.kevin.reactor.examples.fluxcreate.assignment.client.ExternalServiceClient;

public class AssignmentFluxOperator {
    public static void main(String[] args) {

        //only product-names from 1..4 are available
        ExternalServiceClient client = new ExternalServiceClient();
//        for (int i=1; i<=5; i++){
//            client.getProductName(i).subscribe(Util.subscriber());
//        }
        client.getProductName(3).subscribe(Util.subscriber());

        Util.sleepSeconds(3);
    }
}
