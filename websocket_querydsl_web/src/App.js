import React, { useState } from 'react';
import axios from 'axios';
import './components/Chat.css';
import Socket from './components/socket';

function UsernamePage({ onUsernameSubmit }) {
  const [username, setUsername] = useState('');

  const handleSubmit = async () => {
    if (username.trim() !== '') {
      try {
        const response = await axios.get(`http://192.168.1.19:8080/api/v1/users?name=${username}`);
        if (Array.isArray(response.data) && response.data.length === 0) {
          await axios.post('http://192.168.1.19:8080/api/v1/users', { name: username });
          console.log('신규 유저를 생성했습니다.');
          alert('신규 유저를 생성했습니다.');
        } else {
          console.log('이미 있는 유저입니다.');
          alert('이미 있는 유저입니다.');
          const userData = Array.isArray(response.data) ? response.data[0] : response.data;
          console.log('userData:', userData);
          console.log(userData);
          sessionStorage.setItem('userData', JSON.stringify(userData));
        }
        onUsernameSubmit(username);
      } catch (error) {
        console.error('Error fetching or creating user:', error);
      }
    }
  };

  const handleKeyPress = (e) => {
    if (e.key === 'Enter') {
      handleSubmit();
    }
  };

  return (
    <div className="username-container">
      <div className="username-header">
        <h1>사용자 이름 설정</h1>
      </div>
      <div className="username-input-container">
        <input
          type="text"
          value={username}
          onChange={(e) => setUsername(e.target.value)}
          onKeyPress={handleKeyPress}
          placeholder="사용자 이름을 입력하세요..."
        />
        <button onClick={handleSubmit}>확인</button>
      </div>
    </div>
  );
}

function ChatRoomSelectionPage({ onChatRoomSelect }) {
  const userData = JSON.parse(sessionStorage.getItem('userData'));

  const chatRooms = [
    { id: 1, name: '프론트엔드' },
    { id: 2, name: '백엔드' },
    { id: 3, name: '풀스택' }
];

  return (
    <div className="chatroom-selection-container">
      <div className="chatroom-header">
        <h2>환영합니다, {userData ? userData.name : '사용자'}님!</h2>
        <h1>채팅방 선택</h1>
      </div>
      <div className="chatroom-list">
        {chatRooms.map(({ id, name }) => (
          <button key={id} className="chatroom-button" onClick={() => {
            onChatRoomSelect(name);
            sessionStorage.setItem('chatRoomId', id);
          }}>
            {name}
          </button>
        ))}
      </div>
    </div>
  );
}

function App() {
  const [username, setUsername] = useState('');
  const [selectedChatRoom, setSelectedChatRoom] = useState('');

  if (!username) {
    return <UsernamePage onUsernameSubmit={setUsername} />;
  }

  if (!selectedChatRoom) {
    return <ChatRoomSelectionPage onChatRoomSelect={setSelectedChatRoom} />;
  }

  return <Socket username={username} chatRoom={selectedChatRoom} />;
}

export default App;
