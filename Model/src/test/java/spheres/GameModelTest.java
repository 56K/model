package spheres;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.awt.Color;
import java.awt.Point;

import org.junit.Before;
import org.junit.Test;

import spheres.GameChangeEvent.EventType;

public class GameModelTest {

	private GameModel gModel;
	private User mockedUser;
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

		when(mockedUser.getUsername()).thenReturn(null);
		assertEquals("Invalid Username", gModel.getUsername());

		when(mockedUser.getUsername()).thenReturn("");
		assertEquals("Invalid Username", gModel.getUsername());
		verify(mockedGameListener, times(2)).notify(
				new GameChangeEvent(EventType.INVALID_USERNAME, 0));

		gModel.removeGameListener(mockedGameListener);

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
		when(mockedUser.getGameMode()).thenReturn(1);

		// Standardwert vor einem Spiel: 30 Züge
		assertEquals(30, gModel.getDrawsLeft());
		// Ein normaler Spielablauf soll simuliert werden:
		for (int i = 1; i < 30; i++) {
			gModel.subDraws();
			verify(mockedGameListener).notify(
					new GameChangeEvent(EventType.DRAWS_CHANGED, 30 - i));
			assertEquals(30 - i, gModel.getDrawsLeft());
		}
		//

		// Der letzte Spielzug wird getätigt
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

		gModel.removeGameListener(mockedGameListener);
	}

	@Test
	public void testTime() {
		mockedGameListener = mock(GameListener.class);
		gModel.addGameListener(mockedGameListener);
		when(mockedUser.getGameMode()).thenReturn(0);

		// Standardwert vor dem Spiel
		assertEquals(60000, gModel.getTimeLeft());

		// Spiel wird simuliert
		for (int i = 1000; i < 60000; i += 1000) {
			gModel.setTimeLeft(60000 - i);
			verify(mockedGameListener).notify(
					new GameChangeEvent(EventType.TIME_CHANGED, 60000 - i));
		}
		// noch eine Sekunde übrig
		assertEquals(1000, gModel.getTimeLeft());

		// Zeit wird 0 ->Spiel zu Ende
		gModel.setTimeLeft(0);
		assertEquals(0, gModel.getTimeLeft());

		// zu hohe zeit
		gModel.setTimeLeft(100000);
		assertEquals(0, gModel.getTimeLeft());

		// negative Zeit
		gModel.setTimeLeft(-1000);
		assertEquals(0, gModel.getTimeLeft());

		// Die erwarteten notify()-Aufrufe
		verify(mockedGameListener, times(3)).notify(
				new GameChangeEvent(EventType.TIME_CHANGED, 0));
		verify(mockedGameListener, times(3)).notify(
				new GameChangeEvent(EventType.GAME_OVER, 0));

		gModel.removeGameListener(mockedGameListener);
	}

	@Test
	public void testBallAddGet() {
		// Hier wird gezeigt das die Routinen zum hinzufügen eines Balles sauber
		// arbeiten
		Ball testBall = new Ball(new Point(2, 3), Color.RED);
		gModel.addBall(testBall);
		assertEquals(testBall, gModel.getBall(new Point(2, 3)));
		assertEquals(testBall, gModel.getBall(2, 3));
	}

	@Test
	public void testDeleteBall() {
		mockedGameListener = mock(GameListener.class);
		gModel.addGameListener(mockedGameListener);

		// 4 Testbälle, hier wird getestet ob ein Ball gelöscht wird und ob die
		// darüberliegen Bälle nach "unten Fallen".
		Ball testBall1 = new Ball(new Point(2, 0), Color.RED);
		Ball testBall2 = new Ball(new Point(2, 1), Color.GRAY);
		Ball testBall3 = new Ball(new Point(2, 2), Color.GREEN);
		Ball testBall4 = new Ball(new Point(2, 3), Color.BLUE);
		Ball testBall5;

		gModel.addBall(testBall1);
		gModel.addBall(testBall2);
		gModel.addBall(testBall3);
		gModel.addBall(testBall4);

		when(mockedUser.getColorSet()).thenReturn(ColorSet.NORMAL);
		when(mockedUser.getInGamePoints()).thenReturn(1);
		gModel.deleteBall(testBall4);
		verify(mockedGameListener).notify(
				new GameChangeEvent(EventType.POINTS_CHANGED, 1));

		testBall5 = gModel.getBall(2, 0);
		assertEquals(Color.RED, gModel.getBall(2, 1).getBallColor());
		assertEquals(Color.GRAY, gModel.getBall(2, 2).getBallColor());
		assertEquals(Color.GREEN, gModel.getBall(2, 3).getBallColor());

		// Diese Zeile beweist das ein neuer ball angelegt wurde, und das die
		// Farbe sich zufällig ergibt
		System.out.println(testBall5.getBallColor().toString());

		gModel.removeGameListener(mockedGameListener);
	}

	@Test
	public void testDeletColor() {
		mockedGameListener = mock(GameListener.class);
		gModel.addGameListener(mockedGameListener);

		// zunächst das Array mit Bällen gleicher Farbe füllen
		for (int i = 0; i < 6; i++) {
			for (int j = 0; j < 6; j++) {
				gModel.addBall(new Ball(i, j, Color.GRAY));
			}
		}
		// 4 testbälle gleicher Farbe anlegen und demm Array hinzufügen
		gModel.addBall(new Ball(1, 0, Color.RED));
		gModel.addBall(new Ball(5, 2, Color.RED));
		gModel.addBall(new Ball(0, 3, Color.RED));
		gModel.addBall(new Ball(4, 5, Color.RED));

		when(mockedUser.getColorSet()).thenReturn(ColorSet.NORMAL);
		when(mockedUser.getInGamePoints()).thenReturn(1);
		gModel.deleteColor(Color.RED);
		verify(mockedGameListener, times(4)).notify(
				new GameChangeEvent(EventType.POINTS_CHANGED, 1));

		gModel.removeGameListener(mockedGameListener);
	}
	
	@Test
	public void testGetBalls(){
		Ball [][] testBalls = new Ball[6][6];
		assertNotNull(gModel.getBalls());
		assertEquals(testBalls, gModel.getBalls());
	}
}
