// pipeline del Program 6: compila, prueba y corre el programa
pipeline {
    agent any

    options {
        skipStagesAfterUnstable()
    }

    // revisa GitLab cada 2 minutos por si hay cambios
    triggers {
        pollSCM('H/2 * * * *')
    }

    stages {
        stage('Build') {
            steps {
                sh 'mvn -B -DskipTests clean package'
            }
        }
        stage('Test') {
            steps {
                sh 'mvn -B verify'
            }
            post {
                always {
                    junit 'target/surefire-reports/*.xml'
                }
            }
        }
        stage('Deliver') {
            steps {
                sh 'java -cp target/classes App'
            }
        }
    }
}
