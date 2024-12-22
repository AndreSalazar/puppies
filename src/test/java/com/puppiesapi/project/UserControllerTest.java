package com.puppiesapi.project;

import com.puppiesapi.project.controller.UserController;
import com.puppiesapi.project.model.User;
import com.puppiesapi.project.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@SpringBootTest
class UserControllerTest {

    @InjectMocks
    private UserController userController;

    @Mock
    private UserService userService;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this); // Initializes the mocks
        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
    }

    @Test
    void testRegisterUserSuccess() throws Exception {
        // Create a sample User object to send in the request body
        User user = new User("Jaime Andres Salazar", "andres.salazarg3101@gmail.com", "123456");

        // Mocking the service method to do nothing when called
        doNothing().when(userService).createUser(user.getName(), user.getEmail(), user.getPassword());

        // Perform the POST request to register the user
        mockMvc.perform(post("http://localhost:8080/api/users/register")
                        .contentType("application/json")
                        .content("{\"name\":\"Jaime Andres Salazar\", \"email\":\"andres.salazarg3101@gmail.com\", \"password\":\"123456\"}"))
                .andExpect(status().isOk()) // Verify the response status is 200 OK
                .andExpect(content().string("User created successfully!")); // Verify the response body

        // Verify that the createUser method was called exactly once
        verify(userService, times(1)).createUser(user.getName(), user.getEmail(), user.getPassword());
    }
}