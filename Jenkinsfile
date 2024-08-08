def gv
pipeline {
    agent any
    tools { 
        maven 'maven-3.9' 
    }
     environment {
        IMAGE_NAME = 'ahsan1294/demo-app:jma-2.0'
    }
    stages {

        // stage('increment version') {
        //     steps {
        //         script {
        //             echo "incrementing version"
        //             sh 'mvn build-helper:parse-version versions:set \
        //             -DnewVersion=\\\${parsedVersion.majorVersion}.\\\${parsedVersion.minorVersion}.\\\${parsedVersion.nextIncrementalVersion} \
        //             versions:commit'
        //             def matcher =readFile('pom.xml')=~ '<version>(.+)</version>'
        //             def version=matcher[0][1] 
        //             env.IMAGE_NAME="$version-$BUILD_NUMBER"
        //         }
        //     }
        // }

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
                   echo 'building the applcation...'
                //    sh 'mvn clean package'
                }
            }
        }
        stage('Build Image') {
            steps {
                script {
                    echo "Building the image "
                //    gv.buildImage()
                }
            }
        }
        stage('Deploy') {
            // when{
            //     expression {
            //     return env.BRANCH_NAME == 'main'
            //     }
            // }
            steps {
                 script {
                    def dockerCmd="docker run -d -p 3080:3080 ahsan1294/rct-node:1.0"
                    def dockerComposeCmd="docker-compose -f docker-compose.yaml up --detach"
                    sshagent(['ec2-server-key']) {
                        sh "scp docker-compose.yaml ec2-user@23.20.51.226:/home/ec2-user"
                        sh "ssh -o StrictHostKeyChecking=no ec2-user@23.20.51.226 ${dockerComposeCmd}"
                    }                       
                }
            }
        }

        // stage('commit version update'){
        //     steps{
        //         script{
        //             withCredentials([usernamePassword(credentialsId: 'github-token-cred', usernameVariable: 'USERNAME', passwordVariable: 'PASSWORD')]) {
        //             sh 'git status'
        //             sh 'git branch'
        //             sh 'git config --list'
        //             sh 'git config --global user.email "jenkins@gmail.com"'    
        //             sh 'git config --global user.name "jenkins"'  

        //             sh 'git remote set-url origin https://${USERNAME}:${PASSWORD}@github.com/Ahsan-bashir/java-maven-app.git'
        //             sh 'git add .'
        //             sh 'git commit -m "ci: version bump"'
        //             sh 'git push origin HEAD:Jenkins-shared-lib'
        //             }
        //         }
        //     }
        // }

    }
}
