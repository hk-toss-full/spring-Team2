package team2.WebSocket_QuerryDSL.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import team2.WebSocket_QuerryDSL.user.domain.User;

public interface UserRepository extends JpaRepository<User, Long>, QuerydslPredicateExecutor<User> {
    User findFirstByName(String name);
}
