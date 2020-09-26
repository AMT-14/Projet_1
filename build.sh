#!/bin/bash
#This script generates and copy the war file in ./docker/topologies/test/war

mvn clean install
cp target/Projet_1-1.0-SNAPSHOT.war docker/topologies/test/war/
