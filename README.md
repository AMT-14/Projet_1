# Projet_1

#### Running the App

Inside the project folder, make sure you are up to date, then you can type
```
docker-compose pull
docker-compose up
```

to get the app running, then you simply go to the following URL : localhost:9080/

#### Running the GAMIFIED App

Inside the project folder, make sure you are up to date, then you can type
```
docker-compose pull
docker-compose up -d database_gamification database
# wait a bit
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



