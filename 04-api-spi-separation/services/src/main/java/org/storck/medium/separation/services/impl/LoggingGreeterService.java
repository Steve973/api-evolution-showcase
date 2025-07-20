package org.storck.medium.separation.services.impl;

import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.storck.medium.separation.api.Greeter;
import org.storck.medium.separation.api.MediumUser;

@Slf4j
@NoArgsConstructor
public class LoggingGreeterService implements Greeter {

    @Override
    public void greet(MediumUser user) {
        log.info("Hello {}!", user.name());
    }
}
