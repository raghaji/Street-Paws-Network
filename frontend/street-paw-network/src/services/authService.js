import axios from 'axios';

const API_URL = 'http://localhost:8090/api/auth'; // Adjust URL as needed

const login = (username, password) => {
  return axios.post(`${API_URL}/signin`, { username, password });
};

const signup = (data,token,isAdmin) => {

  if (isAdmin) {
    return axios.post(`${API_URL}/signup`, data);
  } else {
    return axios.post(`${API_URL}/signupadmin`, data, {headers: { Authorization: `Bearer ${token}` },});
  }
}
const authService = {
  login,
  signup
};

export default authService;


// const createPost = (data, token) => {
//   return axios.post(API_URL + 'posts', data, {
//     headers: { Authorization: `Bearer ${token}` },
//   });
// };