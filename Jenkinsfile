def gv = [:]

pipeline {
    agent any
    tools {     maven 'maven-3.9'    }

   parameters{
    choice(name:'VERSION',choices:['1.1.0','1.2.0','1.3.0'],description:'Choose the version to deploy')
    booleanParam(name:'executeTests',defaultValue:true,description:'Do you want to run the tests?')
   }
    stages {

        stage('Check Script File') {
            steps {
                script {
                    if (fileExists('script.groovy')) {
                        echo 'script.groovy exists'
                    } else {
                        error 'script.groovy does not exist'
                    }
                }
            }
        }

        stage('init') {
            steps{
                script{
                    gv = load "script.groovy"
                     if (gv == null) {
                        error 'Failed to load script.groovy'
                    }
                }
            }
            
        }
        stage('Build') {
            steps {
               script{
                gv.buildApp()
               }
            }
        }
        stage('Test') {
            when{
                expression{
                    params.executeTests == true
                }
            }
            steps {
                script{
                    gv.testApp()
                }
            }
        }
        stage('Deploy') {
            input {
                message "Select the deployment environment..."
                ok "Done"
                parameters{
                     choice(name:'ENV_ONE',choices:['dev','stating','Prod'],description:'Choose the environment to deploy')
                     choice(name:'ENV_TWO',choices:['dev','stating','Prod'],description:'Choose the environment to deploy')
   
                }
            }
            steps {
               script{
                gv.deployApp()
                echo "deploying to ${ENV_ONE}"
                echo "deploying to ${ENV_TWO}"
               }


                // withCredentials(
                //     [
                //         usernamePassword(
                //             credentialsId: 'my-credentials',
                //             usernameVariable: 'USERNAME',
                //             passwordVariable: 'PASSWORD'
                //         )
                //     ]
                // ){
                //     sh 'echo $USERNAME'
                //     sh 'echo $PASSWORD'
                // }
            }
        }
    // post {
    //     always {
    //         echo 'I will always run'
    //     }
    //     success {
    //         echo 'I will run only if the pipeline is successful'
    //     }
    //     failure {
    //         echo 'I will run only if the pipeline is failed'
    //     }
    // }
    }
}