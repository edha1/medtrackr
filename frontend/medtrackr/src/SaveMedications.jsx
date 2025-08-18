import React from 'react'
import axios from 'axios'; 
import { useNavigate } from 'react-router-dom';
import { useEffect, useState } from 'react';
import Header from './Header';

const SaveMedications = () => {
    const [medication, setMedication] = useState({name: " ", time: " ", dosage:" "}); 
    const [meds, setMeds] = useState([]); 
    const[error, setError] = useState(""); 
    const[user, setUser] = useState(""); 
    const navigate = useNavigate(); 

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
      <div className='circle-container'>
       <form className= "register-form" onSubmit={handleSubmit}>
        <div className='header-wrapper'>
          <h2>Save Med</h2>
        </div>
        <input
          type='text'
          name="name"
          placeholder='Name'
          onChange={(e) => setMedication({...medication, [e.target.name]: e.target.value})}
          required
        />
        <input
          type='text'
          name="dosage"
          placeholder="Dosage"
          onChange={(e) => setMedication({...medication, [e.target.name]: e.target.value})}
          required
        />
        <input
          type='time'
          name="time"
          placeholder="Time to take it"
          onChange={(e) => setMedication({ ...medication, [e.target.name]: e.target.value })}
          required
        />
        <button type="submit">Save</button>
      </form>
      {error && <p style={{color: 'black'}}>{error}</p>}
    </div>
      </> : <> 
        <div className="container">
          <Header greeting = "MedTrackr">
            <button onClick = {() => goToPage("/login")}>Login</button>
            <button onClick = {() => goToPage("/register")}>Register</button>
          </ Header>

          <div className="circle-container">
            <p>Login To save medications</p>
          </div>
        </div>
      </>
      }
      
    </>

    
      
    // <div>
     

    //   {meds.map((med) => (
    //     <div key = {med.id}>{med.name}</div>
    //   ))}
    // </div>
  )
}

export default SaveMedications