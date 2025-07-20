package org.storck.medium.separation.services.provider;

import org.storck.medium.separation.api.MediumUser;
import org.storck.medium.separation.services.impl.ArbitraryMediumUserService;
import org.storck.medium.separation.spi.UserProvider;

public class ArbitraryMediumUserProvider implements UserProvider {

    @Override
    public MediumUser getCurrentUser() {
        return new ArbitraryMediumUserService().getCurrentUser();
    }
}
