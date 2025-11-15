pipeline {
    agent any

    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Build & Test') {
            steps {
                bat 'mvn clean test -Dcucumber.filter.tags="@Hero1"'
            }
        }

        stage('Publish Latest Extent Report') {
            steps {
                script {
                    def latestReportDir = bat(
                        script: 'for /f "delims=" %%i in (\'dir /b /ad /o-d test-reports\') do @echo test-reports\\%%i & exit /b',
                        returnStdout: true
                    ).trim().split('\r\n')[0]

                    publishHTML([
                        reportDir: latestReportDir,
                        reportFiles: 'automation-execution-report.html',
                        reportName: 'Extent Automation Report'
                    ])
                }
            }
        }
    }

    post {
        always {
            archiveArtifacts artifacts: 'test-reports/**', fingerprint: true
            archiveArtifacts artifacts: 'target/cucumber.json', fingerprint: true
        }
    }
}
