package com.example.springcloudrun.user;

import java.util.Collection;

public interface UserService {
    User createUser(User user);
    User getUser(long id);
    User updateUser(UserDTO userDTO);
    void deleteUser(long id);

    Collection<User> findAll();
}
