
public class GameModel {

	private User user;
	
	public GameModel(User userArgs){
		user = userArgs;
	}
	
	public int getGameMode() {
		if (user.getGameMode()<0 || user.getGameMode() > 1)
			return 0;
			
		return user.getGameMode();
		
	}
}
