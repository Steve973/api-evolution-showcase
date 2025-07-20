package org.storck.medium.servfact.core.impl;

import lombok.NoArgsConstructor;
import org.storck.medium.servfact.api.Greeter;
import org.storck.medium.servfact.api.MediumUser;

@NoArgsConstructor
public class ConsoleGreeterService implements Greeter {

    @Override
    public void greet(MediumUser user) {
        System.out.printf("Hello %s!%n", user.name());
    }
}
