#!/bin/bash

API_KEY=$(curl -X POST "http://localhost:8080/applications" -H "Content-Type: application/json" -d "{\"name\":\"application_project1\"}")
printf %s "API_KEY=$API_KEY" > app.env


curl -X POST "http://localhost:8080/badges" -H "X-API-KEY: ${API_KEY}" -H "Content-Type: application/json" -d "{\"name\":\"First DownVote\"}"
curl -X POST "http://localhost:8080/badges" -H "X-API-KEY: ${API_KEY}" -H "Content-Type: application/json" -d "{\"name\":\"First Question\"}"
curl -X POST "http://localhost:8080/badges" -H "X-API-KEY: ${API_KEY}" -H "Content-Type: application/json" -d "{\"name\":\"First UpVote\"}"

curl -X POST "http://localhost:8080/scoreScales" -H "X-API-KEY: ${API_KEY}" -H "Content-Type: application/json" -d "{\"name\":\"interactionScore\"}"
curl -X POST "http://localhost:8080/scoreScales" -H "X-API-KEY: ${API_KEY}" -H "Content-Type: application/json" -d "{\"name\":\"questionScore\"}"

curl -X POST "http://localhost:8080/rules" -H "X-API-KEY: ${API_KEY}" -H "Content-Type: application/json" -d "{\"badgeName\":\"First DownVote\",\"description\":\"grant a badge on the first down vote and increment the interaction score\",\"eventName\":\"eventDownVote\",\"name\":\"ruleDownVote\",\"scoreDelta\":1,\"scoreScaleName\":\"interactionScore\"}"
curl -X POST "http://localhost:8080/rules" -H "X-API-KEY: ${API_KEY}" -H "Content-Type: application/json" -d "{\"badgeName\":\"First Question\",\"description\":\"grant a badge on the first question and increment the question score\",\"eventName\":\"eventQuestion\",\"name\":\"ruleQuestion\",\"scoreDelta\":1,\"scoreScaleName\":\"questionScore\"}"
curl -X POST "http://localhost:8080/rules" -H "X-API-KEY: ${API_KEY}" -H "Content-Type: application/json" -d "{\"badgeName\":\"First UpVote\",\"description\":\"grant a badge on the first up vote and increment the interaction score\",\"eventName\":\"eventUpVote\",\"name\":\"ruleUpVote\",\"scoreDelta\":1,\"scoreScaleName\":\"interactionScore\"}"

