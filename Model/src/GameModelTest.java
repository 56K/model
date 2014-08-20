import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;

public class GameModelTest {

	private GameModel gModel;
	private User mockedUser;

	@Before
	// Diese Methode wird vor jedem Test ausgef�hrt. Gleiche Bedingungen f�r alle Tests
	public void setUp() {
		mockedUser = mock(User.class); // Erstellt einen virtuellen User
		gModel = new GameModel(mockedUser); // Ertellt das GameModel mit dem virtuellen User
	}

	@Test
	/**
	 * Hier wird die Methode getgameMode der Klasse GameModel getestet
	 */
	public void checkGameMode() {
		// Beim ersten Test wird 0 erwartet
		when(mockedUser.getGameMode()).thenReturn(0);
		assertEquals(0, gModel.getGameMode());

		// Hier wird der 2. und letzte erlaubte Wert 1 erwartet
		when(mockedUser.getGameMode()).thenReturn(1);
		assertEquals(1, gModel.getGameMode());

		// Bei ung�ltiger R�ckgabe vom User wird der Standardmodus 0 gew�hlt
		when(mockedUser.getGameMode()).thenReturn(3);
		assertEquals(0, gModel.getGameMode());

		when(mockedUser.getGameMode()).thenReturn(-3);
		assertEquals(0, gModel.getGameMode());
	}
	
	@Test
	/**
	 * Hier wird die Methode getUsername der Klasse GameModel getestet;
	 */
	public void checkUsername(){
		when(mockedUser.getUsername()).thenReturn("Herbert");
		assertEquals("Herbert", gModel.getUsername());
		
		when(mockedUser.getUsername()).thenReturn(null);
		assertEquals("Benutzername", gModel.getUsername());
	}
}
