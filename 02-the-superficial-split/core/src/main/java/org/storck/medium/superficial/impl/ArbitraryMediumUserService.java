package org.storck.medium.superficial.impl;

import org.storck.medium.superficial.api.MediumUser;
import org.storck.medium.superficial.api.MediumUserService;

public class ArbitraryMediumUserService implements MediumUserService {

    public MediumUser getCurrentUser() {
        return new MediumUser("API architect");
    }
}
