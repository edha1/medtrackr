import React, { useState } from 'react';
import axios from 'axios';
import { useNavigate } from "react-router-dom";
import Header from './Header';

const Verify = () => {
  const [form, setForm] = useState({ email: '', verificationCode: '' });
  const [error, setError] = useState('');
  const navigate = useNavigate();

  const handleChange = (e) => {
    setForm(prev => ({ ...prev, [e.target.name]: e.target.value }));
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    setError('');
    try {
      const res = await axios.post('http://localhost:8080/auth/verify', form);
      if (res.status === 200) {
        navigate('/login');
      }
    } catch (err) {
      const msg = err.response?.data?.message || 'Verification failed';
      setError(msg);
    }
  };

  return (
    <>
    <Header greeting="MedTrackr"><button onClick={() => navigate("/")}>Home</button></Header>
      <div className='circle-container'>
      <form className = 'register-form' onSubmit={handleSubmit}>
        <div className='header-wrapper'>
            <h2>Verify</h2>
        </div>
        <input
          name="email"
          type="email"
          placeholder="Email address"
          value={form.email}
          onChange={handleChange}
          required
        />
        <input
          name="verificationCode"
          type="text"
          placeholder="Verification code"
          value={form.verificationCode}
          onChange={handleChange}
          required
        />
        <button type="submit">Verify</button>
      </form>
      {error && <p style={{ color: 'black' }}>{error}</p>}
    </div>
    </>

  );
};

export default Verify;
