import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;

public class GameModelTest {

	public GameModel gModel;
	
	@Test
	public void checkGameMode(){	
		User mUser = mock(User.class);
		gModel = new GameModel(mUser);
		when(mUser.getGameMode()).thenReturn(0);
		assertEquals(0, gModel.getGameMode());
		when(mUser.getGameMode()).thenReturn(1);
		assertEquals(1, gModel.getGameMode());
	}
}
