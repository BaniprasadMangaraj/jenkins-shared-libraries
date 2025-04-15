# Jenkins Shared Library

A collection of reusable Groovy scripts for Jenkins pipelines, including Docker operations, security scanning, and Kubernetes manifest management.

## 📦 Available Scripts

### 1. Docker Build (`dockerBuild.groovy`)
```groovy
def call(Map config = [:]) {
    // Builds Docker images with configurable options
}
```

### 2. Docker Push (`dockerPush.groovy`)
```groovy
def call(Map config = [:]) {
    // Pushes Docker images with secure credential handling
}
```

### 3. Trivy Security Scan (`trivyScan.groovy`)
```groovy
def call() {
    // Runs vulnerability scanning on project files
}
```

### 4. Kubernetes Manifest Updater (`updateK8sManifests.groovy`)
```groovy
def call(Map config = [:]) {
    // Updates image tags in Kubernetes manifests
}
```

### 5. Unit Test Runner (`runUnitTests.groovy`)
```groovy
def call() {
    // Template for running unit tests
}
```

### 6. Workspace Cleaner (`cleanWorkspace.groovy`)
```groovy
def call() {
    // Cleans Jenkins workspace
}
```

## 🛠️ Installation

1. Create a `vars` directory in your Jenkins shared library
2. Add these Groovy files
3. Configure in Jenkins:  
   **Manage Jenkins** → **Configure System** → **Global Pipeline Libraries**

## 🚀 Usage Example

```groovy
@Library('your-shared-lib') _

pipeline {
    agent any
    stages {
        stage('Build') {
            steps {
                dockerBuild(
                    imageName: "myapp",
                    imageTag: "${env.BUILD_NUMBER}"
                )
            }
        }
        stage('Test') {
            steps {
                runUnitTests()
                trivyScan()
            }
        }
        stage('Deploy') {
            steps {
                dockerPush(
                    imageName: "myapp",
                    imageTag: "${env.BUILD_NUMBER}"
                )
                updateK8sManifests(
                    imageTag: "${env.BUILD_NUMBER}"
                )
            }
        }
    }
}
```

## 🔧 Configuration Guide

| Script | Required Parameters | Optional Parameters |
|--------|---------------------|---------------------|
| `dockerBuild` | `imageName` | `imageTag`, `dockerfile`, `context` |
| `dockerPush` | `imageName` | `imageTag`, `credentials` |
| `updateK8sManifests` | `imageTag` | `manifestsPath`, `gitCredentials` |

## 🔒 Security Features

- Credential masking with `withCredentials`
- Safe parameter defaults
- Minimal permission scoping

## 🌟 Best Practices

1. Always pin image tags (avoid `latest`)
2. Use `[ci skip]` in automated commits
3. Regularly update Trivy DB (`trivy --download-db-only`)

## 🆘 Support

For issues, please:
1. Check Jenkins console logs
2. Verify parameter requirements
3. Ensure correct file permissions

## 🤝 Contributing

1. Fork the repository
2. Create feature branch (`git checkout -b feature/fooBar`)
3. Commit changes (`git commit -am 'Add some fooBar'`)
4. Push to branch (`git push origin feature/fooBar`)
5. Create new Pull Request
```

---

### Key Features of This README:
1. **Script Catalog** - Clear list of all available Groovy scripts
2. **Visual Icons** - Easy scanning with emojis
3. **Copy-Paste Examples** - Ready-to-use pipeline snippet
4. **Parameter Tables** - Quick reference for configuration
5. **Security Highlight** - Builds trust in automation
6. **Contributing Guide** - Standard GitHub workflow

Would you like me to add any specific:
- Additional usage examples?
- Screenshots of expected outputs?
- Detailed troubleshooting scenarios? 😊
