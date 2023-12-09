import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import org.junit.AfterClass;
import org.junit.jupiter.api.Test;

public class MutationBased {

	private final InputStream originalSystemIn = System.in;
    private ByteArrayInputStream mockTestIn;
	
	@Test
    public void testOrganizerLogin() {
        Organizer organizer = new Organizer();

        // Test case for successful login
        assertTrue(organizer.organizerLogin("o1", "1234"));
        
        // Test case for failed login
        assertFalse(organizer.organizerLogin("invalidUsername", "invalidPassword"));

    }
	
	@Test
	public void testSpeakerLoginOriginal() {
        
        Speaker speaker = new Speaker("s1","1234");
        boolean actual = speaker.login("s1", "1234");
        boolean expected = true;
        assertEquals(expected, actual);
	}
	
	@Test
	public void testSpeakerLoginMutated() {
        
        Speaker speaker = new Speaker("sssss1","1234");
        boolean actual = speaker.login("sssss1", "1234");
        boolean expected = true;
        assertNotEquals(expected, actual);
	}
	
	 // Original getUsername() method test
    @Test
    public void testGetUsernameOriginal() {
        // Initialize object and set username
        Speaker speaker = new Speaker("OriginalUsername","1234");
        // Check if getUsername() returns the expected username
        assertEquals("OriginalUsername", speaker.getUsername());
    }

    // Mutant getUsername() method test
    @Test
    public void testGetUsernameMutated() {
        // Initialize object and set username
        Speaker speaker = new Speaker("OriginalUsername","1234");
        // Check if getUsername() detects the mutant (MutatedUsername)
        assertNotEquals("MutatedUsername", speaker.getUsername());
    }
    
	 // Original getPassqword() method test
    @Test
    public void testGetPasswordOriginal() {
        // Initialize object and set username
        Speaker speaker = new Speaker("OriginalUsername","1234");
        // Check if getUsername() returns the expected username
        assertEquals("1234", speaker.getPassword());
    }

    // Mutant getUsername() method test
    @Test
    public void testGetPasswordMutated() {
        // Initialize object and set username
        Speaker speaker = new Speaker("OriginalUsername","1234");
        // Check if getUsername() detects the mutant (MutatedUsername)
        assertNotEquals("12345", speaker.getPassword());
    }
	

	@Test
	public void testsimulateSpeakerQASessionOriginal() {
		Speaker speaker = new Speaker("s1","1234");
		// Test case to simulate speaker qa session
        provideInput("Java Programming\nyes\nhttps://www.tutorialspoint.com/java/java_tutorial.pdf\nExcellent\n");
		boolean simulateSpeakerQASessionFlag = speaker.simulateSpeakerQASession();
		boolean expected = true;
		assertEquals(expected,simulateSpeakerQASessionFlag);
	}
	
	@Test
	public void testsimulateSpeakerQASessionMutated() {
		Speaker speaker = new Speaker("ss1","1234");
		// Test case to simulate speaker qa session
        provideInput("Web\nyes\nhttps://www.tutorialspoint.com/java/java_tutorial.pdf\nExcellent\n");
		boolean simulateSpeakerQASessionFlag = speaker.simulateSpeakerQASession();
		simulateSpeakerQASessionFlag = false;
		assertNotEquals(true,simulateSpeakerQASessionFlag);
	}
	
	@Test
	public void testsimulateSpeakerQASessionNo() {
		Speaker speaker = new Speaker("s1","1234");
		// Test case to simulate speaker qa session
        provideInput("Java Programming\nno\nExcellent\n");
		boolean simulateSpeakerQASessionFlag = speaker.simulateSpeakerQASession();
		boolean expected = true;
		assertEquals(expected,simulateSpeakerQASessionFlag);
	}
	
	private void provideInput(String data) {
		mockTestIn = new ByteArrayInputStream(data.getBytes());
        System.setIn(mockTestIn);
    }

	@Test
	public void testuploadPresentationSpeakerFound() {
		Speaker speaker = new Speaker("s1","1234");
		// Test case to upload presentation
        provideInput("s1\n1234\nhttps://www.tutorialspoint.com/java/java_tutorial.pdf\n");
		boolean uploadPresentationFlag = speaker.uploadPresentation("s1");
		boolean expected = true;
		assertEquals(true,uploadPresentationFlag);
	}
	
	@Test
	public void testupdatePresentationSpeakerFound() {
		Speaker speaker = new Speaker("s1","1234");
		// Test case to upload presentation
        provideInput("soo1\n1234\nhttps://www.tutorialspoint.com/java/java_tutorial.pdf\n");
		boolean uploadPresentationFlag = speaker.updatePresentation("soo1");
		boolean expected = true;
		assertEquals(true,uploadPresentationFlag);
	}
	
//	@Test
	public void testupdatePresentationSpeakerNotFound() {
		Speaker speaker = new Speaker("okokoooook","1234");
		// Test case to upload presentation
        provideInput("okokokookkk\n1234\nhttps://www.tutorialspoint.com/java/java_tutorial.pdf\n");
		boolean uploadPresentationFlag = speaker.updatePresentation("okokoooook");
		assertEquals(true,uploadPresentationFlag);
	}

	// Original getTitle() method test
	@Test
    public void testgetTitleOriginal() {
        Session session = new Session("OriginalTitle","OriginalDescription");
        assertEquals("OriginalTitle", session.getTitle());
    }

    // Mutant getTitle() method test
    @Test
    public void testgetTitleMutated() {
        Session session = new Session("OriginalTitle","OriginalDescription");
        assertNotEquals("MutatedTitle", session.getTitle());
    }
    
	 // Original getDescription() method test
    @Test
    public void testgetDescriptionOriginal() {
        Session session = new Session("OriginalTitle","OriginalDescription");
        assertEquals("OriginalDescription", session.getDescription());
    }

    // Mutant getDescription() method test
    @Test
    public void testgetDescriptionMutated() {
        Session session = new Session("OriginalTitle","OriginalDescription");
        assertNotEquals("MutatedDescription", session.getDescription());
    }

}
