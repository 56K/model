package spheres;

import java.util.ArrayList;
import java.util.List;

import spheres.GameChangeEvent.EventType;

public class GameModel {

	private User user;
	private List<GameListener> listeners;

	public GameModel(User userArgs) {
		user = userArgs;
		this.listeners = new ArrayList<>();
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
		if (user.getUsername() == null || user.getUsername().isEmpty()) {
			fireGameEvent(new GameChangeEvent(EventType.INVALID_USERNAME, 0));
			return "Invalid Username";
		}

		else if (!user.getUsername().matches("\\S+"))
			return user.getUsername().replaceAll("\\s", "-");

		return user.getUsername();
	}

	// ----------------- Punktestände als String -------------------------------
	public String getPointsAsString() {
		Integer points;
		if ((points = user.getPoints()) < 0)
			return "0";
		return points.toString();
	}

	public String getSeagalCountAsString() {
		Integer count;
		if ((count = user.getSeagalCount()) < 0)
			return "0";
		return count.toString();
	}

	public String getNorrisCountAsString() {
		Integer count;
		if ((count = user.getNorrisCount()) < 0)
			return "0";
		return count.toString();
	}

	public String getBronsonCountAsString() {
		Integer count;
		if ((count = user.getBronsonCount()) < 0)
			return "0";
		return count.toString();
	}

	// ------ Farbwahl des Users transportieren -------------------------------

	public ColorSet getColorSet() {
		return user.getColorSet();
	}

	public User getUser(){
		return user;
	}
	
	
	
	// Methoden zum steuern des GameEvent-Managements
	protected void fireGameEvent(GameChangeEvent event) {
		for (GameListener listener : listeners) {
			listener.notify(event);
		}
	}

	public void addGameListener(GameListener listener) {
		listeners.add(listener);
		if (user != null)
			user.addGameListener(listener);
	}

	public void removeGameListener(GameListener listener) {
		listeners.remove(listener);
		if (user != null)
			user.removeGameListener(listener);
	}

}