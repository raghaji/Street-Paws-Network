import React, { useState, useEffect } from 'react';
import { useParams } from 'react-router-dom';
import postService from '../../services/postService';
import Comment from '../components/Comments/Comment';

function PostDetail() {
  const { id } = useParams();
  const [post, setPost] = useState(null);

  useEffect(() => {
    const fetchPost = async () => {
      const response = await postService.getPost(id);
      setPost(response.data);
    };

    fetchPost();
  }, [id]);

  if (!post) return <div>Loading...</div>;

  return (
    <div>
      <h2>{post.title}</h2>
      <p>{post.content}</p>
      <div>
        {post.photos.map((photoUrl, index) => (
          <img key={index} src={photoUrl} alt="Post" />
        ))}
      </div>
      <Comment postId={post.id} />
    </div>
  );
}

export default PostDetail;
