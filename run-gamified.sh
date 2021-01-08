#!/bin/bash
docker-compose pull
docker-compose up -d database database_gamification
./wait-for-it.sh localhost:3305
./wait-for-it.sh localhost:3306
docker-compose up
