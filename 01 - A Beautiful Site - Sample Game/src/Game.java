import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.JFrame;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Game extends JPanel {
	int x = 0;
	int y = 0;
	
	final static int FRAME_WIDTH = 1000;
	final static int FRAME_HEIGHT = 500;
	final static int OVAL_WIDTH = 30;
	final static int OVAL_HEIGHT = 30;
	final static int HOR_SPEED = 2;
	final static int VER_SPEED = 2;
	
	String x_dir = "Right";
	String y_dir = "Down";
	
	private void moveBall() {
		if(x <= 0) {
			x = x + HOR_SPEED;
			x_dir = "Right";
		}
		else if(x >= FRAME_WIDTH - (OVAL_WIDTH * 1.5)) {
			x = x - HOR_SPEED;
			x_dir = "Left";
		}
		else {
			if(x_dir == "Right") {
				x = x + HOR_SPEED;
			}
			else if(x_dir == "Left") {
				x = x - HOR_SPEED;
			}
		}
		if(y <= 0) {
			y = y + VER_SPEED;
			y_dir = "Down";
		}
		else if(y >= FRAME_HEIGHT - (OVAL_HEIGHT * 2)) {
			y = y - VER_SPEED;
			y_dir = "Up";
		}
		else {
			if(y_dir == "Down") {
				y = y + VER_SPEED;
			}
			else if(y_dir == "Up") {
				y = y - VER_SPEED;
			}
		}
	}
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2d.fillOval(x, y, OVAL_WIDTH, OVAL_HEIGHT);
	}
	
	public static void main(String[] args) throws InterruptedException {
		JFrame frame = new JFrame("Sample Frame");
		Game game = new Game();
		frame.add(game);
		frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		while(true) {
			game.moveBall();
			game.repaint();
			Thread.sleep(10);
		}
	}
}
