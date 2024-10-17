package team2.WebSocket_QuerryDSL.user.dto;

import team2.WebSocket_QuerryDSL.user.domain.User;

public record UserDto(
        Long id,
        String name
) {
    public static UserDto from(User user) {
        return new UserDto(user.getId(), user.getName());
    }
}
