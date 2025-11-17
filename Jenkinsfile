pipeline {
    agent any

    stages {

        stage('Checkout') {
            steps {
                bat 'mvn clean install'
            }
        }

        stage('Build & Test') {
            steps {
                bat '''
                    mvn clean test ^
                    -Dcucumber.filter.tags="@Hero" ^
                    -Dcucumber.plugin="json:target/cucumber.json" ^
                    -Dextent.reporter.html.start=true ^
                    -Dextent.reporter.html.out=test-reports/ExtentReport.html
                '''
            }
        }

       stage('Publish Extent Report') {
    steps {
        publishHTML([
            reportDir: 'test-reports',
            reportFiles: 'ExtentReport.html',
            reportName: 'Extent Automation Report',
            allowMissing: false,
            alwaysLinkToLastBuild: true,
            keepAll: true
        ])
    }
}


    post {
        always {
            archiveArtifacts artifacts: 'test-reports/**', fingerprint: true
        }
    }
}

}