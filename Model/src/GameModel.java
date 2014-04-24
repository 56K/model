
public class GameModel {

	private User user;
	
	public GameModel(User userArgs){
		user = userArgs;
	}
	
	public int getGameMode() {
		return user.getGameMode();
	}
}
