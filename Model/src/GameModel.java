public class GameModel {

	private User user;

	public GameModel(User userArgs) {
		user = userArgs;
	}

	public int getGameMode() {
		/**
		 * Diese Methode liefert den Spielmodus zur�ck. Aktuell 2 Modi: 0 und 1.
		 */
		// Bei ung�ltiger R�ckgabe wird der Wert 0 gew�hlt
		if (user.getGameMode() < 0 || user.getGameMode() > 1)
			return 0;

		return user.getGameMode();
	}

	public String getUsername() {
		if (user.getUsername() == null || user.getUsername().isEmpty()) {
			// JOptionPane.showMessageDialog(null,
			// "Kein Benutzername hinterlegt!", "Fehlermeldung",
			// JOptionPane.ERROR_MESSAGE);
			// Hier nur Dialogbox, weil keine sinnvolle Referenz vorhanden
			return "Benutzername";
		} else if (!user.getUsername().matches("\\S+"))
			return "Benutzername";

		return user.getUsername();
	}
	
	public String getPointsAsString(){
		Integer points;
		if ((points = user.getPoints()) < 0)
			return "0";
		return points.toString();
	}
}