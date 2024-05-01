pipeline {
    agent any
    stages {
        stage('Input') {
            steps {
                script {
                    def userInput = input(message: 'Enter a value:', parameters: [string(defaultValue: '', description: 'Enter a value')])
                    
                    if (userInput == 'some value') {
                        sh 'your_shell_command_here'
                    }
                }
            }
        }
    }
}
