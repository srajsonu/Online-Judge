package ml.srajsonu.userservice.event;

import lombok.Getter;
import lombok.Setter;
import ml.srajsonu.userservice.model.User;
import org.springframework.context.ApplicationEvent;

@Getter
@Setter
public class SuccessfulRegistrationEvent extends ApplicationEvent {

    private final User registeredUser;

    public SuccessfulRegistrationEvent(User registeredUser) {
        super(registeredUser);
        this.registeredUser = registeredUser;
    }
}
