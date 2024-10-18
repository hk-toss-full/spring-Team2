# Web Socket - HTTP 통신과 소켓 통신의 차이
## HTTP 통신
- 클라이언트의 **요청이 있을 때만** 서버가 응답
- JSON, HTML, Image 등 다양한 데이터를 주고 받을 수 있음
- **서버가 응답한 후 연결을 바로 종료**하는 단방향 통신이지만 Keep Alive 옵션을 주어 일정 시간동안 커넥션을 유지할 수 있다.
- 실시간 연결이 아닌 데이터 전달이 필요한 경우에만 요청을 보내는 상황에 유리

## 소켓 통신
- 클라이언트와 서버가 특정 포트 번호 또는 IP 주소를 통해 **양방향 통신**을 하는 방식
- 데이터 전달 후 연결이 끊어지는 것이 아니라 **계속해서 연결을 유지** → HTTP에 비해 더 많은 리소스 소모
- 클라이언트와 서버가 실시간으로 계속하여 데이터를 주고 받아야 하는 경우에 유리
- 실시간 동영상 스트리밍, 온라인 게임, 채팅 등에 사용

<hr/>

# 소켓 통신 프로토콜
## TCP
- `SOCK_STREAM` 타입으로 생성. 즉, 데이터를 연속적으로 흐르게 하여 패킷 단위가 아닌 바이트 스트림으로 처리하므로 송수신되는 데이터의 크기에 관계없이 연결을 유지하면서 연속적인 데이터를 주고받을 수 있음
- 데이터가 순서대로 도착하고 손실이 발생하지 않도록 보장
- 연결 지향적이고 신뢰성을 제공
- 웹 브라우징(HTTP/HTTPS), 이메일(SMTP, IMAP), 파일 전송(FTP) 등 중요한 데이터를 주고받는 상황에서는 TCP가 더 적합

## UDP
- `SOCK_DGRAM` 타입으로 생성되며, 비연결 지향적
- 데이터 전송 속도가 빠르지만, 신뢰성이나 순서 보장은 하지 않음
- 온라인 게임, 음성 통화 등 실시간성이나 속도가 중요한 경우에는 UDP가 더 유리할 수 있음

<hr/>

# 일반적인 소켓 통신의 흐름

