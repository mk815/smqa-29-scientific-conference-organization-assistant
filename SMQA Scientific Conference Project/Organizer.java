import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Scanner;

import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.Address;
import javax.mail.Authenticator;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import javax.mail.internet.*;
import java.io.OutputStream;
import javax.mail.*;
import javax.net.*;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.Base64;

public class Organizer {
    private static final String ORGANIZER_FILE_PATH = "organizer_credentials.txt";
    private static final String ATTENDEES_FILE_PATH = "attendees.txt";
    private static final String SPEAKERS_FILE_PATH = "speakers.txt";
    private Map<String, String> organizerCredentials;
    private String loggedInOrganizer;
    private static Map<String, Double> registrationFees = new HashMap<>();
    static {
        registrationFees.put("Standard", 100.0);
        registrationFees.put("VIP", 200.0);
    }

    public Organizer() {
        organizerCredentials = loadOrganizerCredentials();
        loggedInOrganizer = null;
    }

    private Map<String, String> loadOrganizerCredentials() {
        try {
            FileReader reader = new FileReader(ORGANIZER_FILE_PATH);
            BufferedReader bufferedReader = new BufferedReader(reader);

            Map<String, String> credentials = new HashMap<>();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] parts = line.split(":");
                credentials.put(parts[0], parts[1]);
            }

            bufferedReader.close();

