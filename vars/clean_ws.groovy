def call() {
    echo "Cleaning up workspace..."
    cleanWs()
}





/*
### Groovy Script:
```groovy
def call() {
    echo "Cleaning up workspace..."
    cleanWs()
}
```

### Explanation:

1. **`def call()`**  
   - This defines a **Jenkins Pipeline step** named `call()`.
   - In Jenkins shared libraries, `call()` is the **default method** that gets executed when the library is invoked.
   - When you use this library in a Jenkinsfile (e.g., `yourSharedLib()`), Jenkins automatically looks for and runs the `call()` method.

2. **`echo "Cleaning up workspace..."`**  
   - `echo` is a **Jenkins Pipeline step** that prints a message to the console log.
   - Here, it logs the message `"Cleaning up workspace..."` to indicate that the workspace cleanup is starting.

3. **`cleanWs()`**  
   - `cleanWs()` is a **step provided by the Jenkins Workspace Cleanup Plugin**.
   - It **deletes all files** in the Jenkins workspace to ensure a clean state for the next build.
   - This helps avoid conflicts from leftover files from previous builds.

### How to Use This in a Jenkinsfile:
If this script is part of a shared library (e.g., `jenkins-shared-libraries`), you can call it in a `Jenkinsfile` like this:

```groovy
@Library('jenkins-shared-libraries') _  // Load the shared library

pipeline {
    agent any
    stages {
        stage('Clean Workspace') {
            steps {
                yourSharedLib()  // This executes the `call()` method
            }
        }
    }
}
```

### Key Notes:
- **`call()`** is the **default entry point** for shared libraries in Jenkins.
- **`cleanWs()`** requires the **Workspace Cleanup Plugin** to be installed in Jenkins.
- Shared libraries help **reuse code** across multiple Jenkins pipelines.

*/