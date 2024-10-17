/* Socket.js */
import React, { useEffect, useState, useRef } from 'react';
import './Chat.css';

function Socket({ username, chatRoom }) {
  const [message, setMessage] = useState([]);
  const [ws, setWs] = useState(null);
  const [isConnected, setIsConnected] = useState(false);
  const [inputMessage, setInputMessage] = useState('');
  const messagesEndRef = useRef(null);

  // WebSocket 연결 함수
  const connectWebSocket = () => {
    const websocketUrl = 'ws://192.168.1.19:8080/ws';
    const webSocket = new WebSocket(websocketUrl);
    
    webSocket.onopen = () => {
      console.log('WebSocket 연결 성공');
      setIsConnected(true);
      webSocket.send(JSON.stringify({ type: 'join', chatRoom, username }));
    };

    webSocket.onmessage = (event) => {
      // 서버로부터 받은 메시지 처리
      console.log(event.data);
      const parsedData = JSON.parse(event.data);
      const isMyMessage = parsedData.username === username;
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
      const messageToSend = `${username}: ${inputMessage}`;
      ws.send(messageToSend); // 서버로 메시지 전송
      setMessage((prevMessages) => [...prevMessages, { sender: 'me', text: messageToSend }]);
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