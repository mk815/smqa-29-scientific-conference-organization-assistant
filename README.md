# Project title
 smqa-29-scientific-conference-organization-assistant
# Installation
  To run this project, you need to have Java Development Kit (JDK) version 14.0.2 installed on your machine. You can download and install Java JDK from the official Oracle website.
  eclipseIDE 2023-12 has been used
  
  - The below libraries are often added to Java projects as dependencies to extend functionalities or support specific features.
  1. javax.mail.jar: This JAR file contains the JavaMail API, which provides the ability to send, receive, and manipulate email messages using Java.
  2. mockito-all-1.10.19.jar: Mockito is a popular mocking framework used in unit tests for Java. This JAR file contains the Mockito library, which helps in creating mock objects in testing.
  3. activation-1.1.jar: The activation.jar file contains the JavaBeans Activation Framework (JAF), which provides a set of standard interfaces and classes for integrating and managing MIME data types.
  4. randoop-all-4.3.2.jar: Randoop is an automatic test generation tool for Java. This JAR file contains the Randoop library, which generates unit tests for Java code based on runtime behavior analysis.
     Create environment variable RANDOOP_JAR and the path should be where the randoop-all-4.3.2.jar has been located.
     
  - These files has been provided in this repository.
  - When using these JAR files in the project, ensure that they are included in your project's classpath or build path so that your code can access the classes and methods provided by these libraries.
  - Go to project -> properties -> Java Build Path -> Libraries -> Classpath -> Add External JARs -> Apply
