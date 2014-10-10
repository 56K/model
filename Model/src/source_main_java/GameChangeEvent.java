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

	@Override
	public String toString() {
		StringBuilder result = new StringBuilder();		
		if (this.getType() == EventType.INVALID_USERNAME)
			result.append("Invalid Username, "+ this.getNewValue());
		else if (this.getType() == EventType.BRONSON_CHANGED)
			result.append("Bronson changed, "+ this.getNewValue());
		return result.toString();
	}
	
	@Override
	public boolean equals(Object object){
		if (object == null)
			return false;
		if (object.getClass() != this.getClass())
			return false;
		GameChangeEvent otherEvent = (GameChangeEvent) object;
		if (otherEvent.getType()==this.getType() && otherEvent.getNewValue() == this.getNewValue())
			return true;
		return false;
	}
}
