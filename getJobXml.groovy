pipeline {
    agent any

    stages {
        stage('Generate Job XMLs') {
            steps {
                script {
                    // Get the workspace directory
                    def workspaceDir = env.WORKSPACE

                    // Iterate through all jobs
                    Jenkins.instance.getAllItems(Job.class).each { job ->
                        // Get job name and configuration as XML
                        def jobName = job.fullName
                        def jobXml = job.getConfigFile().asString()

                        // Create a file with the job's name and store the XML content
                        def fileName = "${workspaceDir}/${jobName.replace('/', '_')}.xml"
                        writeFile file: fileName, text: jobXml

                        // Print a message to indicate success
                        echo "Generated XML for job '${jobName}' and saved as '${fileName}'"
                    }
                }
            }
        }
    }

    post {
        success {
            echo 'Job XMLs have been successfully generated and saved in the workspace.'
        }
        failure {
            echo 'There was an error generating job XMLs.'
        }
    }
}
