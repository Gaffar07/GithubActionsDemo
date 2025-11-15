pipeline {
    agent any

    environment {
        JAVA_HOME = "C:\\Program Files\\Java\\jdk-17" // Adjust if needed
        PATH = "${env.JAVA_HOME}\\bin;${env.PATH}"
    }

    stages {

        stage('Checkout Code') {
            steps {
                echo 'Checking out code from Git...'
                checkout([$class: 'GitSCM', 
                    branches: [[name: '*/main']], 
                    userRemoteConfigs: [[url: 'https://github.com/Gaffar07/GithubActionsDemo.git']]
                ])
            }
        }

        stage('Set up JDK 17') {
            steps {
                echo 'Using JDK 17...'
                bat 'java -version'
            }
        }

        stage('Clean, Build & Run Smoke Tests') {
            steps {
                echo 'Running Maven clean, install and Cucumber tests...'
                bat '''
                    mvn clean install -U -DskipTests
                    mvn test -Dcucumber.filter.tags="@Hero"
                '''
            }
        }

        stage('Find Latest Report Folder') {
            steps {
                script {
                    echo 'Finding latest test report folder...'
                    latestReport = bat(
                        script: '''
                            @echo off
                            setlocal enabledelayedexpansion
                            set latest=
                            for /f "tokens=*" %%i in ('dir /b /ad /o-d test-reports') do (
                                set latest=%%i
                                goto done
                            )
                            :done
                            echo !latest!
                        ''',
                        returnStdout: true
                    ).trim()

                    if (latestReport == "") {
                        echo "No report folder found."
                        latestReport = null
                    } else {
                        echo "Latest report folder: ${latestReport}"
                    }
                }
            }
        }

     stage('Send Latest Cucumber HTML Report via Email') {
    steps {
        script {
            // Detect latest report folder dynamically
            def latestReport = ''
            if (isUnix()) {
                // Linux / macOS
                latestReport = sh(
                    script: "ls -td test-reports/*/ 2>/dev/null | head -1",
                    returnStdout: true
                ).trim()
            } else {
                // Windows
                latestReport = bat(
                    script: 'for /F "delims=" %i in (\'dir /b /ad /o-d test-reports\') do @echo %i & goto :done',
                    returnStdout: true
                ).trim()
            }

            if (!latestReport) {
                echo "No report folder found. Skipping email."
            } else {
                // Construct full path to HTML report
                def reportPath = "${latestReport}/automation-execution-report.html"
                echo "Sending report via email: ${reportPath}"

                // Send email with Email Extension Plugin
                emailext(
                    subject: "Cucumber Test Report - ${env.JOB_NAME} #${env.BUILD_NUMBER}",
                    body: """
                        <p>Hello Team,</p>
                        <p>The latest Cucumber test execution report is attached.</p>
                        <p>Job: ${env.JOB_NAME} <br/>
                        Build Number: ${env.BUILD_NUMBER} <br/>
                        Build URL: <a href="${env.BUILD_URL}">${env.BUILD_URL}</a></p>
                    """,
                    to: "gaffarshaikh07@gmail.com",   // Replace/add recipients here
                    attachmentsPattern: reportPath,
                    mimeType: 'text/html'
                )
            }
        }
    }
}

    post {
        always {
            echo 'Pipeline finished!'
        }
    }
}
