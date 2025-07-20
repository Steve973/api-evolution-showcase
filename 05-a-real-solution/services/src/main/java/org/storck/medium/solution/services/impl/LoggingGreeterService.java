package org.storck.medium.solution.services.impl;

import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.storck.medium.solution.api.Greeter;
import org.storck.medium.solution.api.MediumUser;

@Slf4j
@NoArgsConstructor
public class LoggingGreeterService implements Greeter {

    @Override
    public void greet(MediumUser user) {
        log.info("Hello {}!", user.name());
    }
}
