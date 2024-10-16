package team2.WebSocket_QuerryDSL.user.repository;

import team2.WebSocket_QuerryDSL.user.domain.User;

public interface DSLCustomRepository {

    Long createUser(User user);
}
