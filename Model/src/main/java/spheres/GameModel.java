package spheres;

import java.awt.Color;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import spheres.GameChangeEvent.EventType;

public class GameModel {

	private User user;
	private List<GameListener> listeners;
	private int drawsLeft;
	private long timeLeft;
	private Ball[][] balls;

	public GameModel(User userArgs) {
		user = userArgs;
		this.listeners = new ArrayList<>();
		drawsLeft = 30;
		timeLeft = TimeUnit.SECONDS.toMillis(60);
		balls = new Ball[6][6];
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
		if (user.getColorSet() == null)
			return ColorSet.NORMAL;
		return user.getColorSet();
	}

	public User getUser() {
		return user;
	}

	// ------------Zugspiel---------------------------------
	public int getDrawsLeft() {
		return drawsLeft;
	}

	public void subDraws() {
		setDrawsLeft(getDrawsLeft() - 1);
	}

	public void setDrawsLeft(int draws) {
		if (draws < 0 || draws > 30)
			draws = 0;
		drawsLeft = draws;
		fireGameEvent(new GameChangeEvent(EventType.DRAWS_CHANGED, draws));
		if (draws <= 0 && getGameMode() == 1)
			fireGameEvent(new GameChangeEvent(EventType.GAME_OVER, 0));
	}

	// --------------Zeitspiel-------------------------------
	public long getTimeLeft() {
		return timeLeft;
	}

	public void setTimeLeft(long time) {
		if (time < 0 || time > 60000)
			time = 0;
		timeLeft = time;
		fireGameEvent(new GameChangeEvent(EventType.TIME_CHANGED, time));
		if (time <= 0 && getGameMode() == 0)
			fireGameEvent(new GameChangeEvent(EventType.GAME_OVER, 0));
	}

	// ----------------Ball-Operationen--------------
	public void addBall(Ball ballArgs) {
		balls[ballArgs.getPos().x][ballArgs.getPos().y] = ballArgs;
	}
	
	public Ball getBall(int x, int y){
		return balls[x][y];
	}
	public Ball getBall(Point there){
		return balls[there.x][there.y];
	}
	
	public Ball[][] getBalls(){
		return balls;
	}
	
	public void deleteBall(Ball ball) {
        Point pos = ball.getPos();
        balls[pos.x][pos.y] = null;
        for (int i = pos.y - 1; i >= 0; i--) {
            Ball current = balls[pos.x][i];
            current.setPos(new Point(pos.x, i + 1));
            balls[pos.x][i + 1] = current;
        }
        balls[pos.x][0] = new Ball(pos.x, 0, getColorSet().newRandomColor());
        user.addInGamePoints(1);
        fireGameEvent(new GameChangeEvent(EventType.POINTS_CHANGED, user.getInGamePoints()));
    }
	
	 public void deleteColor(Color ballColor) {
	        Ball[][] balls2 = getBalls();
	        for (Ball[] balls : balls2) {
	            for (Ball ball : balls) {
	                if (ball.getBallColor().equals(ballColor))
	                    deleteBall(ball);
	            }
	        }
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