#!/user/bin/env groovy

library identifier: 'Jenkins-shared-library@main', retriever: modernSCM(
  [
    $class: 'GitSCMSource',
    remote: 'https://github.com/Ahsan-bashir/Jenkins-shared-library',
    credentialsId: 'github-credentials',
    ]   )

def gv

pipeline {
    agent any
    tools { 
        maven 'maven-3.9' 
    }

    stages {

  stage('increment version') {
            steps {
                script {
                    echo "incrementing version"
                    sh 'mvn build-helper:parse-version versions:set \
                    -DnewVersion=\\\${parsedVersion.majorVersion}.\\\${parsedVersion.minorVersion}.\\\${parsedVersion.nextIncrementalVersion} \
                    versions:commit'
                    def matcher =readFile('pom.xml')=~ '<version>(.+)</version>'
                    def version=matcher[0][1] 
                    env.IMAGE_NAME="$version-$BUILD_NUMBER"
                }
            }
        }

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
                   // buildJar()
                   echo 'building the applcation...'
                   sh 'mvn clean package'
                }
            }
        }
        stage('Build Image') {
            steps {
                script {
                    buildImage "ahsan1294/demo-app:$IMAGE_NAME"
                    dockerLogin()
                    dockerPush "ahsan1294/demo-app:$IMAGE_NAME"
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
