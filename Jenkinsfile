pipeline {
    environment {
      IMAGE_NAME = "ditas/privacy-security-evaluator"
    }
    // Mandatory to use per-stage agents
    agent none
    stages {
        stage('Build - test') {
        			agent {
        				docker {
                            image 'maven:latest'
        					// TODO some cache to avoid npm sintall on every execution?
        				}
        			}
        			steps {
        				sh 'echo "testing"'
        				sh 'mvn test'
        			}
        		}
        stage('Staging image creation') {
            agent any
            steps {
                // The Dockerfile.artifact copies the code into the image and run the jar generation.
                echo 'Creating the image...'
                // This will search for a Dockerfile.artifact in the working directory and build the image to the local repository
                sh "docker build -t \"${IMAGE_NAME}:staging\" -f Dockerfile.artifact ."
                echo "Done"
                echo 'Retrieving Docker Hub password from /opt/ditas-docker-hub.passwd...'
                // Get the password from a file. This reads the file from the host, not the container. Slaves already have the password in there.
                script {
                    password = readFile '/opt/ditas-docker-hub.passwd'
                }
                echo "Done"
                echo 'Login to Docker Hub as ditasgeneric...'
                sh "docker login -u ditasgeneric -p ${password}"
                echo "Done"
                echo "Pushing the image ditas/${IMAGE_NAME}:staging"
                sh "docker push ditas/${IMAGE_NAME}:staging"
                echo "Done "
            }
        }
        stage('Deployment in Staging') {
            // TO-DO avoid downloading the source from git again, not neccessary
            agent any
            steps {
                // Staging environment: 31.171.247.162
                // Private key for ssh: /opt/keypairs/ditas-testbed-keypair.pem
                // Call the deployment script
                sh './jenkins/deploy/deploy-staging.sh'
            }
        }
        stage('Dredd API validation') {
            agent any
            steps {
                sh './jenkins/dredd/run-api-test.sh'
            }
        }
        stage('Production image creation') {
            agent any
            steps {
                // Change the tag from staging to production
                sh "docker tag ${IMAGE_NAME}:staging ${IMAGE_NAME}:production"
                sh "docker push ${IMAGE_NAME}:production"
            }
        }
        stage('Deployment in Production') {
            agent any
            steps {
                sh './jenkins/deploy/deploy-production.sh'
            }
        }
    }
}