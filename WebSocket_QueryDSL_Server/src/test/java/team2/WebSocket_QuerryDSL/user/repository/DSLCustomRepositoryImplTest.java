package team2.WebSocket_QuerryDSL.user.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import team2.WebSocket_QuerryDSL.message.domain.Message;
import team2.WebSocket_QuerryDSL.message.domain.QMessage;
import team2.WebSocket_QuerryDSL.user.domain.User;
import team2.WebSocket_QuerryDSL.user.service.UserServiceImpl;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class DSLCustomRepositoryImplTest {
    @Autowired
    private DSLCustomRepository dslCustomRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private JPAQueryFactory queryFactory;
    @Autowired
    private UserServiceImpl userServiceImpl;


    /**
     * 이 테스트는 특정 사용자가 정해진 시간 범위 내에서 보낸 메시지를 검색합니다.
     * QueryDSL을 활용하여 동적 쿼리를 구성하고 실행하는 과정을 보여줍니다.
     */
    @Test
    public void testSearchMessagesByUserAndDateTimeRange() {
        // 사용자 이름을 통해 사용자 정보를 검색합니다.
        User user = userRepository.findByName("조경준");
        if (user == null) {
            fail("User not found, test cannot proceed");
            return;
        }

        // 검색할 메시지의 시간 범위를 설정합니다.
        LocalDateTime startTime = LocalDateTime.now().minusDays(1); // 현재 시간에서 하루 전
        LocalDateTime endTime = LocalDateTime.now(); // 현재 시간

        // QueryDSL 쿼리를 구성하여 사용자와 시간 조건에 맞는 메시지를 검색합니다.
        List<Message> messages = queryFactory
                .selectFrom(QMessage.message1)
                .where(QMessage.message1.user.eq(user) // 사용자 조건
                        .and(QMessage.message1.createdAt.between(startTime, endTime))) // 시간 조건
                .fetch(); // 쿼리 실행 및 결과 페치

        // 검색 결과가 비어있지 않은지 확인합니다.
        assertFalse(messages.isEmpty(), "No messages found in the specified range");

        // 모든 검색된 메시지가 주어진 시간 범위 안에 있는지 검증합니다.
        messages.forEach(message -> {
            assertTrue(message.getCreatedAt().isAfter(startTime) && message.getCreatedAt().isBefore(endTime),
                    "Message timestamp is out of the specified range");
        });
    }
    // 특정 키워드가 포함된 메시지를 검색하
    @Test
    public void testSearchMessagesByUserAndKeyword() {
        // 사용자 이름을 통해 사용자 정보를 검색합니다.
        User user = userRepository.findByName("조경준");
        if (user == null) {
            fail("User not found, test cannot proceed");
            return;
        }

        String keyword = "중요"; // 검색할 메시지에 포함된 키워드

        // QueryDSL을 사용하여 사용자가 보낸 메시지 중 특정 키워드가 포함된 메시지를 검색합니다.
        List<Message> messages = queryFactory
                .selectFrom(QMessage.message1)
                .where(QMessage.message1.user.eq(user) // 사용자 조건
                        .and(QMessage.message1.message.contains(keyword))) // 키워드 조건
                .fetch(); // 쿼리 실행 및 결과 페치

        // 검색된 메시지들이 비어있지 않은지 확인
        assertFalse(messages.isEmpty(), "No messages found containing the keyword");

        // 모든 검색된 메시지가 키워드를 포함하는지 검증
        messages.forEach(message -> {
            assertTrue(message.getMessage().contains(keyword), "Message does not contain the expected keyword");
        });
    }
    //특정 시간 범위 내에서 사용자가 보낸 메시지를 최신 순으로 정렬하여 검색
    @Test
    public void testSearchMessagesByUserAndDateTimeRangeSorted() {
        // 사용자 이름을 통해 사용자 정보를 검색합니다.
        User user = userRepository.findByName("조경준");
        if (user == null) {
            fail("User not found, test cannot proceed");
            return;
        }

        // 시간 범위 설정
        LocalDateTime startTime = LocalDateTime.now().minusDays(1);
        LocalDateTime endTime = LocalDateTime.now();

        // QueryDSL을 사용하여 시간 조건과 정렬 조건을 가진 메시지 검색
        List<Message> messages = queryFactory
                .selectFrom(QMessage.message1)
                .where(QMessage.message1.user.eq(user) // 사용자 조건
                        .and(QMessage.message1.createdAt.between(startTime, endTime))) // 시간 범위 조건
                .orderBy(QMessage.message1.createdAt.desc()) // 최신 순 정렬
                .fetch(); // 쿼리 실행 및 결과 페치

        // 검색된 메시지들이 비어있지 않은지 확인
        assertFalse(messages.isEmpty(), "No messages found in the specified range");

        // 검색된 메시지들이 최신 순으로 정렬되었는지 확인
        LocalDateTime previousTime = endTime;
        for (Message message : messages) {
            assertTrue(message.getCreatedAt().isBefore(previousTime) || message.getCreatedAt().isEqual(previousTime),
                    "Messages are not sorted by date correctly");
            previousTime = message.getCreatedAt();
        }
    }
    //특정 사용자가 특정 채팅방에서 보낸 메시지만 검색
    @Test
    public void testSearchMessagesByUserAndChatRoom() {
        // 사용자 정보 검색
        User user = userRepository.findByName("조경준");
        if (user == null) {
            fail("User not found, test cannot proceed");
            return;
        }

        String chatRoomName = "프론트엔드"; // 채팅방 이름

        // QueryDSL을 사용하여 사용자가 특정 채팅방에서 보낸 메시지를 검색
        List<Message> messages = queryFactory
                .selectFrom(QMessage.message1)
                .join(QMessage.message1.chatRoom) // 채팅방과 조인
                .where(QMessage.message1.user.eq(user) // 사용자 조건
                        .and(QMessage.message1.chatRoom.title.eq(chatRoomName))) // 채팅방 조건
                .fetch(); // 쿼리 실행 및 결과 페치

        // 검색된 메시지들이 비어있지 않은지 확인
        assertFalse(messages.isEmpty(), "No messages found in the specified chat room");

        // 모든 검색된 메시지가 지정된 채팅방에서 온 것인지 확인
        messages.forEach(message -> {
            assertEquals(chatRoomName, message.getChatRoom().getTitle(), "Message is not from the expected chat room");
        });
    }
    //작성자가 없을 경우 메시지 목록을 반환하는 동적 조건을 적용한 쿼리
        @Test
        public void testSearchMessagesForExistingUser() {
            // 사용자 이름으로 사용자 정보를 검색합니다.
            User user = userRepository.findByName("조경준");
            List<Message> messages = queryFactory
                    .selectFrom(QMessage.message1)
                    .where(user != null ? QMessage.message1.user.eq(user) : null) // 동적 조건 적용
                    .fetch(); // 쿼리 실행 및 결과 페치

            // 존재하는 사용자에 대한 메시지가 비어 있지 않은지 확인합니다.
            assertNotNull(user, "User '조경준' should exist");
            assertFalse(messages.isEmpty(), "No messages found for the existing user");
        }
}
