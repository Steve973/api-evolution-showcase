package org.storck.medium.superficial.impl;

import lombok.NoArgsConstructor;
import org.storck.medium.superficial.api.Greeter;
import org.storck.medium.superficial.api.MediumUser;

@NoArgsConstructor
public class ConsoleGreeterService implements Greeter {

    @Override
    public void greet(MediumUser user) {
        System.out.printf("Hello %s!%n", user.name());
    }
}
