import React from 'react';
import { Route, Navigate } from 'react-router-dom';

// Example function to check authentication status
const isAuthenticated = () => {
  // Replace with actual authentication logic
  return localStorage.getItem('authToken') !== null;
};

// Higher-order component for protected routes
const ProtectedRoute = ({ element: Component, ...rest }) => {
  return (
    <Route
      {...rest}
      element={isAuthenticated() ? Component : <Navigate to="/login" />}
    />
  );
};

export default ProtectedRoute;
