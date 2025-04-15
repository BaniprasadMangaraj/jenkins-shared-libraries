def call(Map config = [:]) {
    def imageName = config.imageName ?: error("Image name is required")
    def imageTag = config.imageTag ?: 'latest'
    def dockerfile = config.dockerfile ?: 'Dockerfile'
    def context = config.context ?: '.'
    
    echo "Building Docker image: ${imageName}:${imageTag} using ${dockerfile}"
    
    sh """
        docker build -t ${imageName}:${imageTag} -t ${imageName}:latest -f ${dockerfile} ${context}
    """
}





/*### **What This Script Does**
This is a reusable Docker build command that:
1. Takes configuration options (with sensible defaults)
2. Builds a Docker image with the specified name and tags
3. Can use custom Dockerfile locations and build contexts

---

### **Line-by-Line Explanation**

1. **`def call(Map config = [:])`**  
   - Creates a flexible method that accepts optional configuration parameters
   - Example usage: `dockerBuild(imageName: "my-app", imageTag: "v1.0")`

2. **`def imageName = config.imageName ?: error("Image name is required")`**  
   - Makes `imageName` mandatory - fails with error if not provided
   - `?:` means "use this default value if missing"

3. **`def imageTag = config.imageTag ?: 'latest'`**  
   - Sets default tag to "latest" if none specified

4. **`def dockerfile = config.dockerfile ?: 'Dockerfile'`**  
   - Defaults to "Dockerfile" in current directory

5. **`def context = config.context ?: '.'`**  
   - Defaults to current directory (`.`) as build context

6. **`echo` statement**  
   - Prints what's being built for visibility in logs

7. **`sh` block**  
   - The actual Docker build command that:
     - Tags the image twice (with specified tag + "latest")
     - Uses specified Dockerfile location
     - Uses specified build context

---

### **Example Usage**
```groovy
// In your Jenkinsfile
dockerBuild(
    imageName: "my-app",
    imageTag: "v1.2",
    dockerfile: "docker/production.Dockerfile",
    context: "app-folder"
)
```

---

### **Key Features**
1. **Flexible Configuration**  
   - Only imageName is required (other fields have defaults)
   - Can override any default when needed

2. **Safety**  
   - Explicit error if required field (imageName) is missing
   - Clear logging of what's being built

3. **Convenience**  
   - Automatically tags with both version and "latest"
   - Sensible defaults reduce boilerplate

---

### **Real-World Analogy**
Think of this like a **"Docker Build Shortcut"** where:
- You must give the image a name (like naming a baby)
- Everything else is optional (like choosing middle names)
- It does the standard tagging automatically (like adding "Jr.")

*/