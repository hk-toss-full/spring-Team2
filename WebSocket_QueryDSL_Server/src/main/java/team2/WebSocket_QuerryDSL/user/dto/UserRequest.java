package team2.WebSocket_QuerryDSL.user.dto;

import team2.WebSocket_QuerryDSL.user.domain.User;


public record UserRequest(
        String name
) {
    public User toEntity(){
        return User.builder()
                .name(name)
                .build();
    }
}