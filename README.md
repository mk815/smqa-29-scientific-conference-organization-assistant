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
# Execution
  1. SG2-2: Create user Organizer
     - you have to input organizer username and password
     - organizer's username and password will be stored in organizer_credentials.txt file in the format of username:password
  2. SG2-4: Create user Speaker
     - you have to input speaker username and password
     - speaker's username and password will be stored in speakers.txt file in the format of username:password
  3. SG2-5: Create user Attendee
     - you have to input attendee username and password
     - attendee's username and password will be stored in the attendees.txt file in the format of username:password
  4. SG2-13: Send Invitation to Sponsor
     -  you have to input sponser email-id and message
     -  sponser will get email from 'manikantakallakuri143@gmail.com'
  5. SG2-6: Able to track Speakers
     - list the all number of speaker's username along with count will be displayed
  6. SG2-3: Able to track Attendees
     - list the all number of attendee's username along with count will be displayed
  7. SG2-8: Able to delete Speaker
     - you have to input speaker username that you want to delete
  8. SG2-7: Able to delete Attendee
     - you have to input attendee username that you want to delete
  9. SG2-14: Show Attendees and Speakers
     - list the all number of speaker's username and attendee's username along with count will be displayed
  10. SG2-22: Graphical representation of Attendees
     - a pie chart contains attendees count will be display in a new window
  11. SG2-17: Graphical representation of speakers & Attendees
     - a bar chart contains at attendees count ans speaker count will be displayed in a new window
  12. SG2-16: Send updates to Attendee
     -  you have to input attendee email-id and update message
     -  attendee will get email from 'manikantakallakuri143@gmail.com'
