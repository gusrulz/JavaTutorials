package helicopterbattle;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 * Helicopter which is managed by player.
 * 
 * @author www.gametutorial.net
 */

public class PlayerHelicopter {

	// Health of the helicopter
	private final int healthInit = 100;
	public int health;
	
	// Position of the helicopter on the screen
	public int xCoordinate;
	public int yCoordinate;
	
	// Moving speed and also direction
	private double movingXspeed;
	public double movingYspeed;
	private double acceleratingXspeed;
	private double acceleratingYspeed;
	private double stoppingXspeed;
	private double stoppingYspeed;
	
	// Helicopter rockets.
	private final int numberOfRocketsInit = 80;
	public int numberOfRockets;
	
	// Helicopter machinegun ammo
	private final int amountOfAmmoInit = 1400;
	public int amountOfAmmo;
	
	// Images of helicopter and its propellers
	public BufferedImage helicopterBodyImg;
	private BufferedImage helicopterFrontPropellerAnimImg;
	private BufferedImage helicopterRearPropellerAnimImg;
	
	// Animation of the helicopter propeller
	private Animation helicopterFrontPropellerAnim;
	private Animation helicopterRearPropellerAnim;
	// Offset for the propeller
	private int offsetXFrontPropeller;
	private int offsetYFrontPropeller;
	private int offsetXRearPropeller;
	private int offsetYRearPropeller;
	
	// Offset of the helicopter rocket holder.
	private int offsetXRocketHolder;
	private int offsetYRocketHolder;
	// Position on the frame/window of the helicopter rocket holder.
	public int rocketHolderXcoordinate;
	public int rocketHolderYcoordinate;
	
	// Offset of the helicopter machine gun.
	private int offsetXMachineGun;
	private int offsetYMachineGun;
	// Position on the frame.window of the helicopter machine gun.
	public int machineGunXcoordinate;
	public int machineGunYcoordinate;
	
	/**
	 * Creates object of player.
	 * 
	 * @param xCoordinate Starting x coordinate of helicopter.
	 * @param yCoordinate Starting y coordinate of helicopter.
	 */
	public PlayerHelicopter(int xCoordinate, int yCoordinate) {
		
		this.xCoordinate = xCoordinate;
		this.yCoordinate = yCoordinate;
		
		LoadContent();
		Initialize();
	}
	
	/**
	 * Set variables and objects for this class.
	 */
	private void Initialize(){
		
		this.health = healthInit;
		
		this.numberOfRockets = numberOfRocketsInit;
		this.amountOfAmmo = amountOfAmmoInit;
		
		this.movingXspeed = 0;
		this.movingYspeed = 0;
		this.acceleratingXspeed = 0.2;
		this.acceleratingYspeed = 0.2;
		this.stoppingXspeed = 0.1;
		this.stoppingYspeed = 0.1;
		
		this.offsetXFrontPropeller = 70;
		this.offsetYFrontPropeller = -23;
		this.offsetXRearPropeller = -6;
		this.offsetYRearPropeller = -21;
		
		this.offsetXRocketHolder = 138;
		this.offsetYRocketHolder = 40;
		this.rocketHolderXcoordinate = this.xCoordinate + this.offsetXRocketHolder;
		this.rocketHolderYcoordinate = this.yCoordinate + this.offsetYRocketHolder;
		
		this.offsetXMachineGun = helicopterBodyImg.getWidth() - 40;
		this.offsetYMachineGun = helicopterBodyImg.getHeight();
		this.machineGunXcoordinate = this.xCoordinate + this.offsetXMachineGun;
		this.machineGunYcoordinate = this.yCoordinate + this.offsetYMachineGun;
	}
	
