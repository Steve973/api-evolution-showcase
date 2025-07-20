package org.storck.medium.blob;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class GreeterService {

    public void greet(MediumUser user) {
        System.out.printf("Hello %s!%n", user.name());
    }
}
