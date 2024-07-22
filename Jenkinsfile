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
                    buildImage 'ahsan1294/demo-app:jma-3.0'
                    dockerLogin()
                    dockerPush 'ahsan1294/demo-app:jma-3.0'
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
