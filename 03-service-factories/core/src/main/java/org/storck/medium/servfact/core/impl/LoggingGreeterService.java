package org.storck.medium.servfact.core.impl;

import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.storck.medium.servfact.api.Greeter;
import org.storck.medium.servfact.api.MediumUser;

@Slf4j
@NoArgsConstructor
public class LoggingGreeterService implements Greeter {

    @Override
    public void greet(MediumUser user) {
        log.info("Hello {}!", user.name());
    }
}
