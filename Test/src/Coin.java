

import java.awt.Graphics;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.geom.AffineTransform;
import java.net.URL;

public class Coin {

	// attributes of a frog

	private int x, y; // Position of frog
	private int vx, vy; //velocity variables
	private boolean yellow; // yellow
	private int width; // the size of frog
	private int height;
	private String fileName;
	private Image img; // image of the frog

	// write the constructor for froggy which tales in
	// a string fileName that will be used for the image setup
	// set x and y to be in the middle of a 400x400 screen
	// set width and height to 50

	public Coin(boolean yellow) {
		// assignment statements for attributes

		x = 400;
		y = 800;
		vx = 0;
		vy = 0;
		width = 50;
		height = 50;
		if(yellow)
			img = getImage("yellow.png");
		else
			img = getImage("red.png");
		
		init(x, y);

	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public boolean collided(int ox, int oy, int ow, int oh) {

		Rectangle obs = new Rectangle(ox, oy, ow, oh);
		Rectangle froggy = new Rectangle(x, y, width, height);
		System.out.println(obs);
	System.out.println(froggy);
		return obs.intersects(froggy);
		
	

		


		}
		


	// gets image and procces it

	public void move() {
		
		y += vy;
		x += vx;
		tx.setToTranslation(x, y);

	}
	
	public void hop(int dir) {
		
		switch(dir){
			case 105:			//up
			break;
			
			case 1:			//down
			break;
			
			case 2:			//left
			break;		
			
			case 3:			//right
			break;
		}

		tx.setToTranslation(x, y);

	}
	
	

	private AffineTransform tx = AffineTransform.getTranslateInstance(x, y);

	// draw the affine transform
	public void paint(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		move(); //ask frog to update its location variables
		g2.drawImage(img, tx, null);
		
	}

	private void init(double a, double b) {
		tx.setToTranslation(a, b);
		tx.scale(1, 1);
	}

	// converts image to make it drawable in paint
	private Image getImage(String path) {
		Image tempImage = null;
		try {
			URL imageURL = Coin.class.getResource(path);
			tempImage = Toolkit.getDefaultToolkit().getImage(imageURL);
			
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tempImage;
	}

	// setters and getters

	public void setVx(int vx) {
		this.vx = vx;
	}

	public void setVy(int vy) {
		this.vy = vy;
		
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
		tx.setToTranslation(x, y);
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
		tx.setToTranslation(x, y);
	}

}
