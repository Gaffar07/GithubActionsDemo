pipeline {
  agent any

  stages {
    stage('Checkout') {
      steps {
        checkout scm
        bat 'echo Workspace: %CD%'
      }
    }


    stage('Build & Test') {
      steps {
        bat 'mvn clean install'
        bat '''
        mvn clean test ^
          -Dcucumber.filter.tags="@Hero" ^
          -Dcucumber.plugin="json:target/cucumber.json" ^
        
        '''
      }
    }

    stage('Publish Extent Report') {
      steps {
        script {
          if (fileExists('test-reports/automation-execution-report.html')) {
            echo "Found Extent Report"
            archiveArtifacts artifacts: 'test-reports/automation-execution-report.html', fingerprint: true
          } else {
            echo "No Extent Report found"
          }
        }
      }
    }
  }

 post {
  always {
    archiveArtifacts artifacts: 'test-reports/*.html,target/extent-reports/*.html', allowEmptyArchive: true
  }
}



}
