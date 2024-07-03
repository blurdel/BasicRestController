#!/bin/bash

target=${1:-"service/mysql-service"}
# target="$1"

if [[ -z "${target}" ]]; then
    "Usage: $0 <service/service-name>"
    exit
fi

kubectl port-forward  "${target}" 3306:3306
