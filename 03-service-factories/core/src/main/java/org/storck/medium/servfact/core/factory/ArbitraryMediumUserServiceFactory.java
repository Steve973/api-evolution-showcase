package org.storck.medium.servfact.core.factory;

import org.storck.medium.servfact.api.MediumUserService;
import org.storck.medium.servfact.core.impl.ArbitraryMediumUserService;

public class ArbitraryMediumUserServiceFactory {
    public static MediumUserService createUserService() {
        return new ArbitraryMediumUserService();
    }
}
