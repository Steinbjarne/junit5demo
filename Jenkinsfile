import java.time.ZoneId
import java.time.format.DateTimeFormatter

import static java.time.ZonedDateTime.now

pipeline {
    agent none
    options {
        timeout(time: 5, unit: 'DAYS')
        disableConcurrentBuilds()
    }
    stages {
        stage('Build') {
            agent any
            steps {
                script {
                    env.version = DateTimeFormatter.ofPattern('yyyy-MM-dd-HHmm').format(now(ZoneId.of('UTC')))
                    sh "mvn clean package"
                }
            }
        }
        stage('Master build') {
            agent any
            when { branch 'master' }
            steps {
                script {
                    env.version = DateTimeFormatter.ofPattern('yyyy-MM-dd-HHmm').format(now(ZoneId.of('UTC')))
                    sh "whoami"
                    sh "mvn clean package"
                }
            }
        }

    }
    post {
        changed {
            echo "Changed from failure to success"
        }
        unstable {
            echo "Build is unstable"
        }
        failure {
            echo "Whops, build failed"
        }
        aborted {
            echo "User aborted build"
        }
        success {
            echo "Waddayaknow. Succeeded!"
        }
        always {
            echo "Always show this message"
        }
    }
}
