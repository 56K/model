package source_main_java;

public interface User {

	// Funktionsprototypen
	public int getGameMode();

	public String getUsername();

	public int getPoints();

	public int getSeagalCount();

	public int getBronsonCount();

	public int getNorrisCount();

	public void fireGameEvent(GameChangeEvent event);

	public void addGameListener(GameListener listener);

	public void removeGameListener(GameListener listener);
}
