package net.proselyte.springbootstrap.service;


import net.proselyte.springbootstrap.model.User;

import java.util.List;

public interface UserService {
    public User findById(Long id);

    public List<User> findAll();

    public User saveUser(User user, String[] roles);

    public void deleteById(Long id);

    User findByUserName(String username);
}
