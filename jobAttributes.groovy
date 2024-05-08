import jenkins.model.Jenkins

def jobName = "Your_Job_Name"
def job = Jenkins.instance.getItemByFullName(jobName)

if (job) {
    println "Job Attributes:"
    job.metaClass.properties.each { prop ->
        println "- ${prop.name}: ${job."${prop.name}"}"
    }
} else {
    println "Job not found: $jobName"
}
