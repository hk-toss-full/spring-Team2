package team2.WebSocket_QuerryDSL.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import team2.WebSocket_QuerryDSL.user.domain.User;

public interface UserRepository extends JpaRepository<User, Long> {
}
