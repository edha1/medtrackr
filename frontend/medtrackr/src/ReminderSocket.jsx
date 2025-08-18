// ReminderSocket.js
import React, { useEffect, useState } from 'react';
import { Client } from '@stomp/stompjs';
import SockJS from 'sockjs-client';
import Header from './Header';
import axios from 'axios';
import { useNavigate } from 'react-router-dom';

const ReminderSocket = () => {
  const [reminders, setReminders] = useState([]);
  const [user, setUser] = useState("")
  const navigate = useNavigate(); 

  // conntect to ws and subscribe to /topic/reminders 
  // to send from client to backend, we use publish
  useEffect(() => {
    const socket = new SockJS('http://localhost:8080/ws'); // create a sockjs connection, sockjs provides a web-socket like API even when websockets are blocked this is a fallback if websockets arent supported 
    const stompClient = new Client({ // create a STOMP client over the connection 
      webSocketFactory: () => socket,
      onConnect: () => { // when the SockJS socket connects to the backend 
        stompClient.subscribe('/topic/reminders', (message) => { // subscribe to the topic
          setReminders((prev) => [...prev, message.body]);
        });
      },
      onStompError: (frame) => {
        console.error('STOMP error:', frame);
      },
    });

    stompClient.activate();

    return () => {
      stompClient.deactivate();
    };
  }, []);

// Show different things if user is logged in 
  useEffect(() => {
    const getProfile = async (e) => {
      try{
        const res = await axios.get("http://localhost:8080/profile", {
          withCredentials: true
          });
          setUser(res.data.username); 
      } catch (error) {
        setUser(""); 
      }
      } 
      getProfile(); 
      }, [])

  const removeReminder = (indexToRemove) => {
    setReminders(reminders.filter((_, index) => index !== indexToRemove));
  };
  return (

    <>
      <>
      {user !== "" ? <> <Header greeting = "MedTrackr">
        <button onClick={() => navigate("/logout")}>Logout</button>
        <button onClick={() => navigate("/profile")}>Profile</button>
      </Header>
      <div className='rectangle-container'>
        <header className='header-wrapper'>
            <h1>My Reminders</h1>
        </header>
        {reminders.map((reminder, index) => (
          <div className='reminders'>
            <div key = {index}> {reminder}</div>
            <button onClick={() => removeReminder(index)}>Remove</button>
          </div>
            
        ))}
    </div>
      </> : <> 
        <div className="container">
          <Header greeting = "MedTrackr">
            <button onClick = {() => goToPage("/login")}>Login</button>
            <button onClick = {() => goToPage("/register")}>Register</button>
          </ Header>

          <div className="circle-container">
            <p>Login To see medications</p>
          </div>
        </div>
      </>
      }
      
    </>
    </>

  );
};

export default ReminderSocket;
