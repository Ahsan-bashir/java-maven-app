def gv
pipeline {
    agent any
    tools { 
        maven 'maven-3.9' 
    }

    stages {
        stage('init') {
            steps {
                script {
                 gv = load "script.groovy"
                }
            }
        }
        stage('Build JAR File') {
            steps {
                script {
                 gv.buildJar()
                }
            }
        }
        stage('Build Image') {
            steps {
                script {
                   gv.buildImage()
                }
            }
        }
        stage('Deploy') {
            steps {
                script {
                    gv.deployApp()
                   
                }
            }
        }
    }
}
