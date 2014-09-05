package source_main_java;

import java.awt.event.ActionListener;

public class GameModel {

	private User user;

	public GameModel(User userArgs) {
		user = userArgs;
	}

	public int getGameMode() {
		/**
		 * Diese Methode liefert den Spielmodus zurück. Aktuell 2 Modi: 0 und 1.
		 */
		// Bei ungültiger Rückgabe wird der Wert 0 gewählt
		if (user.getGameMode() < 0 || user.getGameMode() > 1)
			return 0;

		return user.getGameMode();
	}

	public String getUsername() {
		if (user.getUsername() == null || user.getUsername().isEmpty()) 
			return "Benutzername";
		
		else if (!user.getUsername().matches("\\S+"))
			return user.getUsername().replaceAll("\\s", "-");

		return user.getUsername();
	}
	
	public String getPointsAsString(){
		Integer points;
		if ((points = user.getPoints()) < 0)
			return "0";
		return points.toString();
	}
	
	public String getSeagalCountAsString(){
		Integer count;
		if ((count = user.getSeagalCount()) < 0)
			return "0";
		return count.toString();
	}
	
	public String getNorrisCountAsString(){
		Integer count;
		if ((count = user.getNorrisCount()) < 0)
			return "0";
		return count.toString();
	}
	
	public String getBronsonCountAsString(){
		Integer count;
		if ((count = user.getBronsonCount()) < 0)
			return "0";
		return count.toString();
	}
	
	
	
	public void addInvalidUsernameListener(ActionListener invalidUsername){
		invalidUsernameListener.add();
	}

}