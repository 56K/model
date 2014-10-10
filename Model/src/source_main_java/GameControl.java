package source_main_java;

import source_main_java.GameChangeEvent.EventType;


public class GameControl implements GameListener {
	
	private GameModel gModel;
	
	public GameControl(GameModel gModelArgs){
		gModel=gModelArgs;
		
		gModel.addGameListener(this);
	}

	
	
	@Override
	public void notify(GameChangeEvent event) {
		if (event.getType()==EventType.INVALID_USERNAME)
			System.out.println("INVALID_USERNAME");
		
	}
}
