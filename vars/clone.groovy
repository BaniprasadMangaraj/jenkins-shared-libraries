def call(String url, String branch){
  git url: "${url}", branch: "${branch}"
}




/*
### **What This Script Does:**
This script defines a **reusable Git checkout step** in Jenkins. It takes two inputs (`url` and `branch`) and clones a Git repository.

---

### **Line-by-Line Explanation:**
1. **`def call(String url, String branch)`**  
   - Defines a **Jenkins shared library method** named `call`.  
   - It accepts two **parameters**:  
     - `url` → Git repository URL (e.g., `"https://github.com/myproject.git"`).  
     - `branch` → Git branch name (e.g., `"main"` or `"dev"`).  

2. **`git url: "${url}", branch: "${branch}"`**  
   - Uses Jenkins’ built-in **`git` step** to clone the repository.  
   - `"${url}"` → Inserts the `url` parameter you passed.  
   - `"${branch}"` → Inserts the `branch` parameter.  

---

### **How to Use It in a Jenkinsfile:**
If your shared library is named **`gitTools`**, call it like this:
```groovy
@Library('jenkins-shared-libraries') _  

pipeline {
    agent any
    stages {
        stage('Checkout Code') {
            steps {
                gitTools(
                    url: "https://github.com/your-repo.git",
                    branch: "main"
                )
            }
        }
    }
}
```

---

### **Key Points:**
- **Purpose**: Avoid repeating the same `git` command in multiple pipelines.  
- **Inputs**: You must provide a **URL** and **branch** when calling it.  
- **Output**: Jenkins will **clone the repo** with the given branch.  

### **Example:**
```groovy
gitTools("https://github.com/example.git", "dev")
```
→ Clones the `example` repo’s `dev` branch.

---

### **Why Use This?**
- **Reusability**: Use the same Git checkout logic across all pipelines.  
- **Simplicity**: Just pass the URL/branch instead of writing full `git` steps repeatedly.  
*/