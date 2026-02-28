package io.github.Cristian3116.jobtrackr.repository;

import io.github.Cristian3116.jobtrackr.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
}
