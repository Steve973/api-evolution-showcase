package org.storck.medium.servfact.core;

import org.storck.medium.servfact.api.Greeter;
import org.storck.medium.servfact.api.MediumUser;
import org.storck.medium.servfact.api.MediumUserService;
import org.storck.medium.servfact.core.factory.ArbitraryMediumUserServiceFactory;
import org.storck.medium.servfact.core.factory.StringSpecifiedGreeterFactory;

public class GreeterApplication {

    public static void main(String[] args) {
        String impl = args.length > 0 ? args[0] : "console";
        MediumUserService userService = ArbitraryMediumUserServiceFactory.createUserService();
        MediumUser user = userService.getCurrentUser();
        Greeter greeter = StringSpecifiedGreeterFactory.createGreeter(impl);
        greeter.greet(user);
    }
}
