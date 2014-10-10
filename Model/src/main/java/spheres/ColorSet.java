package spheres;

import java.awt.Color;
import java.util.Random;

public enum ColorSet {
	BRIGHT {
		private Color[] colors = new Color[5];
		{
			colors[0] = Color.GRAY.brighter();
			colors[1] = Color.RED.brighter();
			colors[2] = Color.BLUE.brighter();
			colors[3] = Color.GREEN.brighter();
			colors[4] = Color.YELLOW.brighter();
		}

		public Color getColor(int nextInt) {
			return colors[nextInt];
		}
	},
	NORMAL {
		private Color[] colors = new Color[5];
		{
			colors[0] = Color.GRAY;
			colors[1] = Color.RED;
			colors[2] = Color.BLUE;
			colors[3] = Color.GREEN;
			colors[4] = Color.YELLOW;
		}

		public Color getColor(int nextInt) {
			return colors[nextInt];
		}
	},
	DARK {
		private Color[] colors = new Color[5];
		{
			colors[0] = Color.BLACK;
			colors[1] = Color.RED.darker();
			colors[2] = Color.BLUE.darker();
			colors[3] = Color.GREEN.darker();
			colors[4] = Color.YELLOW.darker();
		}

		public Color getColor(int nextInt) {
			return colors[nextInt];
		}
	};

	private Random random = new Random();

	public Color newRandomColor() {
		int nextInt = random.nextInt(5);
		return getColor(nextInt);
	}

	public abstract Color getColor(int nextInt);

}
