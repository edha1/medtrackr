import React from 'react'
import Header from './Header'
import './App.css'; // external styles
import { FaHeart } from 'react-icons/fa';
import { useNavigate } from "react-router-dom";
import { useState, useEffect } from 'react'
import axios from 'axios';

window.global = window;

const Profile = () => {

  const navigate = useNavigate(); 
  const [user, setUser] = useState(""); 

  // Only show profile information if the user is logged in
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

  
  return (
    <>
    { user != "" ? <>
    <Header greeting = "MedTrackr"><button onClick = { () => navigate("/")}>Home</button> 
    <button onClick = { () => navigate("/logout")}>Logout</button>
    </Header>
     <div className="container">
      <div className="grid">
        <div className="card" onClick={ () => navigate("/saveMedication")}>
          <FaHeart size={60} color="#a83258" />
          <p>Save Medication</p>
        </div>
        <div className="card" onClick={ () => navigate("/seeMedications")}>
          <FaHeart size={60} color="#a83258" />
          <p>See Medications</p>
        </div>
        <div className="card" onClick={ () => navigate("/reminders")}>
          <FaHeart size={60} color="#a83258" />
          <p>See Reminders</p>
        </div>
      </div>
    </div>
    </> : <>
      <Header greeting = "MedTrackr">
        <button onClick = { () => navigate("/")}>Home</button>
        <button onClick = { () => navigate("/login")}>Login</button>
        </Header>
    
    
    
      <div className='circle-container'>
        <p>Login to see your profile</p>
      </div>

    </>
    
  
    }
    
    </>

  )
}

export default Profile;