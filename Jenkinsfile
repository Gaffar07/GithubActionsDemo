pipeline {
  agent any


  stages {
    stage('Checkout') {
      steps {
        // If you’re using “Pipeline script from SCM”, Jenkins will auto-checkout.
        // For simple pipelines, this ensures workspace is up to date.
        checkout scm
        bat 'echo Workspace: %CD%'
      }
    }

    stage('Clean old reports') {
      steps {
        bat '''
        @echo off
        echo Deleting old reports...
        rmdir /s /q test-reports 2>nul || echo No test-reports folder to delete
        rmdir /s /q target 2>nul || echo No target folder to delete
        '''
      }
    }

    stage('Build & Test') {
      steps {
        // First install to refresh dependencies, then run tagged tests
        bat 'mvn clean install'
        bat '''
        mvn clean test ^
          -Dcucumber.filter.tags="@Hero" ^
          -Dcucumber.plugin="json:target/cucumber.json" ^
          -Dextent.reporter.html.start=true ^
          -Dextent.reporter.html.out=test-reports/ExtentReport.html
        '''
      }
    }

    stage('Publish latest Extent report') {
      steps {
        script {
          // Find the newest folder under test-reports (if any)
          def latestReportDir = bat(
            script: '''
            @echo off
            setlocal enabledelayedexpansion
            set "latestFolder="

            if not exist test-reports (
              echo No report folder found
              exit /b 0
            )

            for /f "delims=" %%i in ('dir /b /ad /o-d test-reports') do (
              set "latestFolder=%%i"
              goto :found
            )

            :found
            if defined latestFolder (
              echo test-reports\\!latestFolder!
            ) else (
              echo No report folder found
            )
            exit /b 0
            ''',
            returnStdout: true
          ).trim()

          echo "Latest Extent Report Folder: ${latestReportDir}"

          // If we found a path, archive its contents, otherwise skip gracefully
          if (latestReportDir && latestReportDir != '' && !latestReportDir.contains('No report folder')) {
            archiveArtifacts artifacts: "${latestReportDir}/**/*.*", fingerprint: true
          } else {
            echo 'No report folder to archive'
          }
        }
      }
    }
  }

  post {
  always {
    // Archive cucumber JSON if it exists (wildcard to catch variations)
    archiveArtifacts artifacts: '**/cucumber*.json', onlyIfSuccessful: false, fingerprint: true

    // Archive all Extent reports and PDFs
    archiveArtifacts artifacts: 'test-reports/**/*.*', onlyIfSuccessful: false, fingerprint: true

    // Show workspace status for debugging
    bat 'dir /ad /o-d'
    bat 'dir /s /b test-reports'
    bat 'dir /s /b target'
  }
  success {
    echo 'Pipeline finished successfully.'
  }
  failure {
    echo 'Pipeline failed — check console logs and the Publish stage output.'
  }
}

}
