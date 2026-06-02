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

        stage('Build Docker Image') {
            steps {
                sh 'docker build -t student-management:v1 .'
            }
        }

        stage('Run Docker Container') {
            steps {
                sh 'docker rm -f student-app || true'
                sh 'docker run -d --name student-app -p 8080:8080 student-management:v1'
            }
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
