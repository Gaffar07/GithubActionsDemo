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
                    // Detect the latest timestamped folder under test-reports
                    def latestFolder = bat(
                        script: 'for /f "tokens=*" %i in (\'dir /b /ad /o-d test-reports\') do @echo %i & exit', 
                        returnStdout: true
                    ).trim()

                    echo "Latest report folder detected: ${latestFolder}"

                    def reportPath = "test-reports\\${latestFolder}\\automation-execution-report.html"

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
