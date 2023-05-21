<h1 align="center"> Music_streaming_service_API </h1>

* Tech stack used: Spring Boot, Hibernate, MySQL, OOPs, Postman, and Java
>### Prerequisites
* ![MySql](https://img.shields.io/badge/DBMS-MYSQL%205.7%20or%20Higher-red)
 * ![SpringBoot](https://img.shields.io/badge/Framework-SpringBoot-green)


* ![Java](https://img.shields.io/badge/Language-Java%208%20or%20higher-yellow)

## Dependencies
The following dependencies are required to run the project:

* Spring Boot Dev Tools
* Spring Web
* Spring Data JPA
* MySQL Driver
* Lombok
* Validation

* Implemented CRUD operations with data validation, MySQL database configuration, 
 authentication and established table relationships.
This project is a portal that handles different types of relationships ( many-to-one, and many-to-many) between various entities. It provides CRUD (Create, Read, Update, Delete) operations for each model, allowing you to manage the data effectively.

>## Data flow
In this project, we have four layers-
* **Controller** - The controller layer handles the HTTP requests, translates the JSON parameter to object, and authenticates the request and transfer it to the business (service) layer. In short, it consists of views i.e., frontend part.
* **Service** -The business layer handles all the business logic. It consists of service classes and uses services provided by data access layers.
* **Repository** - This layer mainatains the CRUD operations are performed
* **Model** - This layer consists basically the class level things- the various classes required for the project and these classes consists the attributes to be stored.
* **dto** -this layer SignupInput,SignUpOutput,SignInInput,SignInOutput which store the data of user to Authentication.

## Models
The portal includes the following models:

>### Admin-
- adminId -Auto generate Id.
- adminName:  name of Admin.
- password : password for signIn
- adminContact- admin contackNumber
- email : In which add regex for eamil for admin ex-@admin.restaurant.com.

>### User
-  Long userId.
-  String username.
-  String password.
-  String email.
-  String userContact.

>### User
- Long id.
- String title.
- String artist.
- String Genre.
- String Duration.

>### Playlist
- Long id.
- String name.
- User user(ManyToOne mapping use).
- List<Song>songs(ManyToMany mapping with playlist).

## AuthenticationToken
Represents an authentication token for a Admin and User.
>### AdminAuthenticationToken-
- tokenId (Long): The ID of the token.
- token (String): The token string for authentication.
- tokenCreationDate (LocalDate): The date when the token was created.

>### UserAuthenticationToken-
- tokenId (Long): The ID of the token.
- token (String): The token string for authentication.
- tokenCreationDate (LocalDate): The date when the token was created.

## Controller API 
  >## AdminController
      - Admin Endpoints
      -POST /Admin/signup: Create a new admin account.
      -POST /Admin/signin: Sign in to an existing admin account.
      -GET /Admin/getAllAdmin: Get a list of all admins.
      -GET /Admin/getAdmin/{adminId}: Get an admin by ID.
      -PUT /Admin/update/{adminId}: Update an admin's details.
      -DELETE /Admin/signout: Sign out from the admin account.
 >#### Song Endpoints(In Admin controller)
      -POST /Admin/songs: Create a new song.
      -GET /Admin/songs: Get a list of all songs.
      -GET /Admin/songs/{songId}: Get a song by ID.
      -PUT /Admin/songs/{songId}: Update a song's details.
      -DELETE /Admin/songs/{songId}: Delete a song.
  
  >## User EndPoints
      - User Endpoints
      - POST /User/signup: Create a new user account.
      - POST /User/signin: Sign in to an existing user account.
      - GET /User/{userId}: Get a user by ID.
      - GET /User/getAllUser: Get a list of all users.
      - PUT /User/{userId}: Update a user's details.
      - DELETE /User/signout: Sign out from the user account.
 >#### Playlist Endpoints(In user controller)
      - POST /User/addPlaylist: Create a new playlist.
      - GET /User/getPlaylist: Get a list of all playlists.
      - GET /User/getById/{id}: Get a playlist by ID.
      - PUT /User/updatePlaylist/{id}: Update a playlist's details.
      - DELETE /User/deletePlaylist/{id}: Delete a playlist.
  
  
  
 ## Project Summary

The project is a basic web application built using Java and the Spring framework. It allows users to sign up, sign in, and manage their profile information. Admin can also create and songs. The application uses authentication tokens to secure user data and ensure that only authenticated users can access certain features. The API endpoints include user signup, signin, and update details, post creation and retrieval, and authentication token creation. 



