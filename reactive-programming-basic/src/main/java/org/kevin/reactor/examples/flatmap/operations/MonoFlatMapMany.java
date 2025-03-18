package org.kevin.reactor.examples.flatmap.operations;

import org.kevin.reactor.examples.common.Util;
import org.kevin.reactor.examples.flatmap.applications.Order;
import org.kevin.reactor.examples.flatmap.applications.OrderService;
import org.kevin.reactor.examples.flatmap.applications.PaymentService;
import org.kevin.reactor.examples.flatmap.applications.User;
import org.kevin.reactor.examples.flatmap.applications.UserService;

/*
    Sequential non-blocking IO calls!
    flatMap is used to flatten the inner publisher / to subscribe to the inner publisher
 */
public class MonoFlatMapMany {

	/*
		 We have username.
		 Get user account balance
	  */
	public static void main(String[] args) {
		UserService.getAllUsers()
				.map(User::id)
				.flatMap(OrderService::getUserOrders)
				.subscribe(Util.subscriber());

		Util.sleepSeconds(5);

	}

}
