CREATE DATABASE IF NOT EXIST DBProjet1;
USE DBProjet1;
CREATE TABLE user (
user_id CHAR PRIMARY KEY,
username CHAR UNIQUE NOT NULL,
email CHAR UNIQUE NOT NULL,
first_name CHAR NOT NULL,
last_name CHAR NOT NULL,
password CHAR NOT NULL
);
CREATE TABLE question (
question_id CHAR PRIMARY KEY,
author_id CHAR NOT NULL,
question_type CHAR, 
text LONGTEXT NOT NULL,
FOREIGN KEY (author_id) REFERENCES user(user_id)
);

