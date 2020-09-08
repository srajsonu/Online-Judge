package ml.srajsonu.userservice.service;

import ml.srajsonu.userservice.dto.UserDto;
import ml.srajsonu.userservice.event.SuccessfulRegistrationEvent;
import ml.srajsonu.userservice.model.User;
import ml.srajsonu.userservice.model.VerificationToken;
import ml.srajsonu.userservice.repository.UserRepository;
import ml.srajsonu.userservice.repository.VerificationTokenRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    ApplicationEventPublisher applicationEventPublisher;

    @Autowired
    VerificationTokenRepo verificationTokenRepo;

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

        applicationEventPublisher.publishEvent(
                new SuccessfulRegistrationEvent(savedUser)
        );
        return savedUser;
    }

    @Override
    public User validateUser(String token) {

        // TODO: Check token is availabe or not
        VerificationToken verificationToken = verificationTokenRepo.findByToken(token);

        if (verificationToken == null) {
            return null;
        }
        User tokenUser = verificationToken.getUser();

        if (verificationToken.getExpiryTime().getTime() - new Date().getTime() > 0) {

            User verifiedUser = verificationToken.getUser();
            verifiedUser.setIsActive(true);

            userRepository.save(verifiedUser);

            return verifiedUser;

        } else {
            return null;
        }
    }
}
