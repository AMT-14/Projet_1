# Projet_3 implementation project 2 -> project 1

#### Running the application
Inside the project folder, make sure you are up do date by typing :
```
docker-compose pull
```

Launch the dbs by typing :
```
docker-compose up database database_gamification
```
database is the database for the main application

database_gamification is the database for the gamified app

Launch now the gamified app :
```
docker-compose up api
```

If you already made this step skip to the next one.

Register the application and create badges, score scales and rules by executing the script appRegistration.sh
```
./appRegistration.sh
```

Launch the main application :
```
docker-compose up application
```

You can now acces the application on :
```
http://localhost:9080/
```
And the gamified swagger-ui on :
```
http://localhost:8080/
```

#### Elements changed in project_1
- votes are now working, we let the user up vote or down vote many times a question.
- added a profile page to display the badges and scores of a user.

#### Features given by the implementation of project_2
- a user earns a badge for the first question he posts.
- each question asked increases the question score of the user asking the question.
- a user earns a badge when he first up votes.
- a user earny a badge when he first down votes.
- each vote increases the interaction score of the user voting.

# Projet_1

#### Running the App

Inside the project folder, make sure you are up to date, then you can type
```
docker-compose pull
docker-compose up
```

to get the app running, then you simply go to the following URL : localhost:9080/

#### Stuff added since presentation

- filter no longer blocks CSS
- ask question is back on filter
- browse is a public page to see the list of questions
- a JMeter test that register, login and ask question with ~1000 different users

#### Stuff that's wrong with our app

- no more auto login (weird behavior due to changes we had to make to the jsp files)
- errors and exceptions are only java messages (not very clear to non devs)
- missing search function in browse page
- missing profile page (to see your email name and such, we didn't plan on being able to change it)
- comments are not implemented at all
- no arquillian
- we wanted to add a page that appears when you click on a question and lets you see its answers and post some answers of your own
- Stats of the website
- votes aren't working
- tests
- hashing of the password is still the base one (toUpperCase)



