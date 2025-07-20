package org.storck.medium.servfact.core.factory;

import org.storck.medium.servfact.api.Greeter;
import org.storck.medium.servfact.core.impl.ConsoleGreeterService;
import org.storck.medium.servfact.core.impl.LoggingGreeterService;

public class StringSpecifiedGreeterFactory {

    public static Greeter createGreeter(String greeterName) {
        return switch (greeterName) {
            case "console" -> new ConsoleGreeterService();
            case "logging" -> new LoggingGreeterService();
            default -> throw new IllegalArgumentException("Unknown greeter name: " + greeterName);
        };
    }
}
