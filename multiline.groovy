pipeline {
    agent any
    stages {
        stage('Example') {
            steps {
                sh """
                    echo "This is line 1"
                    echo "This is line 2"
                    echo "This is line 3"
                """
            }
        }
    }
}
