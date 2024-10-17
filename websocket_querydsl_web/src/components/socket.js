/* Socket.js */
import React, { useEffect, useState, useRef } from 'react';
import './Chat.css';

function Socket({ username, chatRoom }) {
  const [message, setMessage] = useState([]);
  const [ws, setWs] = useState(null);
  const [isConnected, setIsConnected] = useState(false);
  const [inputMessage, setInputMessage] = useState('');
  const [userId, setUserid] = useState(
    JSON.parse(sessionStorage.getItem('userData')).id
  );
  const [userName, setUserName] = useState(
    JSON.parse(sessionStorage.getItem('userData')).name
  );
  const [chatRoomId, setChatRoomId] = useState(
    sessionStorage.getItem('chatRoomId')
  );
  const messagesEndRef = useRef(null);
  // const [chatRoom, setChatRoom] = useState(chatRoom);

  // WebSocket 연결 함수
  const connectWebSocket = () => {
    const websocketUrl = 'ws://192.168.1.19:8080/ws';
    const webSocket = new WebSocket(websocketUrl);
    
    webSocket.onopen = () => {
      console.log('WebSocket 연결 성공');
      setIsConnected(true);
      //webSocket.send(JSON.stringify({ type: 'join', chatRoom, username }));
    };

    webSocket.onmessage = (event) => {
      // 서버로부터 받은 메시지 처리
      console.log(event.data);
      const parsedData = JSON.parse(event.data);
      console.log(parsedData);
      const isMyMessage = parsedData.userId === userId;
      setMessage((prevMessages) => [...prevMessages, { sender: isMyMessage ? 'me' : 'server', text: `${parsedData.username}: ${parsedData.message}` }]);
    };

    webSocket.onclose = () => {
      console.log('WebSocket 연결 종료');
      setIsConnected(false);
    };

    setWs(webSocket);
  };

  // WebSocket 해제 함수
  const disconnectWebSocket = () => {
    if (ws) {
      ws.close();
      console.log('WebSocket 연결 해제');
      setWs(null);
    }
  };

  // 메시지 전송 함수
  const sendMessage = () => {
    if (ws && isConnected && inputMessage.trim() !== '') {
      const messageToSend = JSON.stringify(
        {
          userId:`${userId}`,
          username:`${userName}`,
          chatRoomId:`${chatRoomId}`,
          message:`${inputMessage}`
        }
      )
      ws.send(messageToSend);
      console.log(messageToSend); // 서버로 메시지 전송
      setMessage((prevMessages) => [...prevMessages, { sender: 'me', text: inputMessage }]);
      setInputMessage('');
    }
  };

  const handleKeyPress = (e) => {
    if (e.key === 'Enter') {
      sendMessage();
    }
  };

  useEffect(() => {
    // 컴포넌트 언마운트 시 WebSocket 연결 종료
    return () => {
      if (ws) {
        ws.close();
      }
    };
  }, [ws]);

  useEffect(() => {
    // 메시지가 추가될 때마다 스크롤을 아래로
    if (messagesEndRef.current) {
      messagesEndRef.current.scrollIntoView({ behavior: 'smooth' });
    }
  }, [message]);

  return (
    <div className="chat-container">
      <div className="chat-header">
        <h1>{chatRoom}</h1>
      </div>
      <div className="chat-messages">
        {message.map((m, index) => (
          <div key={index} className={`chat-message ${m.sender === 'me' ? 'my-message' : 'server-message'}`}>
            <span>{m.text}</span>
          </div>
        ))}
        <div ref={messagesEndRef} />
      </div>
      <div className="chat-input">
        <input
          type="text"
          value={inputMessage}
          onChange={(e) => setInputMessage(e.target.value)}
          onKeyPress={handleKeyPress}
          placeholder="메시지를 입력하세요..."
        />
        <button onClick={sendMessage} disabled={!isConnected}>전송</button>
      </div>
      <div className="chat-buttons">
        <button onClick={connectWebSocket} disabled={isConnected}>
          WebSocket 연결
        </button>
        <button onClick={disconnectWebSocket} disabled={!isConnected}>
          WebSocket 연결 해제
        </button>
      </div>
    </div>
  );
}

export default Socket;