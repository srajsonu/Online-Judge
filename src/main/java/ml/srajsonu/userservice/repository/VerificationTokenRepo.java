package ml.srajsonu.userservice.repository;

import ml.srajsonu.userservice.model.VerificationToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VerificationTokenRepo extends JpaRepository<VerificationToken, Long> {

    VerificationToken findByToken(String token);
}
