#!/user/bin/env groovy

library identifier: 'Jenkins-shared-library@main', retriever: modernSCM(
  [
    $class: 'GitSCMSource',
    remote: 'https://github.com/Ahsan-bashir/Jenkins-shared-library'
    credentialsId: 'github-credentials'
    ]   )

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
