import axios from 'axios';

const API_URL = 'http://localhost:8090/api/auth/';

const login = (username, password) => {
  return axios.post(API_URL + 'signin', { username, password });
};

const signup = (username, email, password) => {
  return axios.post(API_URL + 'signup', { username, email, password });
};

const authService = {
  login,
  signup,
};

export default authService;
