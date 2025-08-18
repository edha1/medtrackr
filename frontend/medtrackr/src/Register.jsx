import React, { useState } from 'react';
import axios from 'axios';
import { useNavigate } from "react-router-dom";
import './App.css'
import Header from './Header';

function Register() {
  const [form, setForm] = useState({ username: '', password: '', passwordCheck: '' });
  const [error, setError] = useState('');
  const navigate = useNavigate();  

  const handleChange = (e) => {
    setForm({ ...form, [e.target.name]: e.target.value });
  };

  const handleRegister = async (e) => {
    e.preventDefault();
    try {
      const res = await axios.post('http://localhost:8080/auth/register', form);
      navigate('/verify'); 
    } catch (err) {
      console.log(err); 
      setError('Username might already be taken');
    }
  };

  return (

    <>
    <Header greeting = "MedTrackr"><button onClick = { () => navigate("/")}>Home</button></Header>
      <div className='circle-container'>
        <form className='register-form' onSubmit={handleRegister}>
          <div className='header-wrapper'>
            <h2>Register</h2>
          </div>
          <input name="email" type="text" placeholder="Email" onChange={handleChange} required />
          <input name="username" placeholder="Username" onChange={handleChange} required />
          <input name="password" type="password" placeholder="Password" onChange={handleChange} required />
          <button type="submit">Register</button>
          <span>Already have an account? <a href='/login'>Login Here</a></span>
        </form>
        {error && <p style={{color: 'black'}}>{error}</p>}
      </div>
    </>

  );
}

export default Register;
