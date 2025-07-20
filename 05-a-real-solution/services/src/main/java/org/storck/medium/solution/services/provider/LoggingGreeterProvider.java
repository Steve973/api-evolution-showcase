package org.storck.medium.solution.services.provider;

import org.storck.medium.solution.api.Greeter;
import org.storck.medium.solution.services.impl.LoggingGreeterService;
import org.storck.medium.solution.spi.GreeterProvider;

public class LoggingGreeterProvider implements GreeterProvider {

    @Override
    public boolean supports(String implName) {
        return "logging".equalsIgnoreCase(implName);
    }

    @Override
    public Greeter createGreeter() {
        return new LoggingGreeterService();
    }
}
