import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class GameModelTest {

	public GameModel gModel;

	@Before
	public void setup(){
		
	}
	@Test
	public void createClass() {
		gModel = new GameModel();
	}
	
	@Test
	public void checkGameMode(){
		gModel = new GameModel();
		assertEquals(0, gModel.getGameMode());
	}
}
