package org.storck.medium.solution.services.provider;

import org.storck.medium.solution.api.Greeter;
import org.storck.medium.solution.services.impl.ConsoleGreeterService;
import org.storck.medium.solution.spi.GreeterProvider;

public class ConsoleGreeterProvider implements GreeterProvider {

    @Override
    public boolean supports(String implName) {
        return "console".equalsIgnoreCase(implName);
    }

    @Override
    public Greeter createGreeter() {
        return new ConsoleGreeterService();
    }
}
