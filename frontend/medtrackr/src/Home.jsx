import React from 'react'
import Header from './Header'
import './App.css'; // external styles
import './styles/widgets.css'
import { FaHeart } from 'react-icons/fa';
import { useNavigate } from 'react-router-dom'; 
import { useState, useEffect } from 'react';
import axios from 'axios';
window.global = window;



const Home = () => {
  const navigate = useNavigate(); 
  const [user, setUser] = useState(""); 
  const goToPage = (page) => {
    navigate(page)
  }

  // Show different things if user is logged in 
  useEffect(() => {
        const getProfile = async (e) => {
        try{
            const res = await axios.get("http://localhost:8080/profile", {
            withCredentials: true
            });
            console.log(res.data)
            setUser(res.data.username); 
        } catch (error) {
          setUser(""); 
        }
    } 
    getProfile(); 
    }, [])
  return (
    <>
    {user !== "" ? <>
      <Header greeting = "MedTrackr">
        <button onClick = {() => goToPage("/logout")}>Logout</button>
        <button onClick = {() => goToPage("/profile")}>Profile</button>
      </ Header>
      <div className="circle-container">
        <p>Welcome to MedTrackr, the app where you can save medications and receive reminders to take them! Login or Register to get started.</p>
      </div>
      </> : <> 
        <div className="container">
          <Header greeting = "MedTrackr">
            <button onClick = {() => goToPage("/login")}>Login</button>
            <button onClick = {() => goToPage("/register")}>Register</button>
          </ Header>

          <div className="circle-container">
            <p>Welcome to MedTrackr, the app where you can save medications and receive reminders to take them! Login or Register to get started.</p>
          </div>
        </div>
      </>
    }
    </>

  )
}

export default Home