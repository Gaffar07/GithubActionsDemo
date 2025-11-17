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
                bat 'mvn clean test -Dcucumber.filter.tags="@Hero" -Dcucumber.plugin="json:target/cucumber.json" -Dextent.reporter.html.start=true -Dextent.reporter.html.out=test-reports/ExtentReport.html '
            }
        }

      stage('Publish Latest Extent Report') {
    script {
        bat '''
        for /F "delims=" %i in ('dir /b /ad /o-d test-reports') do @echo test-reports\\%i   & exit /b 
        '''
    }
}


            publishHTML([
                reportDir: latestReportDir,
                reportFiles: 'automation-execution-report.html',
                reportName: 'Extent Automation Report'
            ])
        }
    }
}
    }

    post {
        always {
            archiveArtifacts artifacts: 'test-reports/**', fingerprint: true
            
        }
    }
}
