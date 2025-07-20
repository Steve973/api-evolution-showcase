package org.storck.medium.faux.impl;

import lombok.NoArgsConstructor;
import org.storck.medium.faux.api.Greeter;
import org.storck.medium.faux.api.MediumUser;

@NoArgsConstructor
public class ConsoleGreeterService implements Greeter {

    @Override
    public void greet(MediumUser user) {
        System.out.printf("Hello %s!%n", user.name());
    }
}
