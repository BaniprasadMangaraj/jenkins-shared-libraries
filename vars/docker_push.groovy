def call(Map config = [:]) {
    def imageName = config.imageName ?: error("Image name is required")
    def imageTag = config.imageTag ?: 'latest'
    def credentials = config.credentials ?: 'docker-hub-credentials'
    
    echo "Pushing Docker image: ${imageName}:${imageTag}"
    
    withCredentials([usernamePassword(
        credentialsId: credentials,
        usernameVariable: 'DOCKER_USERNAME',
        passwordVariable: 'DOCKER_PASSWORD'
    )]) {
        sh """
            echo "\$DOCKER_PASSWORD" | docker login -u "\$DOCKER_USERNAME" --password-stdin
            docker push ${imageName}:${imageTag}
            docker push ${imageName}:latest
        """
    }
}






/*
### **What This Script Does**
This script pushes a Docker image to a registry (like Docker Hub) with:
1. Required image name
2. Optional tag (defaults to "latest")
3. Secure credential handling

---

### **Line-by-Line Breakdown**

1. **`def call(Map config = [:])`**  
   - Creates a flexible method that accepts configuration options
   - Example: `dockerPush(imageName: "my-app", imageTag: "v1.0")`

2. **`def imageName = config.imageName ?: error(...)`**  
   - Makes `imageName` mandatory - fails if missing

3. **`def imageTag = config.imageTag ?: 'latest'`**  
   - Defaults to "latest" tag if none specified

4. **`def credentials = config.credentials ?: 'docker-hub-credentials'`**  
   - Uses Jenkins credential ID "docker-hub-credentials" by default

5. **`withCredentials([usernamePassword(...)])`**  
   - Safely injects Docker registry credentials
   - Creates environment variables `DOCKER_USERNAME` and `DOCKER_PASSWORD`

6. **`sh` block**  
   - Logs into Docker using credentials
   - Pushes both the specified tag and "latest" tag

---

### **Example Usage**
```groovy
// In your Jenkinsfile
dockerPush(
    imageName: "my-company/my-app",
    imageTag: "v1.5",
    credentials: "prod-docker-creds"  // Optional
)
```

---

### **Key Features**
1. **Security**  
   - Never exposes credentials in logs
   - Uses Jenkins credential manager

2. **Convenience**  
   - Pushes both versioned and "latest" tags automatically
   - Default credential ID reduces repetition

3. **Flexibility**  
   - Works with any Docker registry (Docker Hub, ECR, etc.)
   - Customizable credentials per environment

---

### **Real-World Analogy**
Think of this like a **secure package delivery service** where:
- You must specify what to send (`imageName`)
- It adds default labels if none given (`latest` tag)
- Uses locked security boxes for credentials (`withCredentials`)


*/