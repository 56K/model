package spheres;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;

import spheres.GameChangeEvent.EventType;

public class GameModelTest {

	private GameModel gModel;
	private User mockedUser;
	private GameListener mockedGameListener;

	@Before
	// Diese Methode wird vor jedem Test ausgef�hrt. Gleiche Bedingungen f�r
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
	public void checkUsername() {
		when(mockedUser.getUsername()).thenReturn("Herbert");
		assertEquals("Herbert", gModel.getUsername());

		mockedGameListener = mock(GameListener.class);
		gModel.addGameListener(mockedGameListener);

		when(mockedUser.getUsername()).thenReturn(null);
		assertEquals("Invalid Username", gModel.getUsername());

		when(mockedUser.getUsername()).thenReturn("");
		assertEquals("Invalid Username", gModel.getUsername());
		verify(mockedGameListener, times(2)).notify(
				new GameChangeEvent(EventType.INVALID_USERNAME, 0));

		// Testf�lle f�r Sonderzeichen
		when(mockedUser.getUsername()).thenReturn("56k.reuter");
		assertEquals("56k.reuter", gModel.getUsername());

		when(mockedUser.getUsername()).thenReturn("!Test?");
		assertEquals("!Test?", gModel.getUsername());

		// Testf�lle f�r Steuerzeichen
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

	@Test
	/**
	 * Hier wird der transport der Farbeinstellung des users getestet
	 */
	public void testGetColorSet() {
		// Fehlerfall, es kommt null vom User
		when(mockedUser.getColorSet()).thenReturn(null);
		assertEquals(ColorSet.NORMAL, gModel.getColorSet());
		// ----------------------normal color theme------------------
		when(mockedUser.getColorSet()).thenReturn(ColorSet.NORMAL);
		assertEquals(ColorSet.NORMAL, gModel.getColorSet());
		// ---------------------brigth color theme-------------------
		when(mockedUser.getColorSet()).thenReturn(ColorSet.BRIGHT);
		assertEquals(ColorSet.BRIGHT, gModel.getColorSet());
		// ----------------------dark color theme----------------------
		when(mockedUser.getColorSet()).thenReturn(ColorSet.DARK);
		assertEquals(ColorSet.DARK, gModel.getColorSet());
	}

	@Test
	public void testGetUser() {
		assertNotNull(gModel.getUser());
		assertSame(gModel.getUser(), mockedUser);
	}

	@Test
	public void testDraws() {
		mockedGameListener = mock(GameListener.class);
		gModel.addGameListener(mockedGameListener);

		// Standardwert vor einem Spiel: 30 Z�ge
		assertEquals(30, gModel.getDrawsLeft());
		// Ein normaler Spielablauf soll simuliert werden:
		for (int i = 1; i < 30; i++) {
			gModel.subDraws();
			verify(mockedGameListener).notify(
					new GameChangeEvent(EventType.DRAWS_CHANGED, 30 - i));
			assertEquals(30 - i, gModel.getDrawsLeft());
		}
		//
		when(mockedUser.getGameMode()).thenReturn(1);
		// Der letzte Spielzug wird get�tigt
		gModel.subDraws();
		// Das Spiel ist zu Ende		
		// Verhalten von setDrawsLeft(draws) bei falscher Eingabe
		gModel.setDrawsLeft(10);
		assertEquals(10, gModel.getDrawsLeft());
		
		// zu hohe Zugzahl
		gModel.setDrawsLeft(40);
		assertEquals(0, gModel.getDrawsLeft());
		
		// negative Zugzahl
		gModel.setDrawsLeft(-2);
		assertEquals(0, gModel.getDrawsLeft());
		verify(mockedGameListener, times(3)).notify(
			new GameChangeEvent(EventType.DRAWS_CHANGED, 0));
		verify(mockedGameListener, times(3)).notify(
				new GameChangeEvent(EventType.GAME_OVER, 0));
		
	}

}
