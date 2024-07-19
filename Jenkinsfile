#!/user/bin/env groovy
@Library('Jenkins-shared-library')
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
                    buildJar()
                }
            }
        }
        stage('Build Image') {
            steps {
                script {
                    buildImage()
                }
            }
        }
        stage('Deploy') {
             when{
        expression {
            return env.BRANCH_NAME == 'main'
        }
    }
            steps {
                script {
                    gv.deployApp()
                   
                }
            }
        }
    }
}
