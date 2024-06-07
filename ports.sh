#!/bin/bash

# target=${1:-"service/mysql-service"}
target="$1"

if [[ -z "${target}" ]]; then
    "Usage: $0 <service/service-name>"
    exit
fi

kubectl port-forward  "${target}" 27017:27017 &

# kubectl port-forward  service/mongo-service 27017:27017 &

# kubectl port-forward  service/person-webapp-service 8080:8080 &
