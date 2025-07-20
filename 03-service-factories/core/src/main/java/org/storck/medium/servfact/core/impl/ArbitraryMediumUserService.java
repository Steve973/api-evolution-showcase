package org.storck.medium.servfact.core.impl;

import org.storck.medium.servfact.api.MediumUser;
import org.storck.medium.servfact.api.MediumUserService;

public class ArbitraryMediumUserService implements MediumUserService {

    public MediumUser getCurrentUser() {
        return new MediumUser("API architect");
    }
}
