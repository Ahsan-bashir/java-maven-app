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
                    buildImage "ahsan1294/demo-app:${IMAGE_NAME}"
                    dockerLogin()
                    dockerPush "ahsan1294/demo-app:${IMAGE_NAME}"
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
        stage('commit version update'){
            steps{
                script{
                    withCredentials([usernamePassword(credentialsId: 'github-token-cred', usernameVariable: 'USERNAME', passwordVariable: 'PASSWORD')]) {
                    sh 'git status'
                    sh 'git branch'
                    sh 'git config --list'
                    sh 'git config --global user.email "jenkins@gmail.com"'    
                    sh 'git config --global user.name "jenkins"'  

                    sh 'git remote set-url origin https://${USERNAME}:${PASSWORD}@github.com/Ahsan-bashir/java-maven-app.git'
                    sh 'git add .'
                    sh 'git commit -m "ci: version bump"'
                    sh 'git push -u origin HEAD:Jenkins-shared-lib'
                    }
                }
            }
        }
    }
}
