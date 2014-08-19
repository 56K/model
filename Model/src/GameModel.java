public class GameModel {

	private User user;

	public GameModel(User userArgs) {
		user = userArgs;
	}

	public int getGameMode() {
		
	 //Bei ungültiger Rückgabe wird der Wert 0 gewählt 
	 if (user.getGameMode()<0 || user.getGameMode() > 1) return 0;
	 
	 return user.getGameMode();
	}
}
