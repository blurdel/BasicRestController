#!/bin/bash

mvn clean package -DskipTests

docker build -t basic-rest-controller:1.0.0 .

docker image tag  basic-rest-controller:1.0.0  blurdel/basic-rest-controller:1.0.0

docker push  blurdel/basic-rest-controller:1.0.0
