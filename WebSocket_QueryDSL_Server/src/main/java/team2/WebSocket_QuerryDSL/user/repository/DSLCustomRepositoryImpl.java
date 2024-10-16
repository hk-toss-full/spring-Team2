package team2.WebSocket_QuerryDSL.user.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import team2.WebSocket_QuerryDSL.user.domain.QUser;
import team2.WebSocket_QuerryDSL.user.domain.User;

@Repository
@RequiredArgsConstructor
public class DSLCustomRepositoryImpl implements DSLCustomRepository {
    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public Long createUser(User user) {
        QUser quser = QUser.user;
        return jpaQueryFactory
                .insert(quser)
                .values(user)
                .execute();

    }
}
