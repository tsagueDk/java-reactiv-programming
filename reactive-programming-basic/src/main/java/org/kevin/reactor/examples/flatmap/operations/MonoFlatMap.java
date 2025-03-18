package org.kevin.reactor.examples.flatmap.operations;

import org.kevin.reactor.examples.common.Util;
import org.kevin.reactor.examples.flatmap.applications.PaymentService;
import org.kevin.reactor.examples.flatmap.applications.UserService;

/*
    Sequential non-blocking IO calls!
    flatMap is used to flatten the inner publisher / to subscribe to the inner publisher
 */
public class MonoFlatMap {

	/*
		 We have username.
		 Get user account balance
	  */
	public static void main(String[] args) {
		UserService.getUserId("mike")
				.flatMap(PaymentService::getUserBalance)
				.subscribe(Util.subscriber());
	}

}
