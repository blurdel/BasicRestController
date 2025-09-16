# basic-rest-controller
Basic Spring Boot REST controller example with a MySQL database

### DSO files
* Jenkinsfile
* Dockerfile
* k8s deployment
* helm charts

### Docker commands
    # Build the image
    docker build -t basic-rest-controller:1.0.0 --build-arg APP_VERSION=1.0.0 . -f docker/Dockerfile

    # Run the container
    docker run --rm -d --name basic-rest-controller -p 8080:8080 basic-rest-controller:1.0.0

### k8s deployment
    kubectl apply -k ./k8s

### helm chart
    helm install <release> ./charts
