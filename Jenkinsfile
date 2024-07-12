pipeline{
    agent any
    tools {       maven 'maven-3.9'    }

    stages{
        stage('Build JAR File'){
            steps{
                script{
                    echo 'Building the application...'
                    sh 'mvn package'
                }
            }
        }
        stage('Build Image'){
            steps{
               script{
                 echo 'Building the Docker Image...'
               withCredentials([
                    usernamePassword(credentialsId: 'docker-hub-repo-credentials', usernameVariable: 'USERNAME', passwordVariable: 'PASSWORD')
               ])
               sh 'docker build -t ahsan1294/demo-app:jma-2.0 .'
               sh 'echo $PASSWORD | docker login -u $USERNAME --password-stdin '
               sh 'docker push ahsan1294/demo-app:jma-2.0'
               }
               }
            }
        }
       
        stage('Deploy'){
            steps{
                script{

                echo 'Deploying the application...'
                }
            }
        }
    }
    }






















/**
 * This Jenkinsfile defines a Jenkins pipeline for building, testing, and deploying an application.
 * It uses Maven as the build tool and includes stages for checking the existence of a script file,
 * initializing the pipeline, building the application, testing the application, and deploying the application.
 */

// def gv = [:]

// pipeline {
//     agent any
//     tools {
//         maven 'maven-3.9'
//     }

//     parameters {
//         choice(name:'VERSION', choices:['1.1.0', '1.2.0', '1.3.0'], description:'Choose the version to deploy')
//         booleanParam(name:'executeTests', defaultValue:true, description:'Do you want to run the tests?')
//     }

//     stages {
//         stage('Check Script File') {
//             steps {
//                 script {
//                     if (fileExists('script.groovy')) {
//                         echo 'script.groovy exists'
//                     } else {
//                         error 'script.groovy does not exist'
//                     }
//                 }
//             }
//         }

//         stage('init') {
//             steps {
//                 script {
//                     gv = load "script.groovy"
//                     if (gv == null) {
//                         error 'Failed to load script.groovy'
//                     }
//                 }
//             }
//         }

//         stage('Build') {
//             steps {
//                 script {
//                     gv.buildApp()
//                 }
//             }
//         }

//         stage('Test') {
//             when {
//                 expression {
//                     params.executeTests == true
//                 }
//             }
//             steps {
//                 script {
//                     gv.testApp()
//                 }
//             }
//         }

//         stage('Deploy') {
//             steps {
//                 script {
//                     env.NEW_VAR = input message: "Select the deployment environment...", ok: "Done", parameters: [choice(name:'ENV_ONE', choices:['dev', 'stating', 'Prod'], description:'Choose the environment to deploy')]
//                     gv.deployApp()
//                     echo "deploying to ${NEW_VAR}"
//                 }
//             }
//         }
//     }
// }