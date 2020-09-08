package ml.srajsonu.userservice.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

@Entity
@Getter
@Setter
public class VerificationToken {

    public VerificationToken() {
        super();
    }

    private static final int VALIDITY_TIME = 4 * 60;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String token;

    @OneToOne(targetEntity = User.class)
    private User user;

    private Date expiryTime;

    public VerificationToken(User user) {
        String token = generateRandomUniqueToken();

        this.token = token;
        this.user = user;

        this.expiryTime = calculateExpiry();
    }

    public void updateToken() {
        this.token = generateRandomUniqueToken();
        this.expiryTime = calculateExpiry();
    }

    private String generateRandomUniqueToken() {
        return UUID.randomUUID().toString();
    }

    private Date calculateExpiry() {

        Calendar calendar = Calendar.getInstance();

        Date currDateTime = new Date();

        calendar.setTimeInMillis(currDateTime.getTime());

        calendar.add(calendar.MINUTE, VALIDITY_TIME);

        return new Date(calendar.getTime().getTime());
    }

}
