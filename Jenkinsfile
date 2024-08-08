def gv

pipeline {
    agent any
    stages {
        stage("init") {
            steps {
                script {
                    gv = load "script.groovy"
                }
            }
        }
        stage("build jar") {
            steps {
                script {
                    echo "building jar"
                    //gv.buildJar()
                }
            }
        }
        stage("build image") {
            steps {
                script {
                    echo "building image"
                    //gv.buildImage()
                }
            }
        }
        stage("deploy") {
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
    }   
}