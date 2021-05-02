package engine;

import engine.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void saveNewUser(User user) {
        System.out.println("UserService.saveNewUser");
        userRepository.save(user);
    }

    public boolean userExists(String email) {
        System.out.println("UserService.userExists");
        return userRepository.findByEmail(email)
                .isPresent();
    }
}
