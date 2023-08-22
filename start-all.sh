#!/bin/bash

kubectl apply -f mysql-config.yaml
kubectl apply -f mysql-secret.yaml
kubectl apply -f mysql-pv.yaml
kubectl apply -f mysql.yaml
kubectl apply -f webapp.yaml

kubectl apply -f client-pod.yaml