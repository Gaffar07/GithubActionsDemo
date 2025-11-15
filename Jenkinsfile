pipeline {
    agent any

    stages {

        stage('Build') {
            steps {
                bat 'mvn clean install -DskipTests'
            }
        }

        stage('Run Cucumber Tests') {
            steps {
                bat 'mvn test -Dcucumber.filter.tags="@smoke"'
            }
        }

        stage('Publish Extent Report') {
            steps {
                script {
                    // Find the newest timestamp folder in test-reports
                    def latestFolder = bat(
                        script: 'powershell -command "(Get-ChildItem -Directory test-reports | Sort-Object LastWriteTime -Descending | Select-Object -First 1).FullName"',
                        returnStdout: true
                    ).trim()

                    echo "Latest Extent Report Folder: ${latestFolder}"

                    publishHTML(target: [
                        reportDir: latestFolder,
                        reportFiles: 'automation-execution-report.html',
                        reportName: 'Extent Report',
                        allowMissing: false,
                        alwaysLinkToLastBuild: true,
                        keepAll: true
                    ])
                }
            }
        }
    }
}
