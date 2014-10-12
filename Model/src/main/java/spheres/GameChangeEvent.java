package spheres;

public class GameChangeEvent {
	private EventType type;
	private long newValue;

	public GameChangeEvent(EventType type, long newValue) {
		this.type = type;
		this.newValue = newValue;
	}

	public long getNewValue() {
		return newValue;
	}

	public EventType getType() {
		return type;
	}

	public static enum EventType {
		DRAWS_CHANGED, TIME_CHANGED, POINTS_CHANGED, NORRIS_CHANGED, BRONSON_CHANGED, SEAGAL_CHANGED, GAME_OVER, INVALID_USERNAME;
	}

	@Override
	public String toString() {
		StringBuilder result = new StringBuilder();
		if (this.getType() == EventType.INVALID_USERNAME)
			result.append("Invalid Username, " + this.getNewValue());
		else if (this.getType() == EventType.BRONSON_CHANGED)
			result.append("Bronson changed, " + this.getNewValue());
		else if (this.getType() == EventType.POINTS_CHANGED)
			result.append("Points changed, " + this.getNewValue());
		return result.toString();
	}

	@Override
	public boolean equals(Object object) {
		// Diese Abfrage überprüft ob es sich überhaupt um ein GameChangeEvent
		// handelt
		if (object.getClass() != this.getClass() || object == null)
			return false;
		// handelt es sich um ein GameChangeEvent, so wird das Object auf
		// selbigen Typ gecastet um die Methoden der Klasse zu nutzen
		GameChangeEvent otherEvent = (GameChangeEvent) object;
		//Hier wird geprüft ob das Event und der Übergabewert gleich sind
		if (otherEvent.getType() == this.getType()
				&& otherEvent.getNewValue() == this.getNewValue())
			return true;
		return false;
	}
}
