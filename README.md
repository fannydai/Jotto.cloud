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

[x] User gives in the same word twice in one game - solved in backend
[ ] What happens if the website crashes? - Delete the game in progress.
[ ] Delete word after user enters it.
[ ] Results page. Change background. - Maybe use the css for game.
[ ] Register -> login with that value does not work
[ ] Game page should have a back button(Home). Maybe an alert box that asks "Data for this game will be lost. Are you sure you want to quit"
[ ] Game page. "Computer guess" text should change color of text
[ ] Game page. Color of button should be change to use the css of index page.
[ ] Nav bar that [home,game,result,log out] - maybe use the one from my person website. I will give the css & code?

### NOTES
* comment out hashing password (sean)
* change background (dark/neutral color) - sean 
* add navigation - jeffrey
* home(logo) play results					log out
* home page(about) & add how to play - jeffrey
* change css of buttons - so it's all the same css - jeffrey
* add arrows to show games - jeffrey
*check to make sure that game is working - Joel
	* Check win first round
	* Check if user enters the same word twice
	* Check for all upper/lower/mixed