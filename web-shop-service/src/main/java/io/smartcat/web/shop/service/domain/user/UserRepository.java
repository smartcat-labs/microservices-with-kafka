package io.smartcat.web.shop.service.domain.user;

import java.util.Optional;

public interface UserRepository {

    /**
     *  Try to find currently logged user if exists.
     *
     * @return Optional with {@link User} if exists.
     */
    Optional<User> findCurrentUser();
}
