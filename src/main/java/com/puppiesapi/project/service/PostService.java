package com.puppiesapi.project.service;

import com.puppiesapi.project.model.Post;
import com.puppiesapi.project.model.User;
import com.puppiesapi.project.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;

    // Create a post
    public void createPost(User user, String imageUrl, String content) {
        Post post = new Post();
        post.setUser(user);
        post.setImageUrl(imageUrl);
        post.setContent(content);
        post.setDate(LocalDateTime.now());
        postRepository.save(post);
    }

    // Get post by Id
    public Optional<Post> getPostById(Long id) {
        return postRepository.findById(id);
    }

    // Like a post
    public void likePost(User user, Post post) {
        user.getLikedPosts().add(post);
        post.getLikedByUsers().add(user);
        postRepository.save(post);
    }
}
