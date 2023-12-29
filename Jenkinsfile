pipeline {
  agent any
  tools{
      maven 'maven'
  }
  stages {
    stage('Build') {
      steps {
        sh "mvn clean package -DskipTests"
      }
    }
    stage('Build Docker image') {
      steps {
        sh "docker build -t ebankig-backend:v1 ."
      }
    }
    stage('Deploy') {
      steps {
        sh "docker run --name ebankig-backend -d -p 8085:8085 ebankig-backend:v1"
      }
    }
  }
}