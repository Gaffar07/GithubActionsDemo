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
                // the '|| exit 0' ensures the command always succeeds
                bat "rmdir /s /q ${BASE_REPORT_FOLDER}\\latest || exit 0"
            }
        }
    }
}

        stage('Build & Run Cucumber Tests') {
            steps {
                echo "Running Cucumber tests with tag @Hero..."
                bat """
                    mvn clean install -U -DskipTests
                    mvn clean test -Dcucumber.filter.tags="@Hero"
                """
            }
        }

       stage('Detect Latest Report Folder') {
    steps {
        script {
            echo "Detecting latest report folder..."
            // Windows batch version
            def latestReport = bat(script: """
                @echo off
                setlocal enabledelayedexpansion
                set latest=
                for /F "delims=" %%i in ('dir /b /ad /o-d test-reports') do (
                    set latest=%%i
                    goto :done
                )
                :done
                echo !latest!
            """, returnStdout: true).trim()

            if (latestReport) {
                echo "Latest report folder: ${latestReport}"
                env.LATEST_REPORT = latestReport
            } else {
                echo "No report folder found"
            }
        }
    }
}
       stage('Send Latest Report via Email') {
    when {
        expression { env.LATEST_REPORT != null }
    }
    steps {
        emailext (
            subject: "Cucumber Test Report",
            body: "Please find the attached latest Cucumber HTML report.",
            to: "gaffarshaikh07@gmail.com",
            attachmentsPattern: "test-reports\\${env.LATEST_REPORT}\\**\\automation-execution-report.html",
            mimeType: 'text/html'
        )
    }
}
    } // end stages

    post {
        always {
            echo "Pipeline finished. Check email and workspace for reports."
        }
    }
}
