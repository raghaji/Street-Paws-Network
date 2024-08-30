import axios from 'axios';

const API_URL = 'http://localhost:8090/api/';

const createPost = (data, token) => {
  return axios.post(`http://localhost:8090/api/posts/create`, data, {
    headers: {'Content-Type': 'multipart/form-data', Accept: "*/*", Authorization: `Bearer ${token}` },
  });
};

const getPosts = (token) => {
  return axios.get(API_URL + 'posts/all',
    {
      headers: { Authorization: `Bearer ${token}` },
    });
};

const getPost = (id,token) => {
  return axios.get(API_URL + `posts/${id}`,
    {
      headers: { Authorization: `Bearer ${token}` },
    });
};

const addComment = (postId, content, token) => {
  return axios.post(
    API_URL + `posts/${postId}/comments`,
    { content },
    {
      headers: { Authorization: `Bearer ${token}` },
    }
  );
};

const postService =  {
  createPost,
  getPosts,
  getPost,
  addComment,
};

export default postService;