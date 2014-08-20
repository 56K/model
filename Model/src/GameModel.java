public class GameModel {

	private User user;

	public GameModel(User userArgs) {
		user = userArgs;
	}

	public int getGameMode() {
		// Bei ungültiger Rückgabe wird der Wert 0 gewählt
		if (user.getGameMode() < 0 || user.getGameMode() > 1)
			return 0;

		return user.getGameMode();
	}

	public String getUsername() {
		if (user.getUsername() == null)
			return "Benutzername";
		
		return user.getUsername();
	}
}

/*
 * userSettings eigene Klasse -> fire Listener changeNameB2 Uunterpunkt im menü,
 * geht zurück ins menü feuert Action listener und somit wird mann zum ändern
 * gezwungen
 */