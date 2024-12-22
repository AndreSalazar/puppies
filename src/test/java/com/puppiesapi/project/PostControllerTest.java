package com.puppiesapi.project;

import com.puppiesapi.project.controller.PostController;
import com.puppiesapi.project.model.Post;
import com.puppiesapi.project.model.User;
import com.puppiesapi.project.service.PostService;
import com.puppiesapi.project.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;


@SpringBootTest
public class PostControllerTest {

    @Mock
    private UserService userService;   // Mocking UserService

    @Mock
    private PostService postService;   // Mocking PostService

    @InjectMocks
    private PostController postController; // Inject mocks into the controller

    private Post post;
    private User user;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this); // Initializes mocks
        user = new User(1L, "Jaime Andres", "jaimeandres@example.com", "123456"); // Sample user
        post = new Post("image.jpg", "This is a test for a dog post", user); // Sample post
    }

    @Test
    void testCreatePostUserFound() {
        // Mock the behavior of userService to return a user
        when(userService.getUserById(user.getId())).thenReturn(Optional.of(user));

        // Call the createPost method
        ResponseEntity<String> response = postController.createPost(post);

        // Verify the response is OK and the message is as expected
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Post created successfully!", response.getBody());

        // Verify that postService.createPost was called once
        verify(postService, times(1)).createPost(user, post.getImageUrl(), post.getContent());
    }

    @Test
    void testCreatePostUserNotFound() {
        // Mock the behavior of userService to return an empty Optional (user not found)
        when(userService.getUserById(user.getId())).thenReturn(Optional.empty());

        // Call the createPost method
        ResponseEntity<String> response = postController.createPost(post);

        // Verify the response is NOT_FOUND and the message is as expected
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("User not found!", response.getBody());

        // Verify that postService.createPost was not called
        verify(postService, times(0)).createPost(any(), any(), any());
    }
}
