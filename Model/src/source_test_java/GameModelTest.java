package source_test_java;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import source_main_java.GameChangeEvent;
import source_main_java.GameChangeEvent.EventType;
import source_main_java.GameListener;
import source_main_java.GameModel;
import source_main_java.User;

public class GameModelTest {

	private GameModel gModel;
	@Mock
	private User mockedUser;
	@Mock
	private GameListener mockedGameListener;

	@Before
	// Diese Methode wird vor jedem Test ausgeführt. Gleiche Bedingungen für
	// alle Tests
	public void setUp() {
		mockedUser = mock(User.class); // Erstellt einen virtuellen User
		gModel = new GameModel(mockedUser); // Ertellt das GameModel mit dem
											// virtuellen User

	}

	@Test
	/**
	 * Hier wird die Methode getGameMode der Klasse GameModel getestet
	 */
	public void checkGameMode() {
		// Beim ersten Test wird 0 erwartet
		when(mockedUser.getGameMode()).thenReturn(0);
		assertEquals(0, gModel.getGameMode());

		// Hier wird der 2. und letzte erlaubte Wert 1 erwartet
		when(mockedUser.getGameMode()).thenReturn(1);
		assertEquals(1, gModel.getGameMode());

		// Bei ungültiger Rückgabe vom User wird der Standardmodus 0 gewählt
		when(mockedUser.getGameMode()).thenReturn(3);
		assertEquals(0, gModel.getGameMode());

		when(mockedUser.getGameMode()).thenReturn(-3);
		assertEquals(0, gModel.getGameMode());
	}

	@Test
	/**
	 * Hier wird die Methode getUsername der Klasse GameModel getestet;
	 */
	public void checkUsername() {
		when(mockedUser.getUsername()).thenReturn("Herbert");
		assertEquals("Herbert", gModel.getUsername());

		mockedGameListener = mock(GameListener.class);
		gModel.addGameListener(mockedGameListener);
		GameChangeEvent testEvent = new GameChangeEvent(
				EventType.INVALID_USERNAME, 0);

		when(mockedUser.getUsername()).thenReturn(null);
		assertEquals("Benutzername", gModel.getUsername());
		verify(mockedGameListener).notify(testEvent);

		when(mockedUser.getUsername()).thenReturn("");
		assertEquals("Benutzername", gModel.getUsername());
		verify(mockedGameListener).notify(testEvent);

		// Testfälle für Sonderzeichen
		when(mockedUser.getUsername()).thenReturn("56k.reuter");
		assertEquals("56k.reuter", gModel.getUsername());

		when(mockedUser.getUsername()).thenReturn("!Test?");
		assertEquals("!Test?", gModel.getUsername());

		// Testfälle für Steuerzeichen
		when(mockedUser.getUsername()).thenReturn("er b");
		assertEquals("er-b", gModel.getUsername());

		when(mockedUser.getUsername()).thenReturn("er	b");
		assertEquals("er-b", gModel.getUsername());

		when(mockedUser.getUsername()).thenReturn("er\nb");
		assertEquals("er-b", gModel.getUsername());
	}

	@Test
	/**
	 * Hier wird die getPointsAsString-Methode der Klasse GameModel getestet
	 */
	public void testGetPointsAsString() {
		when(mockedUser.getPoints()).thenReturn(88);
		assertEquals("88", gModel.getPointsAsString());

		when(mockedUser.getPoints()).thenReturn(-88);
		assertEquals("0", gModel.getPointsAsString());
	}

	@Test
	/**
	 * Hier wird die getSeagalCountAsString-Methode der Klasse GameModel getestet
	 */
	public void testGetSeagalCountAsString() {
		when(mockedUser.getSeagalCount()).thenReturn(88);
		assertEquals("88", gModel.getSeagalCountAsString());

		when(mockedUser.getSeagalCount()).thenReturn(-88);
		assertEquals("0", gModel.getSeagalCountAsString());
	}

	@Test
	/**
	 * Hier wird die getBronsonCountAsString-Methode der Klasse GameModel getestet
	 */
	public void testGetBronsonCountAsString() {
		when(mockedUser.getBronsonCount()).thenReturn(88);
		assertEquals("88", gModel.getBronsonCountAsString());

		when(mockedUser.getBronsonCount()).thenReturn(-88);
		assertEquals("0", gModel.getBronsonCountAsString());
	}

	@Test
	/**
	 * Hier wird die getNorrisCountAsString-Methode der Klasse GameModel getestet
	 */
	public void testGetNorrisCountAsString() {
		when(mockedUser.getNorrisCount()).thenReturn(88);
		assertEquals("88", gModel.getNorrisCountAsString());

		when(mockedUser.getNorrisCount()).thenReturn(-88);
		assertEquals("0", gModel.getNorrisCountAsString());
	}

}
