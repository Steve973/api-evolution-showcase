package org.storck.medium.faux;

import org.storck.medium.faux.api.Greeter;
import org.storck.medium.faux.api.MediumUser;
import org.storck.medium.faux.impl.ArbitraryMediumUserService;
import org.storck.medium.faux.impl.ConsoleGreeterService;
import org.storck.medium.faux.impl.LoggingGreeterService;

public class GreeterApplication {

    public static void main(String[] args) {
        String impl = args.length > 0 ? args[0] : "console";
        ArbitraryMediumUserService userService = new ArbitraryMediumUserService();
        MediumUser user = userService.getCurrentUser();
        Greeter greeter = "console".equals(impl) ?
                new ConsoleGreeterService() :
                new LoggingGreeterService();
        greeter.greet(user);
    }
}
