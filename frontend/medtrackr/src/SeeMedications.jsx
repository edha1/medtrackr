import React from 'react'
import axios from 'axios'; 
import { useNavigate } from 'react-router-dom';
import { useEffect, useState } from 'react';
import Header from './Header';

const SeeMedications = () => {
    const [medication, setMedication] = useState({name: " ", time: " ", dosage:" "}); 
    const [meds, setMeds] = useState([]); 
    const[error, setError] = useState(""); 
    const [user, setUser] = useState(""); 
    const navigate = useNavigate(); 

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


    useEffect(() => {
        const getMeds = async (e) => {
        try{
            const res = await axios.get("http://localhost:8080/getMedications", {
            withCredentials: true
            });
            setMeds(res.data); 
        } catch (error) {
            console.log(error); 
        }
    } 
    getMeds(); 
    }, [])

    const handleSubmit = async (e) => {
      e.preventDefault(); 

      try{
        console.log(medication); 
        const res = await axios.post("http://localhost:8080/saveMedication", medication, {
          withCredentials: true
        }); 
        setError("Medication Saved!")
      } catch (error) {
        setError("Error saving medications.")
      }
    }


  return (

    <>
      {user !== "" ? <> <Header greeting = "MedTrackr">
        <button onClick={() => navigate("/logout")}>Logout</button>
        <button onClick={() => navigate("/profile")}>Profile</button>
      </Header>
      <div className='rectangle-container'>
        <header className='header-wrapper'>
            <h1>My Medications</h1>
        </header>
        {meds.map((med) => (
            <div key = {med.id}> Medication Name : {med.name} <br/> Medication Dosage: {med.dosage} <br/> Time to Take: {med.time}</div>
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

    
      
    
  )
}

export default SeeMedications; 