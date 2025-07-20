package org.storck.medium.separation.services.impl;

import org.storck.medium.separation.api.MediumUser;
import org.storck.medium.separation.api.MediumUserService;

public class ArbitraryMediumUserService implements MediumUserService {

    public MediumUser getCurrentUser() {
        return new MediumUser("API architect");
    }
}
