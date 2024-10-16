package team2.WebSocket_QuerryDSL.user.repository;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import team2.WebSocket_QuerryDSL.user.domain.User;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class DSLCustomRepositoryImplTest {

    private final DSLCustomRepository dslCustomRepository;

    DSLCustomRepositoryImplTest(DSLCustomRepository dslCustomRepository) {
        this.dslCustomRepository = dslCustomRepository;
    }

    @Test
    void createUserTest() {
        User user = User.builder()
                        .name("seyeon")
                        .build();

        dslCustomRepository.createUser(user);

    }
}