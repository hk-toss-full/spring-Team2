package team2.WebSocket_QuerryDSL.user.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import team2.WebSocket_QuerryDSL.message.domain.Message;
import team2.WebSocket_QuerryDSL.message.domain.QMessage;
import team2.WebSocket_QuerryDSL.user.domain.User;

import java.time.LocalDateTime;
import java.util.List;

@SpringBootTest
class QueryDslTest {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private JPAQueryFactory queryFactory;



}
