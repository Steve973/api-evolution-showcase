package org.storck.medium.separation.spi;

import org.storck.medium.separation.api.Greeter;

public interface GreeterProvider {

    /**
     * Returns true if this provider supports the given implementation name.
     */
    boolean supports(String implName);

    /**
     * Returns a configured Greeter instance.
     */
    Greeter createGreeter();
}
