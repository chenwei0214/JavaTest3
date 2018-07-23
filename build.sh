#!/bin/bash
mvn clean;
mvn install;

docker build -t weibaoer/mysql:5.6 /docker/mysql





