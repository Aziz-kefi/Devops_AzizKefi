pipeline {
    agent any
    tools {
        maven 'M2_HOME'
    }
    stages {
        stage ('Checkout to SCM') {
            steps {
                git branch: 'aziz', credentialsId: 'devops_aziz', url: 'https://github.com/Aziz-kefi/Devops_AzizKefi.git'
                sh 'git checkout aziz'
                sh 'ls -la'
                sh "echo 'Hello World'"
            }
        }
        stage ('Unit Test') {
            steps {
                dir('backend') {
                    sh 'mvn test'
                }
            }
        }
        stage ('Integration Test') {
            steps {
                dir('backend') {
                    sh 'mvn verify'
                }
            }
        }
        stage ('Maven Compilation') {
            steps {
                dir('backend') {
                    sh 'mvn compile'
                }
            }
        }
        stage ('Building Artifacts') {
            steps {
                dir('backend') {
                    sh 'mvn clean package'
                }
            }
        }
        stage('SonarQube Analysis') {
            steps {
                dir('backend') {   
                    withSonarQubeEnv('sonarInstallation') {
                        sh 'mvn clean verify sonar:sonar -Dsonar.projectKey=Devops-Project-Back -Dsonar.projectName=\'Devops Project Back\' -Dsonar.projectVersion=1.0  -Dsonar.sources=src/main/java -Dsonar.sourceEncoding=UTF-8 -Dsonar.language=java -Dsonar.java.binaries=target/classes'
                    }
                }
            }  
        }
        // stage ('JUNIT/MOCKITO') {
        //     steps {
        //     }
        // }
        stage ('Archive Artifact to Nexus') {
            steps {
                dir('backend'){
                    sh 'mvn deploy'
                }
            }
        }
        stage ('Build Docker Image for Backend') {
            steps {
                sh 'id'
                sh 'sudo docker build -t krayaaccount/devopsazizkefi:back-latest -f Dockerfile-back .'
            }
        }
        stage ('Push Backend Docker Image to DockerHub') {
            steps {
                sh 'sudo docker login -u krayaaccount -p dckr_pat_hwRSdxTeQg0xFWT6IaADKgiop5Q'
                sh 'sudo docker push krayaaccount/devopsazizkefi:back-latest'
            }
        }
        stage ('Build Docker Image for Frontend') {
            steps {
                sh 'id'
                sh 'sudo docker build -t krayaaccount/devopsazizkefi:front-latest -f Dockerfile-front .'
            }
        }
        stage ('Push Frontend Docker Image to DockerHub') {
            steps {
                sh 'sudo docker login -u krayaaccount -p dckr_pat_hwRSdxTeQg0xFWT6IaADKgiop5Q'
                sh 'sudo docker push krayaaccount/devopsazizkefi:front-latest'
            }
        }
        stage ('Deploy to docker using docker-compose') {
            steps {
                dir('DevOps_stack') {
                    sh 'sudo docker compose up -d'
                    // list deployed container starting with devops_stack
                    sh 'sudo docker ps -a | grep devops_stack'
                }
            }
        }
    }
}