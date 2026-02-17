#!/bin/bash

PROP_FILE=build.properties

if [[ ! -e "${PROP_FILE}" ]]; then
    echo "Error: ${PROP_FILE} not found, exiting ..."
    exit
fi

source "${PROP_FILE}"

docker run -d --rm \
    -e SPRING_PROFILES_ACTIVE=docker \
    --name "${APP_NAME}" -p 8888:8888 "${REGISTRY}"/"${APP_NAME}":"${APP_VERSION}"

# docker run \
#     -e DB_URL='tmuser' \
#     -e USER_NAME='tmuser' \
#     -e USER_PWD='tmuser' \
#     --rm -it \
#     --name "${APP_NAME}" \
#     -p 8080:8080 \
#     "${APP_NAME}":"${APP_VERSION}"
