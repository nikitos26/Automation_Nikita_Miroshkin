pipeline {
    agent any

    tools {
        // Install the Maven version configured as "M3" and add it to the path.
        maven "MAVEN_HOME"
    }

    stages {
        stage('Project Build') {
            steps{
                sh 'mvn clean install -DskipTests=true'
            }
        }

        stage('Run Suite') {
            steps{
                sh 'mvn test -Dsuite=${SUITE} -Dconfig=${CONFIG}'
            }
        }

        stage('Copy logs') {
            steps{
                archiveArtifacts artifacts: 'target/logs/*', followSymlinks: false
            }
        }
    }

    post('Allure report') {
        always{
            allure includeProperties: false, jdk: '', results: [[path: 'target/allure-results']]
        }
    }
}