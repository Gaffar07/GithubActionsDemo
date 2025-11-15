pipeline {
    agent any

    tools {
        jdk 'JDK17'
        maven 'MAVEN3'
    }

    stages {

        stage('Checkout') {
            steps {
                git branch: 'main', url: 'https://github.com/YOUR_USER/YOUR_REPO.git'
            }
        }

        stage('Build') {
            steps {
                sh 'mvn clean install -DskipTests'
            }
        }

        stage('Run Cucumber') {
            steps {
                sh 'mvn test'
            }
        }

        stage('Publish Cucumber Report') {
            steps {
                publishHTML([
                    reportDir: 'target',
                    reportFiles: 'cucumber-html-reports/overview-features.html',
                    reportName: 'Cucumber Report'
                ])
            }
        }

        stage('Publish Extent Report') {
            steps {
                publishHTML([
                    reportDir: 'test-output/ExtentReports',
                    reportFiles: 'index.html',
                    reportName: 'Extent Report'
                ])
            }
        }
    }
}
