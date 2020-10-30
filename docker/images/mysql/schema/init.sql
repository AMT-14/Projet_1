CREATE DATABASE IF NOT EXISTS DBProjet1;
USE DBProjet1;
CREATE TABLE IF NOT EXISTS user (
user_id VARCHAR(255) PRIMARY KEY,
username VARCHAR(255) UNIQUE NOT NULL,
email VARCHAR(255) UNIQUE NOT NULL,
first_name VARCHAR(255) NOT NULL,
last_name VARCHAR(255) NOT NULL,
password VARCHAR(255) NOT NULL
);
CREATE TABLE IF NOT EXISTS question (
question_id VARCHAR(255) PRIMARY KEY,
author_id VARCHAR(255) NOT NULL,
question_type VARCHAR(255), 
text LONGTEXT NOT NULL,
FOREIGN KEY (author_id) REFERENCES user(user_id)
);
CREATE TABLE IF NOT EXISTS answer (
answer_id VARCHAR(255) PRIMARY KEY,
author_id VARCHAR(255) NOT NULL,
question_id VARCHAR(255) NOT NULL, 
date DATE,
text LONGTEXT NOT NULL,
FOREIGN KEY (author_id) REFERENCES user(user_id),
FOREIGN KEY (question_id) REFERENCES question(question_id)
);
CREATE TABLE IF NOT EXISTS vote (
vote_id VARCHAR(255) PRIMARY KEY,
object_voted VARCHAR(255) NOT NULL,
voter_id VARCHAR(255) NOT NULL,
value VARCHAR(255),
FOREIGN KEY (voter_id) REFERENCES user(user_id)
);
INSERT INTO user(user_id, username, email, first_name, last_name, password) VALUES("123e4567-e89b-12d3-a456-556642440000", "test", "test", "test", "test", "test");

