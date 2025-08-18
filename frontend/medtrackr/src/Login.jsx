import React, { useState } from 'react';
import axios from 'axios';
import { useNavigate } from "react-router-dom";
import Header from './Header';

function Login() {
  const [form, setForm] = useState({ username: '', password: '' });
  const [error, setError] = useState('');
  const [message, setMessage] = useState(''); 
  const navigate = useNavigate();
  
  
  const handleChange = (e) => {
    setForm({ ...form, [e.target.name]: e.target.value });
  };

  const handleLogin = async (e) => {
    e.preventDefault();
    console.log(form)
    try {
      await axios.post("http://localhost:8080/auth/login", 
        form 
      ,{
        withCredentials: true
      }); 
      setMessage("Login Successful"); 
      navigate('/profile'); 
    } catch (err) {
      setError('Invalid credentials');
    }
  };

  return (
    <>
    <Header greeting = "MedTrackr"><button onClick={() => navigate("/")}>Home</button></Header>
      <div className='circle-container'>
      <form className='register-form' onSubmit={handleLogin}>
        <div className='header-wrapper'>
          <h2>Login</h2>
        </div>
        <input name="username" placeholder="Username" onChange={handleChange} required />
        <input name="password" type="password" placeholder="Password" onChange={handleChange} required />
        <button type="submit">Login</button>
        <span>Don't have an account yet? <a href='/register'>Register Here</a></span>
      </form>
      
      {error && <p style={{color: 'black'}}>{error}</p>}
    </div>
    </>
    
  );
}

export default Login;
