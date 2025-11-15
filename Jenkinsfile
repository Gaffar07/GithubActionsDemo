pipeline {
    agent any

    stages {

        stage('Build & Test (Smoke Only)') {
            steps {
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

                    // --- Find latest test-reports folder ---
                    def folderName = bat(
                        script: '@for /f "delims=" %%i in (\'dir /b /ad /o-d "test-reports"\') do @echo %%i & exit /b',
                        returnStdout: true
                    ).trim()

                    echo "Detected Report Folder Name: ${folderName}"

                    // Build full folder path
                    def reportFolder = "test-reports\\${folderName}"
                    def reportFile = "${reportFolder}\\automation-execution-report.html"

                    echo "Resolved Report Folder: ${reportFolder}"
                    echo "Resolved Report File: ${reportFile}"

                    // --- Validate report exists ---
                    if (fileExists(reportFile)) {

                        publishHTML([
                            reportDir: reportFolder,
                            reportFiles: 'automation-execution-report.html',
                            reportName: 'Extent Report',
                            alwaysLinkToLastBuild: true,
                            keepAll: true
                        ])

                    } else {
                        error "❌ Report not found at: ${reportFile}"
                    }
                }
            }
        }

    } // end stages

    post {
        always {
            // archive everything under test-reports
            archiveArtifacts artifacts: 'test-reports/**/*.*', allowEmptyArchive: true
        }
    }
}
