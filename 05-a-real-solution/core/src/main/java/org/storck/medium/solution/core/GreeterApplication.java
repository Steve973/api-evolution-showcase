package org.storck.medium.solution.core;

import org.storck.medium.solution.api.Greeter;
import org.storck.medium.solution.spi.GreeterProvider;
import org.storck.medium.solution.spi.UserProvider;

import java.util.ServiceLoader;

public class GreeterApplication {

    public static void main(String[] args) {
        String impl = args.length > 0 ? args[0] : "console";

        // Discover a matching GreeterProvider
        GreeterProvider greeterProvider = ServiceLoader.load(GreeterProvider.class)
                .stream()
                .map(ServiceLoader.Provider::get)
                .filter(p -> p.supports(impl))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("No GreeterProvider found for: " + impl));

        // Discover a UserProvider (grab the first one for now)
        UserProvider userProvider = ServiceLoader.load(UserProvider.class)
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("No UserProvider implementation found."));

        Greeter greeter = greeterProvider.createGreeter();
        greeter.greet(userProvider.getCurrentUser());
    }
}
