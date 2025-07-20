package org.storck.medium.faux.impl;

import org.storck.medium.faux.api.MediumUser;
import org.storck.medium.faux.api.MediumUserService;

public class ArbitraryMediumUserService implements MediumUserService {
    
    public MediumUser getCurrentUser() {
        return new MediumUser("API architect");
    }
}
