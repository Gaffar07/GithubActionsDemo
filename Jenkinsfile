pipeline {
    agent any

    stages {
        stage('Checkout') {
            steps {
                bat 'mvn clean install'
            }
        }
        
        stage('Clean Old Reports') {
    		steps {
        			bat 'echo Deleting old reports...'
        			bat 'rmdir /s /q test-reports 2>nul || exit 0'
        			bat 'rmdir /s /q target 2>nul || exit 0'
    }
}

        stage('Build & Test') {
            steps {
                bat 'mvn clean test -Dcucumber.filter.tags="@Hero" -Dcucumber.plugin="json:target/cucumber.json" -Dextent.reporter.html.start=true -Dextent.reporter.html.out=test-reports/ExtentReport.html '
            }
        }

      stage('Publish Latest Extent Report') {
    steps {
        script {
           def latestReportDir = bat(
    			script: 'for /f "delims=" %%i in (\'dir /b /ad /o-d test-reports\') do @echo test-reports\\%%i & exit /b',
    			returnStdout: true
		    ).trim().tokenize('\r\n').last()


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
