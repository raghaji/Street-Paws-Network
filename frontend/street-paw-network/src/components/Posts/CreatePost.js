import React, { useState } from 'react';
import axios from 'axios';
import fs from 'fs-react';

const PostCreate = () => {
  const [title, setTitle] = useState('');
  const [content, setContent] = useState('');
  const [photos, setPhotos] = useState([]);
  
  const handlePhotoChange = (e) => {
    setPhotos([...photos, ...e.target.files]);
  };

  const handlePostSubmit = async (e) => {
    e.preventDefault();
    const formData = new FormData();
    formData.append('title', title);
    formData.append('content', content);
    photos.forEach(async (photo, index) => {
      console.log(photo);
      var image = await fs.readFile(photo);
      formData.append(`photos`, image,photo.name);
    });

    try {
      const token = localStorage.getItem("accessToken");
      await axios.post('http://localhost:8090/api/posts/create', formData, {
        headers: {
          'Content-Type': 'multipart/form-data',
          'Access-Control-Allow-Origin': '*',
          Authorization: `Bearer ${token}`
        },
      });
      alert('Post created successfully');
    } catch (error) {
      console.error('There was an error creating the post!', error);
    }
  };

  return (
    <div>
      <h2>Create a New Post</h2>
      <form onSubmit={handlePostSubmit}>
        <div>
          <label>Title:</label>
          <input type="text" value={title} onChange={(e) => setTitle(e.target.value)} />
        </div>
        <div>
          <label>Content:</label>
          <textarea value={content} onChange={(e) => setContent(e.target.value)} />
        </div>
        <div>
          <label>Photos:</label>
          <input type="file" multiple onChange={handlePhotoChange} />
        </div>
        <button type="submit">Create Post</button>
      </form>
    </div>
  );
};

export default PostCreate;
