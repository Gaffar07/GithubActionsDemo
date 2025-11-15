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

        stage('Clean Latest Report') {
            steps {
                echo "Cleaning latest report folder..."
                bat 'rmdir /s /q test-reports\\latest || echo Folder not found'
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

        stage('Archive Reports') {
            steps {
                script {
                    def timestamp = new Date().format("dd-MMM-yy_HH-mm-ss")
                    def latestDir = "test-reports/latest"
                    def archivedDir = "test-reports/${timestamp}"

                    echo "Archiving latest report to: ${archivedDir}"
                    bat "xcopy /E /I /Y \"${latestDir}\" \"${archivedDir}\""

                    publishHTML([
                        allowMissing: false,
                        alwaysLinkToLastBuild: true,
                        keepAll: true,
                        reportDir: latestDir,
                        reportFiles: 'automation-execution-report.html',
                        reportName: "Automation Execution Report"
                    ])

                    archiveArtifacts artifacts: 'test-reports/**', allowEmptyArchive: true
                }
            }
        }
    }

    post {
        always {
            echo "Archiving surefire XML reports..."
            archiveArtifacts artifacts: 'target/surefire-reports/*.xml', allowEmptyArchive: true
        }

        failure {
            echo "Build failed! Check logs and report for details."
        }
    }
}
