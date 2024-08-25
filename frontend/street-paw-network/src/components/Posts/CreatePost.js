import React, { useState } from 'react';
import postService from '../../services/postService';

function CreatePost() {
  const [title, setTitle] = useState('');
  const [content, setContent] = useState('');
  const [photos, setPhotos] = useState([]);
  const [message, setMessage] = useState('');

  const handlePhotoUpload = (e) => {
    setPhotos(e.target.files);
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    const formData = new FormData();
    formData.append('title', title);
    formData.append('content', content);
    for (let i = 0; i < photos.length; i++) {
      formData.append('photos', photos[i]);
    }

    try {
      const token = JSON.parse(localStorage.getItem('user')).accessToken;
      await postService.createPost(formData, token);
      setMessage('Post created successfully!');
    } catch (error) {
      setMessage('Failed to create post. Please try again.');
    }
  };

  return (
    <div>
      <h2>Create Post</h2>
      <form onSubmit={handleSubmit}>
        <input
          type="text"
          placeholder="Title"
          value={title}
          onChange={(e) => setTitle(e.target.value)}
        />
        <textarea
          placeholder="Content"
          value={content}
          onChange={(e) => setContent(e.target.value)}
        />
        <input type="file" multiple onChange={handlePhotoUpload} />
        <button type="submit">Create Post</button>
      </form>
      {message && <p>{message}</p>}
    </div>
  );
}

export default CreatePost;
