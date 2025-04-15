def call(){
  sh "trivy fs ."
}




/*

### **What This Script Does**
This simple script runs a Trivy filesystem scan on your project's code to find vulnerabilities.

---

### **Line-by-Line Explanation**

1. **`def call()`**  
   - Defines the default Jenkins shared library function

2. **`sh "trivy fs ."`**  
   - Runs Trivy filesystem scan on current directory (`.`)
   - Checks for vulnerabilities in dependencies and configuration files

---

### **Example Output**
```
Jenkins console will show:
- Vulnerable dependencies
- Security misconfigurations
- Severity levels (CRITICAL, HIGH, MEDIUM, LOW)
```

---

### **How to Use It**
1. Save as `trivyScan.groovy` in your shared library
2. In Jenkinsfile:
```groovy
@Library('security-libs') _
pipeline {
    agent any
    stages {
        stage('Security Scan') {
            steps {
                trivyScan()  // ← Runs the scan
            }
        }
    }
}
```

---

### **Recommended Improvements**

1. **Add fail conditions** (stop build on critical vulnerabilities):
```groovy
def call() {
    sh "trivy fs --exit-code 1 --severity CRITICAL ."
}
```

2. **Generate reports**:
```groovy
def call() {
    sh """
    trivy fs . > trivy-report.txt
    """
    archiveArtifacts 'trivy-report.txt'
}
```

3. **Scan container images** (alternative version):
```groovy
def call(String imageName) {
    sh "trivy image ${imageName}"
}
```

---

### **Why This Matters**
- Catches security issues early
- Scans for:
  - Known vulnerabilities in dependencies
  - Misconfigurations
  - Secrets accidentally committed
- Lightweight (just one command)
 😊*/