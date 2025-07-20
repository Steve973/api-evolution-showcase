package org.storck.medium.solution.services.provider;

import org.storck.medium.solution.api.MediumUser;
import org.storck.medium.solution.services.impl.ArbitraryMediumUserService;
import org.storck.medium.solution.spi.UserProvider;

public class ArbitraryMediumUserProvider implements UserProvider {

    @Override
    public MediumUser getCurrentUser() {
        return new ArbitraryMediumUserService().getCurrentUser();
    }
}
