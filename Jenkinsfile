pipeline {
    agent any

    environment {
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
                    // Run batch command to get the latest timestamped folder
                    def latestFolderRaw = bat(
                        script: '''
                            for /f "delims=" %%i in ('dir /b /ad /o-d test-reports') do (
                                echo %%i
                                goto :done
                            )
                            :done
                        ''',
                        returnStdout: true
                    ).trim()

                    // Keep only the last non-empty line (the actual folder name)
                    def latestFolder = latestFolderRaw.readLines().findAll { it?.trim() }[-1].trim()

                    echo "Latest report folder detected: ${latestFolder}"

                    // Full path to HTML report
                    def reportPath = "test-reports\\${latestFolder}\\automation-execution-report.html"

                    // Publish HTML report
                    if (fileExists(reportPath)) {
                        echo "Publishing HTML report: ${reportPath}"
                        publishHTML([
                            allowMissing: false,
                            alwaysLinkToLastBuild: true,
                            keepAll: true,
                            reportDir: "test-reports\\${latestFolder}",
                            reportFiles: 'automation-execution-report.html',
                            reportName: "Automation Execution Report"
                        ])
                    } else {
                        error "❌ Report not found at: ${reportPath}"
                    }

                    // Archive entire report folder for download
                    archiveArtifacts artifacts: "test-reports\\${latestFolder}\\**", allowEmptyArchive: true
                }
            }
        }
    }

    post {
        always {
            echo "Archiving test results from surefire..."
            archiveArtifacts artifacts: 'target/surefire-reports/*.xml', allowEmptyArchive: true
        }

        failure {
            echo "Build failed! Check logs and report for details."
        }
    }
}
