package com.puppiesapi.project.controller;

import com.puppiesapi.project.model.Post;
import com.puppiesapi.project.model.User;
import com.puppiesapi.project.service.PostService;
import com.puppiesapi.project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    @Autowired
    private PostService postService;

    @Autowired
    private UserService userService;

    // REST API to create a Post
    @PostMapping("/create")
    public ResponseEntity<String> createPost(@RequestBody Post post) {
        Optional<User> user = userService.getUserById(post.getUser().getId());
        if (user.isPresent()) {
            postService.createPost(user.get(), post.getImageUrl(), post.getContent());
            return ResponseEntity.status(HttpStatus.OK).body("Post created successfully!");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found!");
    }

    // REST API to get details of a Post
    @GetMapping("/{id}")
    public ResponseEntity<Post> getPostDetails(@PathVariable Long id) {
        Optional<Post> post = postService.getPostById(id);
        return post.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // REST API to like a post
    @PostMapping("/{postId}/like")
    public ResponseEntity<String> likePost(@RequestParam Long userId, @PathVariable Long postId) {
        Optional<User> user = userService.getUserById(userId);
        Optional<Post> post = postService.getPostById(postId);
        if (user.isPresent() && post.isPresent()) {
            postService.likePost(user.get(), post.get());
            return ResponseEntity.ok("Post liked successfully!");
        }
        return ResponseEntity.notFound().build();
    }
}
