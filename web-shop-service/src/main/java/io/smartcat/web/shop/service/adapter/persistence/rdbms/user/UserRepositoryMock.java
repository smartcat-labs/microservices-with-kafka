package io.smartcat.web.shop.service.adapter.persistence.rdbms.user;

import io.smartcat.web.shop.service.domain.user.User;
import io.smartcat.web.shop.service.domain.user.UserRepository;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.Optional;

import static io.smartcat.web.shop.service.adapter.persistence.rdbms.user.model.UserGenerator.initializeUserRepository;

/**
 * Mocked User Repository implemented for the sake of simplicity.
 */
@Repository
public class UserRepositoryMock implements UserRepository {

    private final Map<String, User> users;

    public UserRepositoryMock() {
        this.users = initializeUserRepository();
    }

    @Override
    public Optional<User> findCurrentUser() {
        return users.values().stream().findFirst();
    }
}
