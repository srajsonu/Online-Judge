package ml.srajsonu.userservice.service;

import ml.srajsonu.userservice.dto.UserDto;
import ml.srajsonu.userservice.model.User;
import ml.srajsonu.userservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder encoder;

    @Override
    public User registerUser(UserDto userDto) {

        if (userRepository.findByEmail(userDto.getEmail()) != null) {
            // TODO: Throw exceptions
        }
        User user = new User();
        user.setEmail(userDto.getEmail());
        user.setPassword(encoder.encode(userDto.getPassword())); //TODO: Encrypt Password
        user.setFullName(userDto.getFullName());
        user.setIsActive(false);

        User savedUser = userRepository.save(user);
        return savedUser;
    }
}
