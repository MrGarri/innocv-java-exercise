package innocv.innocv.service;

import innocv.innocv.entity.User;
import innocv.innocv.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUser(Integer id) {
        return userRepository.findById(id).orElse(null);
    }

    public User saveUser(User user) {
        return userRepository.save(user);
    }

    public User updateUser(User user, Integer id) {

        return userRepository.findById(id)
                .map(currentUser -> {
                    currentUser.setName(user.getName());
                    currentUser.setBirthDate(user.getBirthDate());
                    return userRepository.save(currentUser);
                })
                .orElseGet(() -> {
                    user.setId(id);
                    return userRepository.save(user);
                });

    }

    public User deleteUser(Integer id) {
        Optional<User> currentUser = userRepository.findById(id);
        if (currentUser.isPresent()) {
            userRepository.deleteById(id);
            return currentUser.get();
        }

        return null;
    }

}
