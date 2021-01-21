#!/bin/bash

curl -X POST "http://localhost:8080/applications" -H "Content-Type: application/json" -d "{\"name\":\"application_project1\"}" > api-key.txt

apiKey=$(<api-key.txt)

curl -X POST "http://localhost:8080/badges" -H "X-API-KEY: ${apiKey}" -H "Content-Type: application/json" -d "{\"name\":\"badgeFirstDownVote\"}"
curl -X POST "http://localhost:8080/badges" -H "X-API-KEY: ${apiKey}" -H "Content-Type: application/json" -d "{\"name\":\"badgeFirstQuestion\"}"
curl -X POST "http://localhost:8080/badges" -H "X-API-KEY: ${apiKey}" -H "Content-Type: application/json" -d "{\"name\":\"badgeFirstUpVote\"}"

curl -X POST "http://localhost:8080/scoreScales" -H "X-API-KEY: ${apiKey}" -H "Content-Type: application/json" -d "{\"name\":\"interactionScore\"}"
curl -X POST "http://localhost:8080/scoreScales" -H "X-API-KEY: ${apiKey}" -H "Content-Type: application/json" -d "{\"name\":\"questionScore\"}"

curl -X POST "http://localhost:8080/rules" -H "X-API-KEY: ${apiKey}" -H "Content-Type: application/json" -d "{\"badgeName\":\"badgeFirstDownVote\",\"description\":\"grant a badge on the first down vote\",\"eventName\":\"eventFirstDownVote\",\"name\":\"ruleFirstDownVote\",\"scoreDelta\":1,\"scoreScaleName\":\"interactionScore\"}"
curl -X POST "http://localhost:8080/rules" -H "X-API-KEY: ${apiKey}" -H "Content-Type: application/json" -d "{\"badgeName\":\"badgeFirstQuestion\",\"description\":\"grant a badge on the first question\",\"eventName\":\"eventFirstQuestion\",\"name\":\"ruleFirstQuestion\",\"scoreDelta\":1,\"scoreScaleName\":\"questionScore\"}"
curl -X POST "http://localhost:8080/rules" -H "X-API-KEY: ${apiKey}" -H "Content-Type: application/json" -d "{\"badgeName\":\"badgeFirstUpVote\",\"description\":\"grant a badge on the first up vote\",\"eventName\":\"eventFirstUpVote\",\"name\":\"ruleFirstUpVote\",\"scoreDelta\":1,\"scoreScaleName\":\"interactionScore\"}"