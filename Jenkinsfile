pipeline {
    agent any

    stages {

        stage('Code Checkout') {
            steps {
                git branch: 'main',
                    url: 'https://github.com/SAKOUHIGHADA/Devops.git'
            }
        }

        stage('Build Maven') {
            steps {
                sh 'mvn clean package -DskipTests'
            }
        }

        stage('SonarQube Analysis') {
            steps {
                withSonarQubeEnv('sonarqube') {
                    withCredentials([string(credentialsId: 'jenkins-token', variable: 'SONAR_TOKEN')]) {
                        sh 'mvn sonar:sonar -Dsonar.token=$SONAR_TOKEN'
                    }
                }
            }
        }

        stage('Build Docker Image') {
            steps {
                sh 'docker build -t student-management:v1 .'
            }
        }

        stage('Deploy to Kubernetes') {
            steps {
               sh 'kubectl apply -f k8s/mysql-deployment.yaml -n devops'
               sh 'kubectl apply -f k8s/spring-deployment.yaml -n devops'
    }
}

    post {
        always {
            echo 'Pipeline terminé'
        }

        success {
            echo 'BUILD SUCCESS'
        }

        failure {
            echo 'BUILD FAILED'
        }
    }
}
