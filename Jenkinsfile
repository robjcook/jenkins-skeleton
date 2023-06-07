pipeline {
    options { ansiColor() }
    environment {
        TF_PLUGIN_CACHE_DIR = "$HOME/.terraform.d/plugin-cache"
        http_proxy=''
        no_proxy='*.amazonaws.com'
    }
    stages {
        stage('main') {
            steps {
                sh "terraform init"
                sh "terraform get -update"
                sh "terraform plan -input=false"
                sh "terraform apply -input=false -auto-approve"
            }
        }
    }
}