package org.storck.medium.superficial;

import org.storck.medium.superficial.api.Greeter;
import org.storck.medium.superficial.api.MediumUser;
import org.storck.medium.superficial.impl.ArbitraryMediumUserService;
import org.storck.medium.superficial.impl.ConsoleGreeterService;
import org.storck.medium.superficial.impl.LoggingGreeterService;

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
