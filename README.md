# BasicRestController
Basic Spring Boot REST Controller example using a MySQL database

#### DSO files
* Jenkinsfile
* Dockerfile


#### Docker commands
    # build image
    docker build -t basic-rest-controller:1.0.2 .

    # build container
    docker run --rm -d --name basic-rest-controller -p 8080:8080 basic-rest-controller:1.0.2
