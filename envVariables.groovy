pipeline {
    agent any

    stages {
        stage('Assume Role') {
            steps {
                script {
                    // Execute AWS CLI command to assume role
                    def assumeRoleOutput = sh(script: 'aws sts assume-role --role-arn "arn:aws:iam::123456789012:role/YourRoleName" --role-session-name "YourSessionName" --output json', returnStdout: true).trim()
                    
                    // Parse JSON output using jq
                    def awsAccessKeyId = sh(script: "echo '${assumeRoleOutput}' | jq -r '.Credentials.AccessKeyId'", returnStdout: true).trim()
                    def awsSecretAccessKey = sh(script: "echo '${assumeRoleOutput}' | jq -r '.Credentials.SecretAccessKey'", returnStdout: true).trim()
                    def awsSessionToken = sh(script: "echo '${assumeRoleOutput}' | jq -r '.Credentials.SessionToken'", returnStdout: true).trim()
                    
                    // Set environment variables
                    env.AWS_ACCESS_KEY_ID = awsAccessKeyId
                    env.AWS_SECRET_ACCESS_KEY = awsSecretAccessKey
                    env.AWS_SESSION_TOKEN = awsSessionToken
                }
            }
        }
        
        // Add additional stages as needed
    }
    
    // Add post-build actions or other pipeline configurations
}
