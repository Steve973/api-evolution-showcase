package org.storck.medium.solution.spi;

import org.storck.medium.solution.api.MediumUser;

public interface UserProvider {

    MediumUser getCurrentUser();
}
