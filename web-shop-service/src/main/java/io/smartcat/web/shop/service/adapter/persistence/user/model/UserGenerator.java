package io.smartcat.web.shop.service.adapter.persistence.user.model;

import io.smartcat.web.shop.service.domain.user.User;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static java.util.function.Function.identity;
import static java.util.stream.Collectors.toMap;

/**
 * Class responsible for generating {@link User} objects.
 */
public abstract class UserGenerator {

    public static Map<String, User> initializeUserRepository() {
        return users().stream().collect(toMap(User::getId, identity()));
    }

    private static List<User> users() {
        return Arrays.asList(
            new User("u1", "username1", "password1"),
            new User("u2", "username2", "password2")
        );
    }
}
