package org.raghaji.street_paw_network.repository;
import org.raghaji.street_paw_network.models.BlacklistedToken;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface BlacklistedTokenRepository extends JpaRepository<BlacklistedToken, Long> {

    Optional<BlacklistedToken> findByToken(String token);
}
