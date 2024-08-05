Jenkins.instance.getAllItems(Job.class).each { job ->
    println "Job Name: ${job.fullName}"
    println "Job XML Configuration:"
    println "------------------------"
    // Get the job configuration as XML
    def jobXml = job.getConfigFile().asString()
    println jobXml
    println "------------------------\n"
}
