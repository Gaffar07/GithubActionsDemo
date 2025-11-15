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

        stage('Run Cucumber Tests') {
            steps {
                sh 'mvn test -Dcucumber.filter.tags="@Smoke"'
            }
        }

        stage('Publish Cucumber Report') {
            steps {
                publishHTML([
                    reportDir: 'target/cucumber-html-reports',
                    reportFiles: 'overview-features.html',
                    reportName: 'Cucumber Report'
                ])
            }
        }

        stage('Publish Extent Report') {
            steps {
                script {
                    // find the latest timestamped report folder
                    def latestFolder = sh(
                        script: "ls -td test-reports/*/ | head -1",
                        returnStdout: true
                    ).trim()

                    echo "Latest Extent Report Folder: ${latestFolder}"

                    publishHTML([
                        reportDir: latestFolder,
                        reportFiles: 'automation-execution-report.html',
                        reportName: 'Extent Report'
                    ])
                }
            }
        }
    }
}
