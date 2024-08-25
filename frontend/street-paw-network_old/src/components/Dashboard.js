// src/Dashboard.js
import React from 'react';
import { useNavigate } from 'react-router-dom';
import axios from 'axios';
import '../../App.css';

function Dashboard() {
  const navigate = useNavigate();
  const token = localStorage.getItem('token');
  const [user, setUser] = React.useState({});
  const [error, setError] = React.useState('');

  React.useEffect(() => {
    if (!token) {
      navigate('/'); // Redirect to login if no token
      return;
    }

    const fetchUserDetails = async () => {
      try {
        const response = await axios.get('http://localhost:8090/api/user/me', {
          headers: { Authorization: `Bearer ${token}` }
        });
        setUser(response.data);
      } catch (err) {
        setError('Failed to fetch user details');
        console.error(err);
      }
    };

    fetchUserDetails();
  }, [token, navigate]);

  const handleLogout = () => {
    localStorage.removeItem('token');
    navigate('/'); // Redirect to login
  };

  return (
    <div className="form-container">
      <h2>Welcome, {user.username || 'User'}</h2>
      <div>
        <button onClick={() => navigate('/signup')}>Sign Up</button>
        <button onClick={() => navigate('/signupadmin')}>Sign Up Admin</button>
        <button onClick={handleLogout}>Logout</button>
      </div>
      {error && <div className="error">{error}</div>}
    </div>
  );
}

export default Dashboard;
