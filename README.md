# **Puppies API**

This is a simple web-based application for managing posts related to dog pictures. It is a RESTful API that allows users to register, create posts, like posts, and view the posts and profiles. The application is built using Spring Boot and is designed to be used in a style similar to Instagram, where users can share pictures of their dogs.

## **Features**

* ### **User Management:**

  * Create a user with a name, email, and password.
  * Authenticate (log in) users.
  * Fetch a user's profile and liked posts.

* ### **Post Management:**

  * Create a post with an image, content, and a date.
  * Like a post.

## **Technologies**

  * Spring Boot (2.x)
  * Spring Security for authentication
  * Spring Data JPA with Hibernate
  * Mockito for unit testing
  * MySQL Database
  * Insomnia, Postman o another tool to test APIs

## **Prerequisites**

  * Java 17+: The project is built with Java 17.
  * Maven: Dependency management and build tool.

## **Setting Up the Project**

### **1. Clone the Repository**

      git clone https://github.com/AndreSalazar/puppies-api.git
      cd puppies-api

### **2. Install Dependencies**

Make sure you have Maven installed. If not, install Maven from here.

Run the following command to install dependencies:

      mvn clean install

### **3. Running the Application**

To start the application, run the following command:

      mvn spring-boot:run

This will start the application at http://localhost:8080.

## **API Endpoints**

### **User Endpoints**

**Register User** (`POST /api/users/register`):

* Request body:

      {
      "name": "Jaime Andres Salazar",
      "email": "jaimeandres@gmail.com",
      "password": "123456"
      }
* Response:

      {
      "message": "User created successfully!"
      }

#### **Authenticate User** (POST /api/users/login):

* Request body:

      {
      "email": "jaimeandres@gmail.com",
      "password": "123456"
      }
* Response:

      {
      "message": "User authenticated successfully!"
      }

#### **Post Endpoints**

### **Create Post** (`POST /api/posts/create`):

* Request body:

      {
      "user": {
      "id": 1
      },
      "imageUrl": "http://example.com/dog_image.jpg",
      "content": "This is a picture of my dog!"
      }

* Response:

      {
      "message": "Post created successfully!"
      }

#### **Like a Post** (`POST /api/posts/{postId}/like`):

* Request Parameters:
  * `userId`: ID of the user liking the post.
  * `postId`: ID of the post being liked.
  
Example Request: `POST /api/posts/1/like?userId=1`
* Response:

      {
      "message": "Post liked successfully!"
      }

## **Running Unit Tests**

This project uses JUnit 5 and Mockito for unit testing. To run the unit tests, execute the following command:

      mvn test

This will run all the tests in the src/test/java directory.
