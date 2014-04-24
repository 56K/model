import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;

public class GameModelTest {

	public GameModel gModel;
	public User mUser = mock(User.class);
	
	@Before
	public void setUp(){
		gModel = new GameModel(mUser);
	}
	
	@Test
	public void checkGameMode(){		
		//Beim ersten test wird 0 erwartet
		when(mUser.getGameMode()).thenReturn(0);
		assertEquals(0, gModel.getGameMode());
		
		when(mUser.getGameMode()).thenReturn(1);
		assertEquals(1, gModel.getGameMode());
	
		when(mUser.getGameMode()).thenReturn(3);
		assertEquals(0, gModel.getGameMode());
		
		when(mUser.getGameMode()).thenReturn(-3);
		assertEquals(0, gModel.getGameMode());
	}
}
