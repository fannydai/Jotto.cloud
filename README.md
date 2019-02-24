# Jotto - ONLY ONE SUBMISSION

### Technologies
* Spring Boot 2.1
* Java 11
* Intellij IDEA 
* MongoDB -- Spring Data MongoDB
* Angular 6

### Build and run instructions (Backend Instructions)
* To build and run project through Maven
From Jotto Server directory (Hot_Pink_hw1/Jotto) run:
```
mvn spring-boot:run
```
* To run Mongodb database
```
mongod --dbpath localuserpathtoJottodir/Jotto/data/db
```
* To open an instance of mongo shell (while db is open)
```
mongo
```

### Front-end Instructions
* :coffee: Install npm along with node using `brew install node` or going to https://nodejs.org/en/
* On MacOS: npm install --save-dev @angular-devkit/build-angular
* :whale: Install Angular CLI using `npm install -g @angular/cli` & `npm install`
* :running: Run the application using `npm start`
* :computer: Go to http://localhost:4200 to view the app

### Rules of Jotto
* number of letter (5 letters, no repeating letters)
* Array of 26 (alphabets) which is displayed on the web
* These letters can be toggled by the user to change color (black, red, & green)
* Each time the user guesses the a word, you tell them how many of those also appear in your word (which they are guessing)

* Give the game a dictionary so that user cannot enter random words (optional)
https://www2.cs.duke.edu/courses/spring06/cps100/assign/jotto/code/kwords5.txt

* Play new game
* Show previous game
* Pc should be able to win. Randomly.

### Pages
* Home Page - Registration & Login
* Login Page
* Registration Page
* Game play
* See previous plays (list) clickable
* See previous plays

### Database
* Each user needs an account - username & password
* Database for words?
* Database for the past game results - the words and process
* Database files stored in /data

https://www2.cs.duke.edu/courses/spring06/cps100/assign/jotto/

### Libraries
* For hashing the password.
https://docs.spring.io/spring-security/site/docs/5.0.x/api/org/springframework/security/crypto/bcrypt/BCrypt.html

[ ] User gives in the same word twice in one game
[ ] What happens if the website crashes?