#!/bin/bash
docker-compose pull
docker-compose up -d database database_gamification
sleep 10
docker-compose up -d api
docker-compose up
