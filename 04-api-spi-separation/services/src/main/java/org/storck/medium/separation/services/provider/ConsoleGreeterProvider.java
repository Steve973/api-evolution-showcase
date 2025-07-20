package org.storck.medium.separation.services.provider;

import org.storck.medium.separation.api.Greeter;
import org.storck.medium.separation.services.impl.ConsoleGreeterService;
import org.storck.medium.separation.spi.GreeterProvider;

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
