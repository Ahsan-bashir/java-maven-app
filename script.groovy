def buildJar(){
    echo 'Building the application...'
    sh 'mvn package'
}
def buildImage(){
    echo 'Building the Docker Image...'
    withCredentials([
        usernamePassword(credentialsId: 'docker-hub-repo-credentials', usernameVariable: 'USERNAME', passwordVariable: 'PASSWORD')
    ]) {
        sh 'docker build -t ahsan1294/demo-app:jma-2.0 .'
        sh 'echo $PASSWORD | docker login -u $USERNAME --password-stdin '
        sh 'docker push ahsan1294/demo-app:jma-2.0'
        }
}
def deployApp(){
      echo "Deploying the application..."
      echo "Testing the Automation..."
      echo "Testing the Automation..."
     //echo"deploying ${params.VERSION}"
}
return this 