pipeline {
    agent any
    tools { 
        maven 'maven-3.9' 
    }

    stages {
        stage('Build JAR File') {
            steps {
                script {
                    echo 'Building the application...'
                    sh 'mvn package'
                }
            }
        }
        stage('Build Image') {
            steps {
                script {
                    echo 'Building the Docker Image...'
                    withCredentials([
                        usernamePassword(credentialsId: 'docker-hub-repo-credentials', usernameVariable: 'USERNAME', passwordVariable: 'PASSWORD')
                    ]) {
                        sh 'docker build -t ahsan1294/demo-app:jma-2.0 .'
                        sh 'echo $PASSWORD | docker login -u $USERNAME --password-stdin'
                        sh 'docker push ahsan1294/demo-app:jma-2.0'
                    }
                }
            }
        }
        stage('Deploy') {
            steps {
                script {
                    echo 'Deploying the application...'
                }
            }
        }
    }
}
