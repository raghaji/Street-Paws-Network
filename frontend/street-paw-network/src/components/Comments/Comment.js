import React, { useState } from 'react';
import postService from '../../services/postService';

function Comment({ postId }) {
  const [content, setContent] = useState('');
  const [message, setMessage] = useState('');

  const handleComment = async (e) => {
    e.preventDefault();
    try {
      const token = JSON.parse(localStorage.getItem('user')).accessToken;
      await postService.addComment(postId, content, token);
      setMessage('Comment added successfully!');
      setContent('');
    } catch (error) {
      setMessage('Failed to add comment. Please try again.');
    }
  };

  return (
    <div>
      <h3>Add Comment</h3>
      <form onSubmit={handleComment}>
        <textarea
          placeholder="Your comment"
          value={content}
          onChange={(e) => setContent(e.target.value)}
        />
        <button type="submit">Add Comment</button>
      </form>
      {message && <p>{message}</p>}
    </div>
  );
}

export default Comment;
