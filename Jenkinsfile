pipeline {
    agent any

    

    stages {

        stage('Build') {
            steps {
                sh 'mvn clean install -DskipTests'
            }
        }

        stage('Run Cucumber Tests') {
            steps {
                sh 'mvn test -Dcucumber.filter.tags="@Hero"'
            }
        }

        stage('Publish Cucumber Report') {
            steps {
                publishHTML(target: [
                    reportDir: 'target/cucumber-html-reports',
                    reportFiles: 'overview-features.html',
                    reportName: 'Cucumber Report',
                    allowMissing: true,
                    alwaysLinkToLastBuild: true,
                    keepAll: true
                ])
            }
        }

        stage('Publish Extent Report') {
            steps {
                script {
                    def latestFolder = sh(
                        script: "ls -td test-reports/*/ | head -1",
                        returnStdout: true
                    ).trim()

                    echo "Latest Extent Report Folder: ${latestFolder}"

                    publishHTML(target: [
                        reportDir: latestFolder,
                        reportFiles: 'automation-execution-report.html',
                        reportName: 'Extent Report',
                        allowMissing: true,
                        alwaysLinkToLastBuild: true,
                        keepAll: true
                    ])
                }
            }
        }
    }
}
