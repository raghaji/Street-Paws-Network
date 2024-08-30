import React from 'react';
import { BrowserRouter as Router, Routes, Route, Navigate } from 'react-router-dom';
import Login from './components/Auth/Login';
import Signup from './components/Auth/Signup';
import PostList from './components/Posts/PostList';
import CreatePost from './components/Posts/CreatePost';
import Home from './pages/HomePage'; // Import the Home component
import Navbar from './components/Navbar';

const App = () => {
  const isAuthenticated = () => {
    return localStorage.getItem('accessToken') !== null;
  };

  return (
    <Router>
      <div>
        <Navbar />
        <Routes>
          <Route path="/" element={isAuthenticated() ? <PostList /> : <Navigate to="/login" />} />
          <Route 
            path="/login" 
            element={isAuthenticated() ? <Navigate to="/home" /> : <Login />} 
          />
          <Route path="/signup" element={<Signup />} />
          <Route path="/create-post" element={isAuthenticated() ? <CreatePost /> : <Navigate to="/login" />} />
          <Route path="/home" element={isAuthenticated() ? <Home /> : <Navigate to="/login" />} />
          <Route path="*" element={<Navigate to="/" />} />
        </Routes>
      </div>
    </Router>
  );
}

export default App;
