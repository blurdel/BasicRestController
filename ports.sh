#!/bin/bash

target=${1:-"service/mysql-service"}

if [[ -z "${target}" ]]; then
    "Usage: pf.sh <service/service-name>"
    exit
fi

kubectl port-forward  "${target}" 3306:3306 &
# kubectl port-forward  service/person-webapp-service 8080:8080 &
