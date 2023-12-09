import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayInputStream;

import org.junit.jupiter.api.Test;

public class SpecificationBasedTesting {
    private ByteArrayInputStream mockTestIn;

    
	private void provideInput(String data) {
		mockTestIn = new ByteArrayInputStream(data.getBytes());
        System.setIn(mockTestIn);
    }
	
    // Test for login functionality
    @Test
    public void testValidLogin() {
        // Implement test for valid login credentials
        assertTrue(Speaker.login("s1", "1234"));
    }

    @Test
    public void testInvalidLogin() {
        // Implement test for invalid login credentials
        assertFalse(Speaker.login("ins1", "in1234"));
    }

    // Test for simulating Q&A session
    @Test
    public void testSimulateQASession() {
        // Implement test for simulating Q&A session
    	provideInput("Java Programming\nyes\nhttps://tutorialspoint.com/java/java_tutorial.pdf\ngood\n");
        assertTrue(Speaker.simulateSpeakerQASession());
    }

    // Test for uploading presentation
    @Test
    public void testValidPresentationUpload() {
        // Implement test for valid presentation upload
        Speaker speaker = new Speaker("s1", "1234");
    	provideInput("s1\n1234\nhttps://tutorialspoint.com/java/java_tutorial.pdf\nJava Programming\nyes\nhttps://tutorialspoint.com/java/java_tutorial.pdf\ngood\n");
        assertTrue(speaker.uploadPresentation("s1"));
    }

    @Test
    public void testInvalidPresentationURL() {
        // Implement test for invalid presentation URL
        Speaker speaker = new Speaker("s1", "1234");
    	provideInput("s1\n1234\nhttps://tutorialspoint.com/java/java_tutal\nJava Programming\nyes\nhttps://tutorialspoint.com/java/java_orial\ngood\n");
        assertTrue(speaker.submitPresentationMaterial("invalidURL"));
    }

    @Test
    public void testInvalidCredentialsForUpload() {
    	// Implement test for invalid credentials during upload
        Speaker speaker = new Speaker("ins1", "in1234");
       	provideInput("invalids1\n1234\nhttps://tutorialspoint.com/java/java_tutorial.pdf\nJava Programming\nyes\nhttps://tutorialspoint.com/java/java_tutorial.pdf\ngood\n");
        assertTrue(speaker.uploadPresentation("s1"));
    }

    // Test for updating presentation
    @Test
    public void testValidPresentationUpdate() {
        // Implement test for valid presentation update
        Speaker speaker = new Speaker("s1", "1234");
    	provideInput("s1\n1234\nhttps://tutorialspoint.com/java/java_tutorial.pdf\nJava Programming\nyes\nhttps://tutorialspoint.com/java/java_tutorial.pdf\ngood\n");
        assertTrue(speaker.updatePresentation("s1"));
    }

    @Test
    public void testInvalidPresentationURLForUpdate() {
        // Implement test for invalid presentation URL for update
        Speaker speaker = new Speaker("s1", "1234");
       	provideInput("invalids1\n1234\nhttps://tutorialspoint.com/java/java_tutorial.pdf\nJava Programming\nyes\nhttps://tutorialspoint.com/java/java_tutorial.pdf\ngood\n");
        assertTrue(speaker.updatePresentationMaterial("invalidURL"));
    }
}
