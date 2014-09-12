package source_main_java;

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
}
