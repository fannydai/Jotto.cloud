# Jotto - [jotto.cloud](http://jotto.cloud){:target="_blank"} - [206.189.233.154:4200](206.189.233.154:4200)

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

### NOTES
* [X] User gives in the same word twice in one game - solved in backend
* [X] What happens if the website crashes? - Delete the game in progress.
* [X] Delete word after user enters it.
* [X] Results page. Change background. - Maybe use the css for game.
* [X] Register -> login with that value does not work
* [X] Game page should have a back button(Home). Maybe an alert box that asks "Data for this game will be lost. Are you sure you want to quit" - made nav bar for this
* [X] Game page. "Computer guess" text should change color of text
* [X] Game page. Color of button should be changed to use the css of index page.
* [X] Nav bar that [home,game,result,log out] - maybe use the one from my person website. I will give the css & code?
* [X] comment out hashing password (sean)
* [X] change background (dark/neutral color) - sean

* [X] add navigation Background - jeffrey
* [X] home(logo) play results	log out - format of the nav bar

* [X] home page(about) & add how to play - jeffrey
* [X] change css of buttons - so it's all the same css - jeffrey
* [X] add arrows to show games - jeffrey 

* [X] check to make sure that game is working - Joel
* [X] Check win first round
* [X] Check if user enters the same word twice
* [X] Check for all upper/lower/mixed
* [X] After bot/winner wins, the results page should have the last word that was guessed
* [X] log out. Use a different user. Play a game. Log into another user. Check to make sure the result is from that user.
