package org.storck.medium.separation.services.impl;

import lombok.NoArgsConstructor;
import org.storck.medium.separation.api.Greeter;
import org.storck.medium.separation.api.MediumUser;

@NoArgsConstructor
public class ConsoleGreeterService implements Greeter {

    @Override
    public void greet(MediumUser user) {
        System.out.printf("Hello %s!%n", user.name());
    }
}
