import jenkins.model.Jenkins

def printJobInfo(job, folderPath) {
    def assignedNode = job.assignedLabelString ?: "Master"
    println "${folderPath}${job.fullName}: ${Jenkins.instance.getRootUrl()}job/${job.fullName} (View: ${folderPath}, Targeted Node: ${assignedNode})"
    
    def buildDiscarderProperty = job.getProperty(jenkins.model.BuildDiscarderProperty.class)
    if (buildDiscarderProperty != null) {
        def strategy = buildDiscarderProperty.strategy
        if (strategy != null) {
            println "   Build Discard Strategy: ${strategy.getClass().getSimpleName()}"
            if (strategy instanceof jenkins.model.BuildDiscarder.LogRotator) {
                def logRotator = (jenkins.model.BuildDiscarder.LogRotator) strategy
                println "      Days to keep: ${logRotator.getDaysToKeep()}"
                println "      Number to keep: ${logRotator.getNumToKeep()}"
                println "      Artifact days to keep: ${logRotator.getArtifactDaysToKeep()}"
                println "      Artifact number to keep: ${logRotator.getArtifactNumToKeep()}"
            }
        }
    }
}

def printFolderInfo(folder, folderPath) {
    folder.getAllItems().each { item ->
        if (item instanceof jenkins.model.AbstractItem) {
            printJobInfo(item, folderPath)
        } else if (item instanceof com.cloudbees.hudson.plugins.folder.Folder) {
            printFolderInfo(item, "${folderPath}${folder.fullName}/")
        }
    }
}

Jenkins.instance.getAllViews().each { view ->
    if (view instanceof com.cloudbees.hudson.plugins.folder.Folder) {
        printFolderInfo(view, view.fullName + "/")
    } else {
        view.getItems().each { item ->
            printJobInfo(item, "")
        }
    }
}
