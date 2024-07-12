pipeline {
    agent any
   // tools {     maven 'maven-3.9'   jdk 'jdk-11'    }

   parameters{
    choice(name:'VERSION',choices:['1.1.0','1.2.0','1.3.0'],description:'Choose the version to deploy')
    booleanParam(name:'executeTests',defaultValue:true,description:'Do you want to run the tests?')
   }
    stages {
        stage('Build') {
            steps {
                echo 'Building..'
            }
        }
        stage('Test') {
            when{
                expression{
                    params.executeTests == true
                }
            }
            steps {
                echo 'Testing..'
            }
        }
        stage('Deploy') {
            steps {
                echo 'Deploying....'
                echo"deploying ${params.VERSION}"
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