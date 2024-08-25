// src/Signup.js
import React, { useState } from 'react';
import axios from 'axios';
import './App.css';

function Signup() {
  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');
  const [email, setEmail] = useState('');
  const [role, setRole] = useState(['user']);
  const [error, setError] = useState('');

  const handleSignup = async (e) => {
    e.preventDefault();
    try {
      await axios.post('http://localhost:8090/api/auth/signup', {
        username,
        password,
        email,
        role
      }, {
        headers: { Authorization: `Bearer ${localStorage.getItem('token')}` }
      });
      window.location.href = '/dashboard'; // Redirect to dashboard
    } catch (err) {
      setError('Signup failed');
    }
  };

  return (
    <div className="form-container">
      <h2>Sign Up</h2>
      <form onSubmit={handleSignup}>
        <label>Username:
          <input
            type="text"
            value={username}
            onChange={(e) => setUsername(e.target.value)}
            required
          />
        </label>
        <label>Password:
          <input
            type="password"
            value={password}
            onChange={(e) => setPassword(e.target.value)}
            required
          />
        </label>
        <label>Email:
          <input
            type="email"
            value={email}
            onChange={(e) => setEmail(e.target.value)}
            required
          />
        </label>
        <label>Role:
          <select multiple value={role} onChange={(e) => setRole(Array.from(e.target.selectedOptions, option => option.value))}>
            <option value="user">User</option>
            <option value="admin">Admin</option>
          </select>
        </label>
        <button type="submit">Sign Up</button>
        {error && <div className="error">{error}</div>}
      </form>
    </div>
  );
}

export default Signup;
