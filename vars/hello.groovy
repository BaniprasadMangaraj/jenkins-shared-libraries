def call(){
  echo "Hello Dosto, I am Baniprasad"
}



/*
### **Line-by-Line Breakdown**
1. **`def call()`**  
   - Defines a Jenkins function named `call()` (the default function that runs)

2. **`echo "Hello Dosto, I am Baniprasad"`**  
   - Prints the message to Jenkins console output

---

### **How to Use It**
1. Save this in your shared library as `baniprasad.groovy`
2. In any Jenkinsfile, call it like this:
   ```groovy
   @Library('your-shared-library') _
   pipeline {
       agent any
       stages {
           stage('Greet') {
               steps {
                   baniprasad()  // ← This will print your message
               }
           }
       }
   }
   ```

---

### **Expected Output**
```
[Pipeline] echo
Hello Dosto, I am Baniprasad
```

---

### **Why This Exists**
- Simple demonstration of Jenkins shared libraries
- Can be extended to do real work (this is the "hello world" equivalent)
- Shows how to personalize automation scripts


*/