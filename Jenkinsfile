pipeline {
    agent any

    environment {
        BASE_REPORT_FOLDER = "test-reports"
    }

    stages {

        stage('Checkout SCM') {
            steps {
                checkout scm
            }
        }

        stage('Clean Latest Report') {
            steps {
                script {
                    echo "Cleaning latest report folder..."
                    if (isUnix()) {
                        sh "rm -rf ${BASE_REPORT_FOLDER}/latest || true"
                    } else {
                        bat "rmdir /s /q ${BASE_REPORT_FOLDER}\\latest || echo Folder not found"
                    }
                }
            }
        }

        stage('Build & Run Cucumber Tests') {
            steps {
                echo "Running Cucumber tests with tag @Hero..."
                bat """
                    mvn clean install -U -DskipTests
                    mvn test -Dcucumber.filter.tags="@Hero"
                """
            }
        }

        stage('Detect Latest Report Folder') {
            steps {
                script {
                    def latestReport = ''
                    if (isUnix()) {
                        latestReport = sh(
                            script: "ls -td ${BASE_REPORT_FOLDER}/*/ 2>/dev/null | head -1",
                            returnStdout: true
                        ).trim()
                    } else {
                        latestReport = bat(
                            script: 'for /F "delims=" %i in (\'dir /b /ad /o-d test-reports\') do @echo %i & goto :done',
                            returnStdout: true
                        ).trim()
                    }

                    if (!latestReport) {
                        echo "No report folder found."
                        currentBuild.description = "No report generated"
                        env.LATEST_REPORT = ''
                    } else {
                        echo "Latest report folder detected: ${latestReport}"
                        env.LATEST_REPORT = latestReport
                    }
                }
            }
        }

        stage('Send Latest Report via Email') {
            when {
                expression { env.LATEST_REPORT != '' }
            }
            steps {
                script {
                    def reportPath = "${env.LATEST_REPORT}/automation-execution-report.html"
                    echo "Sending report via email: ${reportPath}"

                    emailext(
                        subject: "Cucumber Test Report - ${env.JOB_NAME} #${env.BUILD_NUMBER}",
                        body: """
                            <p>Hello Team,</p>
                            <p>The latest Cucumber test execution report is attached.</p>
                            <p>Job: ${env.JOB_NAME} <br/>
                            Build Number: ${env.BUILD_NUMBER} <br/>
                            Build URL: <a href="${env.BUILD_URL}">${env.BUILD_URL}</a></p>
                        """,
                        to: "gaffarshaikh07@gmail.com",
                        attachmentsPattern: reportPath,
                        mimeType: 'text/html'
                    )
                }
            }
        }

    } // end stages

    post {
        always {
            echo "Pipeline finished. Check email and workspace for reports."
        }
    }
}
