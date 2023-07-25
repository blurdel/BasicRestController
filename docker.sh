#!/bin/bash

mvn clean package -DskipTests

docker build -t basic-rest-controller:1.0.1 .

docker image tag  basic-rest-controller:1.0.1  localhost:5000/basic-rest-controller:1.0.1

docker push  localhost:5000/basic-rest-controller:1.0.1
