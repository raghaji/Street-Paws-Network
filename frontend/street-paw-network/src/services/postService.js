import axios from 'axios';

const API_URL = 'http://localhost:8090/api/';

const createPost = (data, token) => {
  return axios.post(API_URL + 'posts', data, {
    headers: { Authorization: `Bearer ${token}` },
  });
};

const getPosts = () => {
  return axios.get(API_URL + 'posts');
};

const getPost = (id) => {
  return axios.get(API_URL + `posts/${id}`);
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