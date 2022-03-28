package net.proselyte.springbootstrap.service;

import net.proselyte.springbootstrap.model.Role;
import net.proselyte.springbootstrap.model.User;
import net.proselyte.springbootstrap.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User findById(Long id){
        return userRepository.getOne(id);
    }

    public List<User> findAll(){
        return userRepository.findAll();
    }

    public User saveUser(User user, String[] roles){
        Set<Role> roleSet = Arrays.stream(roles)
                .map(Role::new)
                .collect(Collectors.toSet());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(roleSet);
        return userRepository.save(user);
    }

    public void deleteById(Long id){
        userRepository.deleteById(id);
    }

    @Override
    public User findByUserName(String username) {
        return userRepository.findByUsername(username);
    }
}
