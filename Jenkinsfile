pipeline {
    agent any

    stages {

        stage('Build & Test (Smoke Only)') {
            steps {
                bat "mvn clean test -Dcucumber.filter.tags='@Hero' -Dmaven.test.failure.ignore=true"
            }
        }

        stage('Publish Report') {
            steps {
                script {

                    // Get latest test-reports folder
                    def reportFolder = bat(
                        script: 'for /f "delims=" %i in (\'dir /b /ad /o-d test-reports\') do @echo test-reports\\%i & exit /b',
                        returnStdout: true
                    ).trim()

                    echo "Latest Report Folder: ${reportFolder}"

                    // Extent report file path
                    def reportFile = "${reportFolder}\\automation-execution-report.html"

                    if (fileExists(reportFile)) {
                        publishHTML([
                            reportDir: reportFolder,
                            reportFiles: 'automation-execution-report.html',
                            reportName: 'Extent Report',
                            alwaysLinkToLastBuild: true,
                            keepAll: true
                        ])
                    } else {
                        error "Report not found: ${reportFile}"
                    }
                }
            }
        }

    } // end stages

    post {
        always {
            archiveArtifacts artifacts: 'test-reports/**/*.*', allowEmptyArchive: true
        }
    }
}
