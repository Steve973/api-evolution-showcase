package org.storck.medium.solution.services.impl;

import org.storck.medium.solution.api.MediumUser;
import org.storck.medium.solution.api.MediumUserService;

public class ArbitraryMediumUserService implements MediumUserService {

    public MediumUser getCurrentUser() {
        return new MediumUser("API architect");
    }
}