	/**
	 * Load files for this class.
	 */
	private void LoadContent() {
		
		try {
			
			URL helicopterBodyImgUrl = this.getClass().getResource("/helicopterbattle/resources/images/1_helicopter_body.png");
            helicopterBodyImg = ImageIO.read(helicopterBodyImgUrl);
            
            URL helicopterFrontPropellerAnimImgUrl = this.getClass().getResource("/helicopterbattle/resources/images/1_front_propeller_anim.png");
            helicopterFrontPropellerAnimImg = ImageIO.read(helicopterFrontPropellerAnimImgUrl);
            
            URL helicopterRearPropellerAnimImgUrl = this.getClass().getResource("/helicopterbattle/resources/images/1_rear_propeller_anim_blur.png");
            helicopterRearPropellerAnimImg = ImageIO.read(helicopterRearPropellerAnimImgUrl);
        } 
        catch (IOException ex) {
            Logger.getLogger(PlayerHelicopter.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        // Now that we have images of propeller animation we initialize animation object.
        helicopterFrontPropellerAnim = new Animation(helicopterFrontPropellerAnimImg, 204, 34, 3, 20, true, xCoordinate + offsetXFrontPropeller, yCoordinate + offsetYFrontPropeller, 0);
        helicopterRearPropellerAnim = new Animation(helicopterRearPropellerAnimImg, 54, 54, 4, 20, true, xCoordinate + offsetXRearPropeller, yCoordinate + offsetYRearPropeller, 0);
	}
	
	/**
	 * Resets the player.
	 * 
	 * @param xCoordinate Starting x coordinate of helicopter.
	 * @param yCoordinate Starting y coordinate of helicopter.
	 */
	public void Reset(int xCoordinate, int yCoordinate) {
		
		this.health = healthInit;
		
		this.numberOfRockets = numberOfRocketsInit;
		this.amountOfAmmo = amountOfAmmoInit;
		
		this.xCoordinate = xCoordinate;
		this.yCoordinate = yCoordinate;
		
		this.machineGunXcoordinate = this.xCoordinate + this.offsetXMachineGun;
		this.machineGunYcoordinate = this.yCoordinate + this.offsetYMachineGun;
		
		this.movingXspeed = 0;
		this.movingYspeed = 0;
	}
	
	/**
	 * Checks if player is shooting. It also checks if player can
	 * shoot (time between bullets, does a player have a billet left).
	 * 
	 * @param gameTime The current elapsed game time in nanoseconds.
	 * @return true if player is shooting.
	 */
	public boolean isShooting(long gameTime) {
		
		// Checks if left mouse button is down && if it is the time for a new bullet.
		if(Canvas.mouseButtonState(MouseEvent.BUTTON1) &&
		   ((gameTime - Bullet.timeOfLastCreatedBullet) >= Bullet.timeBetweenNewBullets) &&
		   this.amountOfAmmo > 0) {
			
			return true;
		}
		else {
			
			return false;
		}
	}
	
	/**
	 * Checks if player has fired a rocket. It also checks if the player can
	 * fire a rocket (time between rockets, does a player have any rockets left).
	 * 
	 * @param gameTime The current elapsed game time in nanoseconds.
	 * @return true if player has fired a rocket.
	 */
	public boolean hasFiredRocket(long gameTime) {
		
		// Checks if right mouse button is down && if it is time for a new rocket && if there are any rockets left.
		if(Canvas.mouseButtonState(MouseEvent.BUTTON3) &&
		  ((gameTime - Rocket.timeOfLastCreatedRocket) >= Rocket.timeBetweenNewRockets) &&
		  this.numberOfRockets > 0) {
			
			return true;
		}
		else {
			
			return false;
		}
	}
	
	/**
	 * Checks if player is moving and sets its moving speed 
	 */
	public void isMoving() {
		
		// Moving on the x coordinate.
		if(Canvas.keyboardKeyState(KeyEvent.VK_D) || Canvas.keyboardKeyState(KeyEvent.VK_RIGHT)) {
			
			movingXspeed += acceleratingXspeed;
		}
		else if(Canvas.keyboardKeyState(KeyEvent.VK_A) || Canvas.keyboardKeyState(KeyEvent.VK_LEFT)) {
			
			movingXspeed -= acceleratingXspeed;
		}
		else {
			
			if(movingXspeed < 0) {
				
				movingXspeed += stoppingXspeed;
			}
			else if(movingXspeed > 0) {
				
				movingXspeed -= stoppingXspeed;
			}
		}
		
		// Moving on the y coordinate.
		
	}
}
