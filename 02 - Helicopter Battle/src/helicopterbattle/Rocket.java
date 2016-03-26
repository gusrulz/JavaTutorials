package helicopterbattle;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

/**
 * Rocket.
 * Use: create object of this class then initialize it.
 * @author www.gametutorial.net
 *
 */

public class Rocket {

	// Time that must pass before another rocket can be fired.
	public final static long timeBetweenNewRockets = Framework.secInNanosec / 4;
	public static long timeOfLastCreatedRocket = 0;
	
	// Damage to an enemy helicopter when it is hit by a rocket.
	public static int damagePower = 100;
	
	// Rocket position
	public int xCoordinate;
	public int yCoordinate;
	
	// Moving speed and also direction. Rocket always goes straight, so it only moves on the x coordinate.
	private double movingXspeed;
	
	// Life time of current rocket smoke.
	public long currentSmokeLifeTime;
	
	// Imgae of rocket. Image is loaded and set in Game class in LoadContent().
	public static BufferedImage rocketImg;
	
	
	/**
	 * Set variables and objects for this class
	 */
	public void Initialize(int xCoordinate, int yCoordinate) {
		
		this.xCoordinate = xCoordinate;
		this.yCoordinate = yCoordinate;
		
		this.movingXspeed = 23;
		
		this.currentSmokeLifeTime = Framework.secInNanosec / 2;
	}
	
	/**
	 * Checks if the rocket has left the screen.
	 * 
	 * @return true if the rocket has left the screen, false otherwise.
	 */
	public boolean hasItLeftScreen() {
		
		if(xCoordinate > 0 && xCoordinate < Framework.frameWidth) {
			
			return false;
		}
		else {
			
			return true;
		}
	}
	
	/**
	 * Moves the rocket.
	 */
	public void Update() {
		
		xCoordinate += movingXspeed;
	}
	
	/**
	 * Draws the rocket to the screen.
	 * 
	 * @param g2d Graphics2D
	 */
	public void Draw(Graphics2D g2d) {
		
		g2d.drawImage(rocketImg, xCoordinate, yCoordinate, null);
	}
}