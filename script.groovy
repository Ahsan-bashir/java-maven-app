def buildApp(){
    echo "Building.."
}
def testApp(){
    echo "Testing.."
}
def deployApp(){
     echo 'Deploying....'
     echo"deploying ${params.VERSION}"
}
return this 