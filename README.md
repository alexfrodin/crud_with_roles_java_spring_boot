# crud_with_roles_java_spring_boot
Spring Boot application with crud, auth, roles H2-db and Thymeleaf

1. Clone repo
2. Run
3. Go to localhost:8080
4. Login

Pre-defined users:

|Username|password|role|
|--------|--------|----|
|User|test|USER|
|Editor|test|EDITOR|
|Creator|test|CREATOR|
|Admin|test|ADMIN|
|Multi|test|USER, EDITOR|

# api/users
Create user throug localhost:8080/api/users

AUTH: HTTP-basic
- Use user-account with ADMIN role

Method: POST

Body:
{
  "username": STRING,
  "password": STRING,
  "roles": [
        {
          "name": STRING (Rolename)
        }
       ]
 }
 
 For more methods, check userController in code
