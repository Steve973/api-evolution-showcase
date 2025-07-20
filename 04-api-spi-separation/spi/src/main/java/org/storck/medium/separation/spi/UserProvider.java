package org.storck.medium.separation.spi;

import org.storck.medium.separation.api.MediumUser;

public interface UserProvider {

    MediumUser getCurrentUser();
}
