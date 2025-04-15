def call() {
    echo "Running unit tests..."
    
    // Add your unit test commands here
    // For example:
    // sh "npm test" or "mvn test" depending on your project
    
    echo "Unit tests completed successfully"
}





/*### **What This Script Does**
This is a template for running unit tests in Jenkins. Currently it just:
1. Announces when tests start
2. Has placeholder for your test commands
3. Announces when tests finish

---

### **Line-by-Line Explanation**

1. **`def call()`**  
   - Defines the default function that runs when you call this shared library

2. **`echo "Running unit tests..."`**  
   - Prints a message to the Jenkins console when tests begin

3. **Comment Section**  
   ```groovy
   // Add your unit test commands here
   // For example:
   // sh "npm test" or "mvn test" depending on your project
   ```
   - This is where you would add your actual test commands

4. **`echo "Unit tests completed successfully"`**  
   - Prints a completion message (note: this will show even if tests fail)

---

### **How to Customize It**
Replace the comments with your actual test commands:

```groovy
def call() {
    echo "Running unit tests..."
    
    // JavaScript/Node.js example:
    sh "npm test"
    
    // Java/Maven example:
    // sh "mvn test"
    
    // Python example:
    // sh "python -m pytest"
    
    echo "Unit tests completed successfully"
}
```

---

### **Example Usage in Jenkinsfile**
```groovy
@Library('your-shared-library') _
pipeline {
    agent any
    stages {
        stage('Test') {
            steps {
                runUnitTests()  // Calls this function
            }
        }
    }
}
```

---

### **Important Notes**
1. Currently **doesn't handle test failures** (would need try/catch)
2. The success message appears **before** tests actually run (need to fix ordering)
3. You should **add your specific test commands**
*/