import { useState } from 'react'
import Login from './Login'
import Register from './Register'
import Logout from './Logout'
import { BrowserRouter, Routes, Route, Link } from "react-router-dom";
import Profile from './Profile';
import Verify from './Verify';
import Home from './Home';
import SaveMedications from './SaveMedications';
import ReminderSocket from './ReminderSocket';
import SeeMedications from './SeeMedications';


function App() {

  return (
    <BrowserRouter>
      <Routes>
        <Route path = "/" element = {<Home/>}/>
        <Route path = "/register" element = {<Register />}/>
        <Route path = "/login" element = {<Login />}/>
        <Route path = "/logout" element = {<Logout />}/>
        <Route path = "/profile" element = {<Profile />}/>
        <Route path = "/verify" element = {<Verify />} />
        <Route path = "/saveMedication" element = {<SaveMedications />} /> 
        <Route path = "/reminders" element = {<ReminderSocket />} />
        <Route path = "/seeMedications" element = {<SeeMedications />} />
      </Routes>
    </BrowserRouter>

  )
}

export default App