![Image](https://github.com/user-attachments/assets/75cbef10-45e2-4a03-96f9-c34dae8758c9)

### 서버
1. 소켓 생성
2. 바인딩 (ip, port번호 설정)
3. listen()으로 클라이언트 요청에 대기열을 만들어 몇개의 클라이언트를 대기시킬지 결정
4. accept()로 클라이언트와 연결
5. 데이터 송수신
6. 소켓 닫기

### 클라이언트
1. 소켓 생성
2. 서버에 설정된 ip, port로 연결 시도
3. accept()로 클라이언트의 socket descriptor 반환
4. 데이터 송수신
5. 소켓 닫기

<hr/>

## Socket의 장점
1. **실시간 양방향 통신**: 웹소켓은 클라이언트와 서버 간의 실시간 양방향 통신을 가능하게 하며, 한 번 연결되면 양쪽에서 자유롭게 데이터를 주고받을 수 있음. 이를 통해 채팅, 게임, 주식 가격 업데이트 등 실시간 기능을 효율적으로 구현 가능
2. **낮은 오버헤드**: HTTP처럼 요청과 응답을 반복하는 방식이 아니라, 한 번 연결된 소켓을 통해 지속적으로 데이터를 주고받기 때문에 오버헤드가 적고, 대용량 실시간 데이터 전송에 적합함
3. **지속적인 연결**: 웹소켓은 연결이 유지되기 때문에 매번 새로 연결을 생성할 필요가 없어 서버와 클라이언트 간의 상호작용이 더 빠르고 효율적임

## 단점
1. **방화벽 및 프록시 문제**: 웹소켓은 일반 HTTP나 HTTPS와 다른 방식으로 작동하기 때문에 일부 방화벽이나 프록시 서버에서 웹소켓 트래픽을 차단할 수 있음
2. **복잡성**: 웹소켓을 사용하면 기존 HTTP 기반 애플리케이션보다 더 복잡한 구조를 요구할 수 있으며 실시간 데이터 관리 및 지속적인 연결을 유지하는 코드를 작성하는 것이 어려울 수 있음
3. **대역폭 문제**: 지속적인 연결이 이루어지기 때문에 비효율적인 코딩이나 과도한 트래픽을 발생시키면 서버의 대역폭을 많이 차지할 수 있음

<hr/>

## 왜 Socket을 쓰나요?
1. **실시간 통신**: 소켓은 클라이언트와 서버 간에 실시간으로 데이터를 전송할 수 있어, 즉각적인 반응이 필요한 애플리케이션(채팅, 게임 등)에 적합함
2. **양방향 데이터 전송**: 소켓을 통해 두 방향으로 데이터를 송수신할 수 있어, 클라이언트와 서버 간의 상호작용이 원활합니다.
3. **프로토콜 지원**: TCP/IP, UDP 등 다양한 프로토콜을 지원하여, 필요한 전송 방식에 맞춰 선택할 수 있음. TCP는 안정적인 전송, UDP는 빠른 전송 제공
4. **네트워크 통신의 표준화**: 소켓 API는 다양한 언어와 플랫폼에서 사용할 수 있어, 이식성이 뛰어나고 개발이 용이
5. **다중 연결 처리**: 소켓은 여러 클라이언트와의 동시 연결을 지원하므로, 서버가 동시에 여러 사용자의 요청을 처리할 수 있음

<hr/>

## Socket 사용 기업
### 게임 회사:
- Riot Games (리그 오브 레전드): 실시간 멀티플레이어 게임에서 소켓을 사용해 플레이어 간의 데이터 전송 관리
- Epic Games (포트나이트): 실시간 데이터 전송을 위해 소켓 프로그래밍을 활용

### 메신저 앱:
- Slack: 팀 간의 실시간 메시지 전송과 업데이트
- WhatsApp: 메시지 전송과 실시간 알림

### 비디오 스트리밍 서비스:
- Netflix: 사용자와 서버 간의 실시간 통신을 위해 소켓을 활용해 비디오 데이터 스트리밍
- Twitch: 실시간 스트리밍과 채팅 기능

### 소셜 미디어:
- Facebook: 실시간 알림과 채팅 기능
- Twitter: 실시간 피드 업데이트 및 사용자 간의 상호작용

### IoT 기업:
- Nest: IoT 장치 간의 통신을 위해 소켓을 사용하여 데이터 전송
- Philips Hue: 스마트 조명 제어에 소켓을 사용해 실시간으로 조명을 조절

<hr/>

## Socket을 어디에 적용할 수 있을까
유윤지 : 사용자가 청약 신청을 완료하거나 새로운 주택 정보가 등록될 때, 청약 공고 찜 목록에 실시간 업데이트할 때, 웹소켓을 통해 즉시 알림 전송 + 사용자가 실시간으로 신청 상태 확인 및 찜 목록 확인 가능
이세연 : 가상화폐 시세 데이터를 가져올 때 새로고침이 아니라 소켓을 통해 실시간으로 업데이트 되도록 적용하면 좋을 것 같다.
이승진 : 지도에 표시된 가게들의 영업중, 영업마감 정보를 실시간으로 받아올 수 있을 거 같다.
조경준 : TravelTap 환율데이터를 보여주는 곳에서  웹소켓을 사용하여 실시간 환율정보를 가져와 실시간으로 갱신하는곳에 적용 가능 하다고 생각된다.

<hr/>

## Polling 동작 방식
1. 클라이언트가 주기적으로 서버에 요청을 보냄
2. 서버는 요청을 받고, 현재 상태(데이터)를 확인
3. 서버가 데이터가 변경되었는지 여부에 따라 응답을 보냄
4. 클라이언트는 응답을 받고, 필요한 경우 UI를 업데이트
5. 이 과정을 주기적으로 반복

예) 클라이언트가 5초마다 서버에 GET 요청을 보내 새로운 뉴스 기사를 확인하는 경우

<hr/>

## Long Polling 동작 방식
1. 클라이언트가 서버에 요청을 보냄
2. 서버는 즉시 응답하지 않고, 데이터가 준비될 때까지 대기
3. 데이터가 준비되면 서버가 클라이언트에 응답
4. 클라이언트는 응답을 받고 필요한 작업(예: UI 업데이트)을 수행
5. 클라이언트는 다시 서버에 새로운 요청을 보내어 이 과정을 반복

예) 채팅 애플리케이션에서 클라이언트가 메시지를 기다리며 요청을 보내고, 서버가 새로운 메시지가 도착하면 응답하는 경우

<hr/>

## Streaming 동작 방식
1. 클라이언트가 서버에 연결을 요청 (예: WebSocket 연결)
2. 서버가 클라이언트의 연결 요청을 수락하고, 양방향 연결이 설정됨
3. 클라이언트와 서버는 이 연결을 통해 데이터를 실시간으로 주고받고, 데이터가 발생하면 즉시 전송됨
4. 연결이 유지되는 동안, 클라이언트와 서버는 언제든지 데이터를 전송할 수 있음
5. 통신이 끝나면 클라이언트나 서버가 연결을 닫음

예) 온라인 게임, 실시간 주식 거래 애플리케이션, 또는 WebSocket을 사용하는 채팅 서비스에서 데이터가 실시간으로 전송되는 경우

**=> 소켓을 사용할 때, 통신 방식으로는 Streaming이 가장 적합함**

<hr/>

## 차이점 정리

<Image src=https://github.com/user-attachments/assets/1976aa54-2b53-4dce-8c21-160e1ff0ab7c width=800 />

<hr/>

# QueryDSL
https://spiny-dedication-748.notion.site/Query-DSL-11f68897439480ce9053ea8e87f3ad4b?pvs=4
