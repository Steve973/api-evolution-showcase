package org.storck.medium.separation.services.provider;

import org.storck.medium.separation.api.Greeter;
import org.storck.medium.separation.services.impl.LoggingGreeterService;
import org.storck.medium.separation.spi.GreeterProvider;

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
