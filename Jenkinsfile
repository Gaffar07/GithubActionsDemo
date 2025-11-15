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
                bat 'mvn test -Dcucumber.filter.tags="@Hero"'
            }
        }

        stage('Publish Extent Report') {
            steps {
                script {
                    def latestFolder = powershell(
                        script: '(Get-ChildItem -Directory "test-reports" | Sort-Object LastWriteTime -Descending | Select-Object -First 1).FullName',
                        returnStdout: true
                    ).trim()

                    echo "Latest Extent Report Folder: ${latestFolder}"

                    publishHTML(target: [
                        reportDir: latestFolder,
                        reportFiles: 'automation-execution-report.html',
                        reportName: 'Extent Report',
                        keepAll: true,
                        alwaysLinkToLastBuild: true,
                        allowMissing: false
                    ])
                }
            }
        }
    }
}
