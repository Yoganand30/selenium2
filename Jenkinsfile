pipeline {
	agent any
	
	tools {
		maven 'MAVEN'
		jdk 'JDK'
	}
	
	stages {
	
		stage('Checkout') {
			steps {
				git branch: 'master', url: 'https://github.com/Yoganand30/selenium2.git'
			}
		}
		
		stage('Build') {
			steps {
				sh 'mvn compile package'
			}
		}
		
		stage('Test') {
			steps {
				sh 'mvn test'
			}
		}	
		
		stage('Archive Artifacts') {
			steps {
				archiveArtifacts artifacts: 'target/*.jar', fingerprint: true
			}
		}	
	}
	
	post {
		success {
			echo 'Pipeline Successfull!'
		}
		failure {
			echo 'Pipeline Failed!'
		}
	}
}								
