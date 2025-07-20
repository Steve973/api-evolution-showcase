package org.storck.medium.solution.services.impl;

import lombok.NoArgsConstructor;
import org.storck.medium.solution.api.Greeter;
import org.storck.medium.solution.api.MediumUser;

@NoArgsConstructor
public class ConsoleGreeterService implements Greeter {

    @Override
    public void greet(MediumUser user) {
        System.out.printf("Hello %s!%n", user.name());
    }
}
