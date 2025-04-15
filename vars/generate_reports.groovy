def call(Map config = [:]) {
    def projectName = config.projectName ?: 'Project'
    def imageName = config.imageName ?: ''
    def imageTag = config.imageTag ?: ''
    
    echo "Generating build report..."
    
    // Create directory for reports
    sh "mkdir -p reports"
    
    // Generate report
    sh """
        echo "===== ${projectName} Build Report =====" > reports/build-report.txt
        echo "Generated: \$(date)" >> reports/build-report.txt
        echo "" >> reports/build-report.txt
        echo "Build Number: ${env.BUILD_NUMBER}" >> reports/build-report.txt
        echo "Docker Images: ${imageName}" >> reports/build-report.txt
        echo "Image Tag: ${imageTag}" >> reports/build-report.txt
        echo "Build Status: ${currentBuild.result ?: 'SUCCESS'}" >> reports/build-report.txt
        echo "Build URL: ${env.BUILD_URL}" >> reports/build-report.txt
    """
    
    // Archive the report
    archiveArtifacts artifacts: 'reports/*', allowEmptyArchive: true
}





/*
### **What This Script Does**
This script creates a simple text report containing build information and archives it for later access.

---

### **Line-by-Line Explanation**

1. **Configuration Setup**
   ```groovy
   def projectName = config.projectName ?: 'Project'  // Default to 'Project' if not specified
   def imageName = config.imageName ?: ''           // Empty string if not provided
   def imageTag = config.imageTag ?: ''             // Empty string if not provided
   ```
   - Sets up configurable values with safe defaults

2. **Report Initialization**
   ```groovy
   echo "Generating build report..."
   sh "mkdir -p reports"  // Creates reports directory if it doesn't exist
   ```

3. **Report Content Generation**
   ```groovy
   sh """
     echo "===== ${projectName} Build Report =====" > reports/build-report.txt
     echo "Generated: \$(date)" >> reports/build-report.txt
     ...
   """
   ```
   Writes these details to the report:
   - Project name (configurable)
   - Current date/time
   - Jenkins build number (`${env.BUILD_NUMBER}`)
   - Docker image info (if provided)
   - Build status (SUCCESS by default)
   - Build URL (links back to this build in Jenkins)

4. **Artifact Archiving**
   ```groovy
   archiveArtifacts artifacts: 'reports/*', allowEmptyArchive: true
   ```
   - Saves the report as a build artifact
   - `allowEmptyArchive` prevents failures if somehow no report was created

---

### **Example Usage**
```groovy
generateReport(
    projectName: "Customer Portal",
    imageName: "customer-portal-web",
    imageTag: "v2.1.0"
)
```

---

### **Key Benefits**
1. **Visibility** - Creates permanent record of build details
2. **Configurable** - Adapts to different projects
3. **Accessible** - Archived report appears in Jenkins UI
4. **Non-blocking** - Won't fail even if some info is missing

---

### **Real-World Analogy**
Think of this like an **automated build receipt** that:
- Records what was built
- When it happened
- How to find it again
- Gets filed automatically for your records


*/