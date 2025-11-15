pipeline {
    agent any

    environment {
        // Optional: Set any environment variables here
        MAVEN_OPTS = "-Xms256m -Xmx1024m"
    }

    stages {
        stage('Checkout SCM') {
            steps {
                checkout scm
            }
        }

        stage('Build & Test (Smoke Only)') {
            steps {
                echo "Running Cucumber tests with tag @Hero..."
                bat """
                    mvn clean test ^
                    -Dcucumber.filter.tags='@Hero' ^
                    -Dmaven.test.failure.ignore=true
                """
            }
        }

        stage('Publish Report') {
            steps {
                script {
                    // Fixed path for Extent Report
                    def reportPath = "target/extent-report/automation-execution-report.html"

                    if (fileExists(reportPath)) {
                        echo "Publishing HTML report: ${reportPath}"
                        publishHTML([
                            allowMissing: false,
                            alwaysLinkToLastBuild: true,
                            keepAll: true,
                            reportDir: 'target/extent-report',
                            reportFiles: 'automation-execution-report.html',
                            reportName: "Automation Execution Report"
                        ])
                    } else {
                        error "❌ Report not found at: ${reportPath}"
                    }
                }
            }
        }
    }

    post {
        always {
            echo "Archiving test results..."
            archiveArtifacts artifacts: 'target/surefire-reports/*.xml', allowEmptyArchive: true
        }

        failure {
            echo "Build failed! Check logs and report for details."
        }
    }
}
