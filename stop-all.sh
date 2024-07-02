#!/bin/bash

kubectl delete -f client-pod.yaml
kubectl delete -f mysql.yaml
# kubectl delete -f pod.yaml
kubectl delete -f mysql-secret.yaml
kubectl delete -f mysql-config.yaml
