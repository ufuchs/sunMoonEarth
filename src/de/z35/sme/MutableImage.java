package de.z35.sme;

import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;

/**
 * 
 * @author Uli Fuchs
 *
 */
public abstract class MutableImage {
	
	protected Image image;
	
	protected int width;
	
	protected int heigth;
	
	private int backgroundColor;	
	
	protected Graphics g;
	
	/**
	 * 
	 *
	 */
	public MutableImage(final int backgroundColor, final int width,
			final int heigth) {

		super();

		this.width = width;

		this.heigth = heigth;

		this.backgroundColor = backgroundColor;

		initialize();

	}

	/**
	 * 
	 * 
	 */
	private void initialize() {
		
		image = Image.createImage(width, heigth);
		
		g = image.getGraphics();
		
		setBackgroundColor(backgroundColor);
		
	}

	////////////////////////////////////////////////////////////////////////////
	// accessors
	////////////////////////////////////////////////////////////////////////////
	
	/**
	 * 
	 * @return
	 */
	public Image getImage() {
		
		return image;
	}
	
	////////////////////////////////////////////////////////////////////////////
	// mutators
	////////////////////////////////////////////////////////////////////////////
	
	/**
	 * 
	 * @param backgroundColor
	 */
	public void setBackgroundColor(int backgroundColor) {

		if ((backgroundColor > 0) && (this.backgroundColor != backgroundColor)) {
		
			this.backgroundColor = backgroundColor;

			g.setColor(backgroundColor);
			
			g.fillRect(0, 0, width, heigth);
			
			this.paintImage();
			
		}
		
	}
	
	////////////////////////////////////////////////////////////////////////////
	// production
	////////////////////////////////////////////////////////////////////////////
	
	/**
	 * 
	 *
	 */
	public abstract void paintImage();
	
}
