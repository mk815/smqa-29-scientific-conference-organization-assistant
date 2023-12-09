# Project title
 smqa-29-scientific-conference-organization-assistant
# Installation & Set up
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
# User stories development execution
  1. SG2-2: Create user Organizer
     - you have to select the option -> 2. Signup as Organizer
     - you have to input organizer username and password
     - organizer's username and password will be stored in 'organizer_credentials.txt' file in the format of username:password
  2. SG2-4: Create user Speaker
     - you have to select the options -> 1. Login as Organizer -> 2. Add Speaker
     - you have to input speaker username and password
     - speaker's username and password will be stored in 'speakers.txt' file in the format of username:password
  3. SG2-5: Create user Attendee
     - you have to select the options -> 1. Login as Organizer -> 1. Add Attendee
     - you have to input attendee username and password
     - attendee's username and password will be stored in the 'attendees.txt' file in the format of username:password
  4. SG2-13: Send Invitation to Sponsor
     - you have to select the options -> 1. Login as Organizer -> 8. Send invitation to sponser
     - you have to input sponser email-id and message
     - sponser will get email from 'manikantakallakuri143@gmail.com'
  5. SG2-6: Able to track Speakers
      - you have to select the options -> 1. Login as Organizer -> 3. Track Speakers
      - list the all number of speaker's username along with count will be displayed
  6. SG2-3: Able to track Attendees
      - you have to select the options -> 1. Login as Organizer -> 4. Track Attendees
      - list the all number of attendee's username along with count will be displayed
  7. SG2-8: Able to delete Speaker
      you have to select the options -> 1. Login as Organizer -> 6. Delete Speaker
     - you have to input speaker username that you want to delete
  8. SG2-7: Able to delete Attendee
      - you have to select the options -> 1. Login as Organizer -> 5. Delete Attendee
     - you have to input attendee username that you want to delete
  9. SG2-14: Show Attendees and Speakers
      - you have to select the options -> 1. Login as Organizer -> 7. Show all Speakers, Attendees
      - list the all number of speaker's username and attendee's username along with count will be displayed
  10. SG2-22: Graphical representation of Attendees
      - - you have to select the options -> 4. Login as Speaker -> 4. Show visualization of no.of attendees
      - a pie chart contains attendees count will be display in a new window
  11. SG2-17: Graphical representation of speakers & Attendees
      - you have to select the options -> 1. Login as Organizer -> 11. Show Attendees & Speakers count visualization
      - a bar chart contains at attendees count ans speaker count will be displayed in a new window
  12. SG2-16: Send updates to Attendee
      - you have to select the options -> 1. Login as Organizer -> 10. Send Update to Attendee
      - you have to input attendee email-id and update message
      - attendee will get email from 'manikantakallakuri143@gmail.com'
  13. SG2-18: Upload presentation materials
      - you have to select the option -> 4. Login as Speaker -> 2. Upload presentation
      - you have to enter speaker username and password for security purpose
      - then you have to enter a valid pdf url ends with .pdf for example (https://www.tutorialspoint.com/java/java_tutorial.pdf)
      - speaker username and pdf url will be stored in 'presentations.txt' file in the format of username:pdfurl
  14. SG2-19: Update presentation materials
      - you have to select the option -> 4. Login as Speaker -> 3. Update presentation
      - then you have to enter a valid pdf url ends with .pdf for example (https://www.tutorialspoint.com/java/java_tutorial.pdf)
      - pdf url will be updated to corresponding speaker username in 'presentations.txt' file in the format of username:pdfurl
  15. SG2-9: Send updates to Speakers
      - you have to select the options -> 1. Login as Organizer -> 9. Send Update to Speaker
      - you have to input speaker email-id and update message
      - speaker will get email from 'manikantakallakuri143@gmail.com'
  16. SG2-15: Authorized presentation
      - you have to select the option -> 4. Login as Speaker -> 2. Upload presentation
      - you have to enter speaker username and password for security purpose
      - then you have to enter a valid pdf url ends with .pdf for example (https://www.tutorialspoint.com/java/java_tutorial.pdf)
      - speaker username and pdf url will be stored in 'presentations.txt' file in the format of username:pdfurl
  17. SG2-20: Send session links
      - you have to select the option -> 1. Login as Organizer -> 12. Send virtual session links to attendees
      - you have to input joining session link for example(https://meet.google.com/azd-xyhs-ome) and attendee emails in a comma-separated(for example: abc@gmail.com,xyz@gmail.com)
      - then the attendee's will get session links email from 'manikantakallakuri143@gmail.com'
  18. SG2-10: Speaker Q&A session
      - you have to select the option -> 4. Login as Speaker -> 1. simulate q&a session
      - you have to input presentation title, next if you have presentation materials to submit then type yes -> then you have to enter a valid pdf url ends with .pdf for example 
        (https://www.tutorialspoint.com/java/java_tutorial.pdf) the presentation pdf will submitted within the same path with the name presentationTitle you've entered, next you have input feedback.
  19. SG2-11: Attendee Q&A session
      - you have to select the option -> 3. Login as Attendee -> 1. simulate q&a session
      - you have to input interests or preferences for sessions (for example 'web') then sessions will be recommended, next you have to input feedback
  20. SG2-12:  Attendee download presentation
      - you have to select the option -> 3. Login as Attendee -> 2. Download presentation
      - you have to input speaker username, then corresponding pdf will be downloaded
  21. SG2-24: Conduct, View polls
      - you have to select the option -> 3. Login as Attendee -> 3. Participate in a poll for conducting polls
      - you have to participate in the poll
      - you have to select the option -> 1. Login as Organizer -> 16. View Polling results for viewing polls
      - poll_results.html will be generated within the same folder then you can copy that html file path and paste it in any browser then you can be able to view
  22. SG2-21: Able to generate reports
      - you have to select the option -> 1. Login as Organizer -> 14. Generate report contains all users
      - attendance_report.html file contails attendee's and speaker's information will be generated within the same folder then you can copy that html file path and paste it in any browser then you can be able   
        to view
  23. SG2-25: Implement payments
      - you have to select the option -> 1. Login as Organizer -> 13. Send payment link to attendees
      - you have to attendee username and attendee email-id and payment type (for example: vip) then payment details will be send to attendee email from 'manikantakallakuri143@gmail.com'
  24. SG2-23: Digital certificates for attendees
      - you have to select the option -> 1. Login as Organizer -> 15. Generate participation certificate
      - you have to input attendee full name
      - certificate.html will be generated within the same folder then you can copy that html file path and paste it in any browser then you can be able to view
     
# Compiling test cases
  1. Statement based testing
     - **Steps:**
       1. Preparation: Ensure that the organizer_credentials.txt file does not contain the o1111:1234 record. If present, remove it manually before running the test cases.
       2. Statement-Based Testing for Speaker.java: The test cases of Statement-Based Testing for Speaker.java class can be found in StatementAndBranchbasedMainTest.java.
       3. JUnit Test Execution:
          - Right-click on the StatementAndBranchbasedMainTest.java test class in the Package Explorer.
          - Select "Run As" or "Run Configurations," then choose "JUnit Test" to execute the test cases.
          - In the JUnit tab, observe the test results to ensure the expected behavior of the Speaker.java class.
       4. Code Coverage Analysis:
          - To check code coverage for the Speaker.java class:
          - Right-click on the StatementAndBranchbasedMainTest.java test class in the Package Explorer.
          - Select "Coverage As" and then choose "JUnit Test."
          -In the Coverage tab, examine the code coverage results specifically related to the Speaker.java file.
  2. Branch based testing
     - **Steps:**
       1. Preparation: Ensure that the organizer_credentials.txt file does not contain the o1111:1234 record. If present, remove it manually before running the test cases.
       2. Branch-Based Testing for Speaker.java: The test cases of Branch-Based Testing for Speaker.java class can be found in StatementAndBranchbasedMainTest.java.
       3. JUnit Test Execution:
          - Right-click on the StatementAndBranchbasedMainTest.java test class in the Package Explorer.
          - Select "Run As" or "Run Configurations," then choose "JUnit Test" to execute the test cases.
          - In the JUnit tab, observe the test results to ensure the expected behavior of the Speaker.java class.
       4. Code Coverage Analysis:
          - To check code coverage for the Speaker.java class:
          - Right-click on the StatementAndBranchbasedMainTest.java test class in the Package Explorer.
          - Select "Coverage As" and then choose "JUnit Test."
          - In the Coverage tab, examine the code coverage results specifically related to the Speaker.java file.
  3. Mutation based testing
     - **Steps**
       1. Prerequisites:
          - Install Pitclipse Plugin:
          - Open Eclipse IDE.
          - Go to "Help" -> "Eclipse Marketplace".
          - Search for "Pitclipse" and install the plugin.
          - Restart Eclipse if prompted.
       2. Run Mutation Tests:
          - Right-click on the MutationBased.java test class in the Package Explorer.
          - Choose "Run As" -> "PIT Mutation Test" from the context menu.
          - Once the mutation testing process completes, Open the PIT Summary tab in Eclipse.
          - In the PIT Summary tab, Click on the default link. You'll see the mutation coverage results for the Session.java file.
  4. Specification based testing
     - **Steps**
       1. 
  5. Random based testing
     -

