package engine.repositoryServices;

import engine.UserRepository;
import engine.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void save(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        User userDb = userRepository.save(user);
        System.out.println("userDb = " + userDb);
    }

    public boolean exist(User user) {
        String email = user.getEmail();
        Optional<User> userDb = userRepository.findByEmail(email);
        return userDb.isPresent();
    }

    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email)
                .get();
    }
}
