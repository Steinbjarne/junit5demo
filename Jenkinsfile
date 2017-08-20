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
            when { expression { env.BRANCH_NAME.matches(/(feature|bugfix|demo)\/(\w+-\w+)/) } }
            agent any
            steps {
                script {
                    env.version = DateTimeFormatter.ofPattern('yyyy-MM-dd-HHmm').format(now(ZoneId.of('UTC')))
                    sh "mvn clean install"
                }
            }
        }
        stage('Master build') {
            when { branch 'master' }
            steps {
                script {
                    env.version = DateTimeFormatter.ofPattern('yyyy-MM-dd-HHmm').format(now(ZoneId.of('UTC')))
                    sh "mvn clean install"
                }
            }
        }
        stage('Archive artifact') {
            archiveArtifacts artifacts: '**/target/*.jar', fingerprint: true
        }
        stage('Upload artifact') {
            nexusPublisher nexusInstanceId: 'localNexus',
                    nexusRepositoryId: 'releases',
                    packages: [[$class: 'MavenPackage',
                                mavenAssetList: [[classifier: '', extension: '', filePath: 'jar/target/jenkins.jar']],
                                mavenCoordinate: [artifactId: 'jenkins-ja', groupId: 'org.jenkins-ci.main', packaging: 'jar', version: '2.23']]]
        }
    }
    post {
        changed {
            echo "*******************************"
            echo "Changed from failure to success"
        }
        unstable {
            echo "*****************"
            echo "Build is unstable"
        }
        failure {
            echo "*******************************"
            echo "Whops, build failed"
        }
        aborted {
            echo "******************"
            echo "User aborted build"
        }
        success {
            echo "***********************"
            echo "Waddayaknow. Succeeded!"
        }
        always {
            echo "************************"
            echo "Always show this message"
        }
    }
}