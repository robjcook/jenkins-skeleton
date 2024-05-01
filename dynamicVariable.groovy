pipeline {
    agent any
    stages {
        stage('Example') {
            steps {
                script {
                    def commandOutput = sh(script: 'echo "Hello, World!"', returnStdout: true).trim()
                    echo "Output of the command: ${commandOutput}"
                }
            }
        }
    }
}