            return credentials;
        } catch (IOException e) {
            // File does not exist or an error occurred, return an empty map
            return new HashMap<>();
        }
    }

    public void saveOrganizerCredentials() {
        try {
            FileWriter writer = new FileWriter(ORGANIZER_FILE_PATH);
            BufferedWriter bufferedWriter = new BufferedWriter(writer);

            for (Map.Entry<String, String> entry : organizerCredentials.entrySet()) {
                bufferedWriter.write(entry.getKey() + ":" + entry.getValue() + "\n");
            }

            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private List<Attendee> loadAttendees() {
        try {
            FileReader reader = new FileReader(ATTENDEES_FILE_PATH);
            BufferedReader bufferedReader = new BufferedReader(reader);

            List<Attendee> attendees = new ArrayList<>();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] parts = line.split(":");
                attendees.add(new Attendee(parts[0], parts[1]));
            }

            bufferedReader.close();

            return attendees;
        } catch (IOException e) {
            return new ArrayList<>();
        }
    }

    private void saveAttendees(List<Attendee> attendees) {
        try {
            FileWriter writer = new FileWriter(ATTENDEES_FILE_PATH);
            BufferedWriter bufferedWriter = new BufferedWriter(writer);

            for (Attendee attendee : attendees) {
                bufferedWriter.write(attendee.getUsername() + ":" + attendee.getPassword() + "\n");
            }

            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private List<Speaker> loadSpeakers() {
        try {
            FileReader reader = new FileReader(SPEAKERS_FILE_PATH);
            BufferedReader bufferedReader = new BufferedReader(reader);

            List<Speaker> speakers = new ArrayList<>();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] parts = line.split(":");
                speakers.add(new Speaker(parts[0], parts[1]));
            }

            bufferedReader.close();

            return speakers;
        } catch (IOException e) {
            return new ArrayList<>();
        }
    }

    public void saveSpeakers(List<Speaker> speakers) {
        try {
            FileWriter writer = new FileWriter(SPEAKERS_FILE_PATH);
            BufferedWriter bufferedWriter = new BufferedWriter(writer);

            for (Speaker speaker : speakers) {
                bufferedWriter.write(speaker.getUsername() + ":" + speaker.getPassword() + "\n");
            }

            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean organizerSignup(String username, String password) {
        if (!organizerCredentials.containsKey(username)) {
            organizerCredentials.put(username, password);
            saveOrganizerCredentials();
            System.out.println("Organizer signup successful!");
        } else {
            System.out.println("Username already exists. Please choose a different username.");
        }
        return true;
    }

    public boolean organizerLogin(String username, String password) {
        // Check if the provided username and password match the stored credentials
        if (organizerCredentials.containsKey(username) && organizerCredentials.get(username).equals(password)) {
            loggedInOrganizer = username;
            System.out.println("Organizer login successful!");
            return true;
        } else {
            System.out.println("Organizer login failed. Incorrect username or password.");
            return false;
        }
    }
    
    public boolean organizerLogout() {
        loggedInOrganizer = null;
        System.out.println("Organizer logout successful.");
        return true;
    }

    public boolean isLoggedIn() {
        return loggedInOrganizer != null;
    }

    public boolean addAttendee(String username, String password) {
        // Check if the organizer is logged in before allowing attendee addition
        if (isLoggedIn()) {
            List<Attendee> attendees = loadAttendees();

            // Check if the attendee already exists
            boolean attendeeExists = attendees.stream().anyMatch(a -> a.getUsername().equalsIgnoreCase(username));

            if (!attendeeExists) {
                // Create a new Attendee and add it to the list
                Attendee newAttendee = new Attendee(username, password);
                attendees.add(newAttendee);

                // Save the updated attendee list to the file
                saveAttendees(attendees);
                System.out.println("Adding attendee: " + username);
            } else {
                System.out.println("Attendee with username '" + username + "' already exists.");
            }
        } else {
            System.out.println("Organizer not logged in. Please log in before adding attendees.");
        }
        return false;
    }

    public boolean addSpeaker(String username, String password) {
        // Check if the organizer is logged in before allowing speaker addition
        if (isLoggedIn()) {
            List<Speaker> speakers = loadSpeakers();

            // Check if the speaker already exists
            boolean speakerExists = speakers.stream().anyMatch(s -> s.getUsername().equalsIgnoreCase(username));

            if (!speakerExists) {
                // Create a new Speaker and add it to the list
                Speaker newSpeaker = new Speaker(username, password);
                speakers.add(newSpeaker);

                // Save the updated speaker list to the file
                saveSpeakers(speakers);
                System.out.println("Adding speaker: " + username);
            } else {
                System.out.println("Speaker with username '" + username + "' already exists.");
            }
        } else {
            System.out.println("Organizer not logged in. Please log in before adding speakers.");
        }
        return true;
    }

    public boolean listSpeakers() {
        try {
            FileReader reader = new FileReader(SPEAKERS_FILE_PATH);
            BufferedReader bufferedReader = new BufferedReader(reader);

            int count = 0;

            System.out.println("List of Speakers:");

            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] parts = line.split(":");
                String username = parts[0];
                System.out.println(username);
                count++;
            }

            System.out.println("Total Speakers: " + count);

            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        return true;

    }

    public boolean listAttendees() {
        try {
            FileReader reader = new FileReader(ATTENDEES_FILE_PATH);
            BufferedReader bufferedReader = new BufferedReader(reader);

            int count = 0;

            System.out.println("List of Attendees:");

            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] parts = line.split(":");
                String username = parts[0];
                System.out.println(username);
                count++;
            }

            System.out.println("Total Attendees: " + count);

            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }

    public boolean deleteAttendee(String deleteAttendeeUsername) {
        try {
            FileReader reader = new FileReader(ATTENDEES_FILE_PATH);
            BufferedReader bufferedReader = new BufferedReader(reader);

            StringBuilder fileContent = new StringBuilder();
            String line;

            while ((line = bufferedReader.readLine()) != null) {
                String[] parts = line.split(":");
                String username = parts[0];

                if (!username.equals(deleteAttendeeUsername)) {
                    fileContent.append(line).append("\n");
                }
            }

            bufferedReader.close();

            // Write the updated content back to the file
            FileWriter writer = new FileWriter(ATTENDEES_FILE_PATH);
            BufferedWriter bufferedWriter = new BufferedWriter(writer);
            bufferedWriter.write(fileContent.toString());
            bufferedWriter.close();

            System.out.println("Attendee '" + deleteAttendeeUsername + "' deleted successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }

    public boolean deleteSpeaker(String deleteSpeakerUsername) {
        try {
            FileReader reader = new FileReader(SPEAKERS_FILE_PATH);
            BufferedReader bufferedReader = new BufferedReader(reader);

            StringBuilder fileContent = new StringBuilder();
            String line;

            while ((line = bufferedReader.readLine()) != null) {
                String[] parts = line.split(":");
                String username = parts[0];

                if (!username.equals(deleteSpeakerUsername)) {
                    fileContent.append(line).append("\n");
                }
            }

            bufferedReader.close();

            // Write the updated content back to the file
            FileWriter writer = new FileWriter(SPEAKERS_FILE_PATH);
            BufferedWriter bufferedWriter = new BufferedWriter(writer);
            bufferedWriter.write(fileContent.toString());
            bufferedWriter.close();

            System.out.println("Speaker '" + deleteSpeakerUsername + "' deleted successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }

    public boolean sendInvitationToSponser(String sponserEmail) {
     // send email logic
    	Properties props = new Properties();
    	//Configuring properties for gmail
    	//If you are not using gmail you may need to change the values
    	 props.put("mail.smtp.host", "smtp.gmail.com");
    	 props.put("mail.smtp.port", "587");
    	 props.put("mail.smtp.auth", "true");
    	 props.put("mail.smtp.starttls.enable", "true");
    	 props.put("mail.smtp.ssl.trust", "smtp.gmail.com");     
    	//Creating a new session
    	Session session = Session.getDefaultInstance(props,
    	        new javax.mail.Authenticator() {
    	            //Authenticating the password
    	            protected PasswordAuthentication getPasswordAuthentication() {
    	                return new PasswordAuthentication("manikantakallakuri143@gmail.com", "txfl bqiy eqcj jbby");
    	            }
    	        });
    	try {
    	    //Creating MimeMessage object
    	    MimeMessage mm = new MimeMessage(session);
    	    //Setting sender address
    	    mm.setFrom(new InternetAddress("manikantakallakuri143@gmail.com"));
    	    //Adding receiver
    	    mm.addRecipient(Message.RecipientType.TO, new InternetAddress(sponserEmail));
    	    //Adding subject
    	    mm.setSubject("Event Invitation");
    	    //Adding message
    	    mm.setText("You have been invited to the event");           
    	    //Sending email
    	    Transport.send(mm);
            System.out.println("Invitation to sponsor has been sent successfully");

    	} catch (MessagingException e) {
    	    e.printStackTrace();
    	}
        return true;
    }

    public boolean sendUpdatetoSpeaker(String speakerEmail, String message) {
     // send email logic
    	Properties props = new Properties();
    	//Configuring properties for gmail
    	//If you are not using gmail you may need to change the values
    	 props.put("mail.smtp.host", "smtp.gmail.com");
    	 props.put("mail.smtp.port", "587");
    	 props.put("mail.smtp.auth", "true");
    	 props.put("mail.smtp.starttls.enable", "true");
    	 props.put("mail.smtp.ssl.trust", "smtp.gmail.com");     
    	//Creating a new session
    	Session session = Session.getDefaultInstance(props,
    	        new javax.mail.Authenticator() {
    	            //Authenticating the password
    	            protected PasswordAuthentication getPasswordAuthentication() {
    	                return new PasswordAuthentication("manikantakallakuri143@gmail.com", "txfl bqiy eqcj jbby");
    	            }
    	        });
    	try {
    	    //Creating MimeMessage object
    	    MimeMessage mm = new MimeMessage(session);
    	    //Setting sender address
    	    mm.setFrom(new InternetAddress("manikantakallakuri143@gmail.com"));
    	    //Adding receiver
    	    mm.addRecipient(Message.RecipientType.TO, new InternetAddress(speakerEmail));
    	    //Adding subject
    	    mm.setSubject("Update from organizer");
    	    //Adding message
    	    mm.setText(message);           
    	    //Sending email
    	    Transport.send(mm);
            System.out.println("Update to attendee has been sent successfully");

    	} catch (MessagingException e) {
    	    e.printStackTrace();
    	}
        return true;
    }

    public boolean sendUpdatetoAttendee(String attendeeMail, String message) {
            // send email logic
        	Properties props = new Properties();
        	//Configuring properties for gmail
        	//If you are not using gmail you may need to change the values
        	 props.put("mail.smtp.host", "smtp.gmail.com");
        	 props.put("mail.smtp.port", "587");
        	 props.put("mail.smtp.auth", "true");
        	 props.put("mail.smtp.starttls.enable", "true");
        	 props.put("mail.smtp.ssl.trust", "smtp.gmail.com");     
        	//Creating a new session
        	Session session = Session.getDefaultInstance(props,
        	        new javax.mail.Authenticator() {
        	            //Authenticating the password
        	            protected PasswordAuthentication getPasswordAuthentication() {
        	                return new PasswordAuthentication("manikantakallakuri143@gmail.com", "txfl bqiy eqcj jbby");
        	            }
        	        });
        	try {
        	    //Creating MimeMessage object
        	    MimeMessage mm = new MimeMessage(session);
        	    //Setting sender address
        	    mm.setFrom(new InternetAddress("manikantakallakuri143@gmail.com"));
        	    //Adding receiver
        	    mm.addRecipient(Message.RecipientType.TO, new InternetAddress(attendeeMail));
        	    //Adding subject
        	    mm.setSubject("Update from Organizer");
        	    //Adding message
        	    mm.setText(message);           
        	    //Sending email
        	    Transport.send(mm);
                System.out.println("Update to attendee has been sent successfully");

        	} catch (MessagingException e) {
        	    e.printStackTrace();
        	}
        return true;
    }

    public static boolean sendVirtualSessionLinks() {
        Scanner scanner = new Scanner(System.in);

        // Get the virtual session link
        System.out.println("Enter the joining session link: ");
        String sessionLink = scanner.nextLine();

        // Get multiple attendee emails
        System.out.println("Enter attendee emails (comma-separated): ");
        String emailsInput = scanner.nextLine();
        List<String> attendeeEmails = parseEmails(emailsInput);

        // Send virtual session links to multiple emails
        sendEmailsToAttendees(attendeeEmails, sessionLink);
        return true;
    }

    private static List<String> parseEmails(String emailsInput) {
        // Parse the comma-separated emails into a list
        String[] emailArray = emailsInput.split(",");
        List<String> attendeeEmails = new ArrayList<>();
        for (String email : emailArray) {
            attendeeEmails.add(email.trim());
        }
        return attendeeEmails;
    }

    private static void sendEmailsToAttendees(List<String> attendeeEmails, String sessionLink) {
        for (String email : attendeeEmails) {
            // Implement logic to send the virtual session link to each email
        	// send email logic
        	Properties props = new Properties();
        	//Configuring properties for gmail
        	//If you are not using gmail you may need to change the values
        	 props.put("mail.smtp.host", "smtp.gmail.com");
        	 props.put("mail.smtp.port", "587");
        	 props.put("mail.smtp.auth", "true");
        	 props.put("mail.smtp.starttls.enable", "true");
        	 props.put("mail.smtp.ssl.trust", "smtp.gmail.com");     
        	//Creating a new session
        	Session session = Session.getDefaultInstance(props,
        	        new javax.mail.Authenticator() {
        	            //Authenticating the password
        	            protected PasswordAuthentication getPasswordAuthentication() {
        	                return new PasswordAuthentication("manikantakallakuri143@gmail.com", "txfl bqiy eqcj jbby");
        	            }
        	        });
        	try {
        	    //Creating MimeMessage object
        	    MimeMessage mm = new MimeMessage(session);
        	    //Setting sender address
        	    mm.setFrom(new InternetAddress("manikantakallakuri143@gmail.com"));
        	    //Adding receiver
        	    mm.addRecipient(Message.RecipientType.TO, new InternetAddress(email));
        	    //Adding subject
        	    mm.setSubject("Virtual Session Link");
        	    //Adding message
        	    mm.setText("Join the session using the link: " + sessionLink);           
        	    //Sending email
        	    Transport.send(mm);
                System.out.println("Session link sent to " + email);

        	} catch (MessagingException e) {
        	    e.printStackTrace();
        	}
        }
    }

    public static boolean sendPaymenyLinks() {
        System.out.println("\nPayment System");
        System.out.print("Enter attendee name: ");
        Scanner scanner = new Scanner(System.in);
        String attendeeName = scanner.nextLine();
        System.out.print("Enter attendee email: ");
        String attendeeEmail = scanner.nextLine();
        System.out.println("Select payment type:");
        registrationFees.forEach((type, fee) -> System.out.println(type + ": $" + fee));
        System.out.print("Enter payment type: ");
        String registrationType = scanner.nextLine();
        registerAttendee(attendeeName, attendeeEmail, registrationType);
        return true;
    }

    public static void registerAttendee(String name, String email, String registrationType) {
        double fee = registrationFees.getOrDefault(registrationType, 0.0);
        String purpose = "Payment for " + registrationType;
        sendPaymentLink(email, fee, purpose);
    }

    public static void sendPaymentLink(String email, double amount, String purpose) {
        //Placeholder logic for generating and sending payment links
        Properties props = new Properties();
    	//Configuring properties for gmail
    	//If you are not using gmail you may need to change the values
    	 props.put("mail.smtp.host", "smtp.gmail.com");
    	 props.put("mail.smtp.port", "587");
    	 props.put("mail.smtp.auth", "true");
    	 props.put("mail.smtp.starttls.enable", "true");
    	 props.put("mail.smtp.ssl.trust", "smtp.gmail.com");     
    	//Creating a new session
    	Session session = Session.getDefaultInstance(props,
    	        new javax.mail.Authenticator() {
    	            //Authenticating the password
    	            protected PasswordAuthentication getPasswordAuthentication() {
    	                return new PasswordAuthentication("manikantakallakuri143@gmail.com", "txfl bqiy eqcj jbby");
    	            }
    	        });
    	try {
    	    //Creating MimeMessage object
    	    MimeMessage mm = new MimeMessage(session);
    	    //Setting sender address
    	    mm.setFrom(new InternetAddress("manikantakallakuri143@gmail.com"));
    	    //Adding receiver
    	    mm.addRecipient(Message.RecipientType.TO, new InternetAddress(email));
    	    //Adding subject
    	    String subject = "Conferrence event Payment";
            String body = "To: " + email +
                    "    Amount: " + amount + "\n" +
                    "  Purpose: Registration Fee \n" +
                    "    Click on the below link to make payment \n" +
                    "https://example-payment-gateway.com/pay?recipient=" + email +
                    "&amount=" + amount + "&purpose=" + purpose;
    	    mm.setSubject(subject);
    	    //Adding message
    	    mm.setText(body);           
    	    //Sending email
    	    Transport.send(mm);
            System.out.println(
                    "Payment link sent to " + email + " for amount $" + amount + " (Purpose: " + purpose + ")");
    	} catch (MessagingException e) {
    	    e.printStackTrace();
    	}
        
    }

    public boolean generateAttendanceReports() {
        try {
            // Read attendees and speakers from files
            List<String> attendees = readUsernamesFromFile("attendees.txt");
            List<String> speakers = readUsernamesFromFile("speakers.txt");

            // Create HTML document
            try (BufferedWriter writer = new BufferedWriter(new FileWriter("attendance_report.html"))) {
                writer.write("<html><body>");

                // Write attendees to HTML
                writer.write("<h2>Attendees:</h2>");
                writer.write("<ul>");
                for (String attendee : attendees) {
                    writer.write("<li>" + attendee + "</li>");
                }
                writer.write("</ul>");

                // Separate attendees and speakers in the report
                writer.write("<h2>Speakers:</h2>");
                writer.write("<ul>");
                for (String speaker : speakers) {
                    writer.write("<li>" + speaker + "</li>");
                }
                writer.write("</ul>");

                writer.write("</body></html>");
                System.out.println("Attendance report HTML generated successfully.");
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            System.err.println("Error reading user files: " + e.getMessage());
        }
        return true;
    }

    private List<String> readUsernamesFromFile(String filePath) throws IOException {
        List<String> usernames = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                usernames.add(line.split(":")[0]); // Assuming the format is "username:password"
            }
        }
        return usernames;
    }

    public static String generateCertificate(String attendeeName, String conferenceName) {
        // Load HTML template
        String template = loadHtmlTemplate(attendeeName,conferenceName);

        // Replace placeholders with actual values
        String certificate = template.replace("[AttendeeName]", attendeeName)
                .replace("[ConferenceName]", conferenceName);

        try (FileWriter fileWriter = new FileWriter("certificate.html")) {
            fileWriter.write(certificate);
            System.out.println("Certificate has been written to: " + "certificate.html");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return certificate;
    }

    public static String loadHtmlTemplate(String attendeeName,String conferenceName) {
        // In a real-world scenario, you might read the template from a file or a
        // database.
        // For simplicity, we'll use a hardcoded template here.
        
        String cssStyles = generateCSS(); // Generate CSS styles

        return "<!DOCTYPE html>\n" +
            "<html lang=\"en\">\n" +
            "<head>\n" +
            "    <meta charset=\"UTF-8\">\n" +
            "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
            "    <style>\n" +
            cssStyles + // Include generated CSS styles
            "    </style>\n" +
            "</head>\n" +
            "<body>\n" +
            "    <div class=\"certificate\">\n" +
            "        <div class=\"title\">Certificate of Participation</div>\n" +
            "        <div class=\"content\">\n" +
            "            This is to certify that <strong>" + attendeeName + "</strong><br>\n" +
            "            has actively participated in the <strong>" + conferenceName + "</strong>.\n" +
            "        </div>\n" +
            "        <div class=\"signature\">\n" +
            "            <p>Organizer's Signature</p>\n" +
            "        </div>\n" +
            "    </div>\n" +
            "</body>\n" +
            "</html>";
    }

    public static String generateCSS() {
        return "body {\n" +
            "    font-family: 'Arial', sans-serif;\n" +
            "    text-align: center;\n" +
            "}\n" +
            ".certificate {\n" +
            "    border: 2px solid #000;\n" +
            "    padding: 20px;\n" +
            "    max-width: 600px;\n" +
            "    margin: 20px auto;\n" +
            "}\n" +
            ".title {\n" +
            "    font-size: 24px;\n" +
            "    font-weight: bold;\n" +
            "    margin-bottom: 20px;\n" +
            "}\n" +
            ".content {\n" +
            "    font-size: 18px;\n" +
            "    margin-bottom: 20px;\n" +
            "}\n" +
            ".signature {\n" +
            "    margin-top: 30px;\n" +
            "}";
    }

    public boolean viewPollingResults() {
        try (BufferedReader reader = new BufferedReader(new FileReader("attendee_poll_responses.txt"));
                FileWriter htmlWriter = new FileWriter("poll_results.html")) {

            // Write HTML header
            htmlWriter.write("<html><head><title>Polling Results</title></head><body>");
            htmlWriter.write("<h1>Polling Results</h1>");

            String line;
            while ((line = reader.readLine()) != null) {
                // Split the line into username and poll response
                String[] data = line.split(":");
                if (data.length == 2) {
                    String username = data[0].trim();
                    int pollResponse = Integer.parseInt(data[1].trim());

                    // Write progress bar for each poll response
                    htmlWriter.write("<p><strong>Username:</strong> " + username + "</p>");
                    htmlWriter.write("<div style='background-color: #f1f1f1; width: 300px; margin-bottom: 10px;'>");
                    htmlWriter.write("<div style='background-color: #4CAF50; width: " + (pollResponse * 30)
                            + "px; height: 20px;'></div>");
                    htmlWriter.write("</div>");
                }
            }

            // Write HTML footer
            htmlWriter.write("</body></html>");

            System.out.println("Polling results HTML file with progress bars generated successfully.");

        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }

}
