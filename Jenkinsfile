pipeline {
  agent any

  stages {
    stage('Checkout') {
      steps {
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
        mkdir test-reports
        '''
      }
    }

    stage('Build & Test') {
      steps {
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

    stage('Publish Extent Report') {
      steps {
        script {
          if (fileExists('test-reports/ExtentReport.html')) {
            echo "Found Extent Report"
            archiveArtifacts artifacts: 'test-reports/ExtentReport.html', fingerprint: true
          } else {
            echo "No Extent Report found"
          }
        }
      }
    }
  }

  post {
    always {
      archiveArtifacts artifacts: '**/*.html', allowEmptyArchive: true
    }
  }
}
