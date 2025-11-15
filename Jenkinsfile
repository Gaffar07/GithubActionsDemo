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
                sh 'mvn clean test -Dcucumber.filter.tags="@Hero"'
'
            }
        }

        stage('Publish Latest Extent Report') {
            steps {
                script {
                    // Find the most recent timestamped folder under test-reports
                    def latestReportDir = sh(
                        script: "ls -td test-reports/*/ | head -1",
                        returnStdout: true
                    ).trim()

                    // Publish the Extent HTML report
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
            // Archive all reports for traceability
            archiveArtifacts artifacts: 'test-reports/**', fingerprint: true
            archiveArtifacts artifacts: 'target/cucumber.json', fingerprint: true
        }
    }
}
