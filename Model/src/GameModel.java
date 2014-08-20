public class GameModel {

	private User user;

	public GameModel(User userArgs) {
		user = userArgs;
	}

	public int getGameMode() {
		// Bei ung�ltiger R�ckgabe wird der Wert 0 gew�hlt
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
 * userSettings eigene Klasse -> fire Listener changeNameB2 Uunterpunkt im men�,
 * geht zur�ck ins men� feuert Action listener und somit wird mann zum �ndern
 * gezwungen
 */