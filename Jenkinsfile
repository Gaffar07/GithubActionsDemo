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

        stage('Upload Cucumber HTML Report') {
            when {
                expression { latestReport != null }
            }
            steps {
                echo "Archiving report folder: test-reports\\${latestReport}"
                archiveArtifacts artifacts: "test-reports\\${latestReport}\\**/*", allowEmptyArchive: true
            }
        }

    }

    post {
        always {
            echo 'Pipeline finished!'
        }
    }
}
