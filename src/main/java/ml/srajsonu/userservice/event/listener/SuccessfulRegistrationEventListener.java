package ml.srajsonu.userservice.event.listener;

import ml.srajsonu.userservice.event.SuccessfulRegistrationEvent;
import ml.srajsonu.userservice.model.User;
import ml.srajsonu.userservice.model.VerificationToken;
import ml.srajsonu.userservice.repository.VerificationTokenRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class SuccessfulRegistrationEventListener implements ApplicationListener<SuccessfulRegistrationEvent> {

    @Autowired
    VerificationTokenRepo verificationTokenRepo;

    @Override
    public void onApplicationEvent(SuccessfulRegistrationEvent successfulRegistrationEvent) {

        User registeredUser = successfulRegistrationEvent.getRegisteredUser();

        VerificationToken token = new VerificationToken(registeredUser);

        verificationTokenRepo.save(token);


    }
}
