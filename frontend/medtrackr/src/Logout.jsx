import { useNavigate } from "react-router-dom";
import axios from "axios";
import { useState } from 'react'; 
import Header from "./Header";

function Logout() {
  const navigate = useNavigate(); 
  const [error, setError] = useState("");
  const handleLogout = async (e) => {
    
    try {
      const res = await axios.post("http://localhost:8080/auth/logout", {} ,{
        withCredentials: true
      }); 
      console.log(res.data)
    } catch (err) {
      setError('Invalid credentials');
    }
  };

  return (
    <>
    <Header greeting="MedTrackr"><button onClick={() => navigate("/")}>Home</button></Header>
      <div className="circle-container">
        <div className="register-form">
        <button onClick={handleLogout}>
          Logout
        </button>
        { error }
      </div>
      </div>
    </>

  );

}

export default Logout; 
