package helicopterbattle;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;

/**
 * Create a JPanel on which we draw and listen for keyboard and mouse events.
 * 
 * @author www.gametutorial.net
 */

public abstract class Canvas extends JPanel implements KeyListener, MouseListener {
	
	// Keyboard states - Here are stored states for keyboard keys - is it down or not.
	private static boolean[] keyboardState = new boolean[525];
	
	// Mouse states - Here are stored states for mouse keys - is it down or not.
	private static boolean[] mouseState = new boolean[3];
	
	
	public Canvas() {
		
		// We use double buffer to draw on the screen.
		this.setDoubleBuffered(true);
		this.setFocusable(true);
		this.setBackground(Color.black);
		
		// If you will draw your own mouse cursor or if you just want that mouse cursor to disappear,
		// insert "true" into if condition and mouse cursor will be removed.
		if(true) {
			
			BufferedImage blankCursoImg = new BufferedImage(16, 16, BufferedImage.TYPE_INT_ARGB);
			Cursor blankCursor = Toolkit.getDefaultToolkit().createCustomCursor(blankCursorImg, new Point(0, 0), null);
			this.setCursor(blankCursor);
		}
		
		// Adds the keyboard listener to JPanel to receive key events from this component.
		this.addKeyListener(this);
		// Adds the mouse listener to JPanel to receive mouse events from this component.
		this.addMouseListener(this);
	}
	
	// This method is overridden in Framework.java and is used for drawing to the screen.
	public abstract void Draw(Graphics2D g2d);
}
