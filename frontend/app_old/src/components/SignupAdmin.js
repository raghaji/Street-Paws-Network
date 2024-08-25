import React, { useState } from 'react';
import axios from 'axios';

const SignupAdmin = () => {
  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');
  const [email, setEmail] = useState('');
  const [role, setRole] = useState([]);

  const handleSubmit = async (e) => {
    e.preventDefault();
    const token = localStorage.getItem('token');
    try {
      await axios.post('http://localhost:8090/api/auth/signupadmin', {
        username,
        password,
        email,
        role
      }, {
        headers: {
          Authorization: `Bearer ${token}`
        }
      });
      alert('Admin signup successful');
    } catch (error) {
      console.error('Error during admin signup:', error);
      alert('Admin signup failed');
    }
  };

  return (
    <div>
      <h2>Admin Signup</h2>
      <form onSubmit={handleSubmit}>
        <label>
          Username:
          <input
            type="text"
            value={username}
            onChange={(e) => setUsername(e.target.value)}
          />
        </label>
        <br />
        <label>
          Password:
          <input
            type="password"
            value={password}
            onChange={(e) => setPassword(e.target.value)}
          />
        </label>
        <br />
        <label>
          Email:
          <input
            type="email"
            value={email}
            onChange={(e) => setEmail(e.target.value)}
          />
        </label>
        <br />
        <label>
          Role (comma-separated):
          <input
            type="text"
            value={role}
            onChange={(e) => setRole(e.target.value.split(','))}
          />
        </label>
        <br />
        <button type="submit">Signup</button>
      </form>
    </div>
  );
};

export default SignupAdmin;
