import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;

public class GameModelTest {

	public GameModel gModel;
	public User mockedUser = mock(User.class);
	
	@Before
	public void setUp(){
		gModel = new GameModel(mockedUser);
	}
	
	@Test
	public void checkGameMode(){		
		//Beim ersten test wird 0 erwartet
		when(mockedUser.getGameMode()).thenReturn(0);
		assertEquals(0, gModel.getGameMode());
		
		//Hier wird der 2. und letzte erlaubte Wert 1 erwartet
		when(mockedUser.getGameMode()).thenReturn(1);
		assertEquals(1, gModel.getGameMode());
		
		//Bei ungültiger Rückgabe vom User wird der Standardmodus 0 gewählt
		when(mockedUser.getGameMode()).thenReturn(3);
		assertEquals(0, gModel.getGameMode());
		
		when(mockedUser.getGameMode()).thenReturn(-3);
		assertEquals(0, gModel.getGameMode());
	}
}
