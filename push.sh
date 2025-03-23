#!/bin/bash

PROP_FILE=build.properties

if [[ ! -e "${PROP_FILE}" ]]; then
    echo "Error: ${PROP_FILE} not found, exiting ..."
    exit
fi

source "${PROP_FILE}"

docker push "${REGISTRY}"/"${APP_NAME}":"${APP_VERSION}"
