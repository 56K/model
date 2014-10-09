package source_main_java;

public class GameChangeEvent implements Comparable<GameChangeEvent> {
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
	public int compareTo(GameChangeEvent o) {
		if (this.getType() == o.getType())
			return 0;
		
		return 1;
		//return this.getType().compareTo(o.getType());
	}
	@Override
	public String toString() {
		StringBuilder result = new StringBuilder();		
		if (this.getType() == EventType.INVALID_USERNAME)
			result.append("Invalid Username, "+ this.getNewValue());
		else if (this.getType() == EventType.BRONSON_CHANGED)
			result.append("Bronson changed, "+ this.getNewValue());
		return result.toString();
	}
}
