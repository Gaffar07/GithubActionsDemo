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
    steps {
        script {
            def latestReportDir = bat(
                script: '''
                @echo off
                set "latestFolder="
                for /f "delims=" %%i in ('dir /b /ad /o-d test-reports') do (
                    set "latestFolder=%%i"
                    goto :found
                )
                :found
                if defined latestFolder (
                    echo test-reports\\%latestFolder%
                ) else (
                    echo No report folder found
                )
                exit /b 0
                ''',
                returnStdout: true
            ).trim()

            echo "Latest Extent Report Folder: ${latestReportDir}"

            // Optional: archive the report
            archiveArtifacts artifacts: "${latestReportDir}/**/*.*", fingerprint: true
        }
    }
}


    post {
        always {
            archiveArtifacts artifacts: 'test-reports/**', fingerprint: true
            
        }
    }
}
