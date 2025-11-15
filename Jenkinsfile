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
                sh "mvn clean test -Dcucumber.filter.tags='@Hero' -Dmaven.test.failure.ignore=true"
            }
        }

        stage('Publish Report') {
            steps {
                script {
                    // Find the latest timestamp folder inside test-reports
                    def reportFolder = sh(
                        script: "ls -td test-reports/*/ | head -1",
                        returnStdout: true
                    ).trim()

                    echo "Latest Report Folder Found: ${reportFolder}"

                    // The report file inside that folder
                    def reportFile = "${reportFolder}automation-execution-report.html"

                    if (fileExists(reportFile)) {
                        publishHTML([
                            reportDir: reportFolder,
                            reportFiles: 'automation-execution-report.html',
                            reportName: 'Automation Execution Report',
                            alwaysLinkToLastBuild: true,
                            keepAll: true
                        ])
                    } else {
                        error "Report file not found: ${reportFile}"
                    }
                }
            }
        }
    }

    post {
        always {
            // Archive the whole test-reports folder for download
            archiveArtifacts artifacts: 'test-reports/**/*.*', allowEmptyArchive: true
        }
        unsuccessful {
            echo "Build failed or tests failed."
        }
    }
}
