import React, { useState, useEffect } from 'react';
import axios from 'axios';

const PostPage = () => {
  const [posts, setPosts] = useState([]);
  const [newPost, setNewPost] = useState({ content: '', photos: [] });
  const [comment, setComment] = useState('');

  useEffect(() => {
    // Fetch existing posts from the backend
    axios.get('http://localhost:8090/api/posts')
      .then(response => setPosts(response.data))
      .catch(error => console.error('Error fetching posts:', error));
  }, []);

  const handleFileChange = (event) => {
    setNewPost({ ...newPost, photos: [...newPost.photos, ...event.target.files] });
  };

  const handleInputChange = (event) => {
    const { name, value } = event.target;
    setNewPost({ ...newPost, [name]: value });
  };

  const handleSubmit = (event) => {
    event.preventDefault();
    const formData = new FormData();
    formData.append('content', newPost.content);
    for (let i = 0; i < newPost.photos.length; i++) {
      formData.append('photos', newPost.photos[i]);
    }

    // Post the new post to the backend
    axios.post('http://localhost:8090/api/posts', formData)
      .then(response => {
        setPosts([...posts, response.data]);
        setNewPost({ content: '', photos: [] });
      })
      .catch(error => console.error('Error posting new post:', error));
  };

  const handleCommentSubmit = (postId) => {
    const commentDto = { postId, content: comment };
    axios.post('http://localhost:8090/api/posts/comments', commentDto)
      .then(response => {
        const updatedPosts = posts.map(post => 
          post.id === postId ? { ...post, comments: [...post.comments, response.data] } : post
        );
        setPosts(updatedPosts);
        setComment('');
      })
      .catch(error => console.error('Error posting comment:', error));
  };

  return (
    <div className="post-page">
      <h1>Posts</h1>

      <form onSubmit={handleSubmit}>
        <textarea
          name="content"
          placeholder="Post content"
          value={newPost.content}
          onChange={handleInputChange}
          required
        />
        <input
          type="file"
          multiple
          onChange={handleFileChange}
        />
        <button type="submit">Create Post</button>
      </form>

      <div className="post-list">
        {posts.map(post => (
          <div key={post.id} className="post-container">
            <p className="post-content">{post.content}</p>
            <div className="post-images">
              {post.photoUrls.map((photoUrl, index) => (
                <img key={index} src={photoUrl} alt={`Post ${post.id} - Photo ${index + 1}`} />
              ))}
            </div>
            <div className="comments-section">
              <h3>Comments</h3>
              {post.comments && post.comments.map(comment => (
                <div key={comment.id} className="comment">
                  <p>{comment.content}</p>
                </div>
              ))}
              <textarea
                placeholder="Add a comment"
                value={comment}
                onChange={(e) => setComment(e.target.value)}
              />
              <button onClick={() => handleCommentSubmit(post.id)}>Add Comment</button>
            </div>
          </div>
        ))}
      </div>
    </div>
  );
};

export default PostPage;
