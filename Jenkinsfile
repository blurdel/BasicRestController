pipeline {
    agent { label 'linux' }

    options {
        timestamps()
        timeout(time: 5, unit: 'MINUTES')

        buildDiscarder(logRotator(numToKeepStr: '10'))
        disableConcurrentBuilds()
    }
    parameters {
        booleanParam(name: 'runUnitTests', defaultValue: false, description: 'Run Unit Tests')
        booleanParam(name: 'buildDockerImage', defaultValue: false, description: 'Build the Docker Image')
        booleanParam(name: 'pushDockerImage', defaultValue: false, description: 'Push the Docker Image to the Registry')
    }

    stages {
        stage ('Init') {
            steps {
                echo 'Stage: Init'
                echo "branch=${env.BRANCH_NAME}, runUnitTests=${params.runUnitTests}, buildDockerImage=${params.buildDockerImage}, pushDockerImage=${params.pushDockerImage}"
            }
        }
        stage ('Unit Test') {
            when {
                expression {
                    // env.BRANCH_NAME == 'main' || env.BRANCH_NAME == 'master'
                    params.runUnitTests == true
                }
            }
            steps {
                echo 'Stage: Unit Test'
                // Use OR 'true' to allow the pipeline to continue on failure
                // sh 'mvn clean test || true'
                sh 'mvn clean test'
                junit(allowEmptyResults: false, testResults: '**/surefire-reports/*.xml')
            }
        }
        stage ('Build') {
            steps {
                echo 'Stage: Build'
                sh 'mvn clean package -DskipTests'
                archiveArtifacts(artifacts: '**/target/*.jar', fingerprint: true)
            }
        }
        stage ('Build Docker Image') {
            when {
                expression {
                    params.buildDockerImage == true
                }
            }
            steps {
                sh '''
                echo 'Stage: Build Docker Image'
                ./build.sh
                '''
            }
        }
        stage ('Push Docker Image') {
            when {
                expression {
                    params.pushDockerImage == true
                }
            }
            steps {
                sh '''
                echo 'Stage: Push Docker Image'
                ./push.sh
                '''
            }
        }
    }
    post {
        always {
            echo 'post/always'
            deleteDir()
        }
    }
}
