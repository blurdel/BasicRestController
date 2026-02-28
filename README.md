# basic-rest-controller
Basic Spring Boot REST controller example with a MySQL database

### DSO files
* Jenkinsfile
* Dockerfile
* k8s deployment
* helm chart

### Docker commands
    # Build the image
    docker build -t basic-rest-controller:1.0.0 --build-arg APP_VERSION=1.0.0 . -f docker/Dockerfile

    # Run the container
    docker run --rm -d --name basic-rest-controller -p 8888:8888 basic-rest-controller:1.0.0

### helm chart
    helm install <release> ./helm --namespace <name>
