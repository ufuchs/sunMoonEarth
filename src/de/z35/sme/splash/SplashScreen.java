package de.z35.sme.splash;

import javax.microedition.lcdui.*;

import org.j4me.ui.DeviceScreen;
import org.j4me.ui.ApplicationInitializer;

/**
 * SplashScreen: Implements the splash screen for a simple MIDlet game.
 */
public class SplashScreen extends Canvas implements Runnable {
	
    private Image splashImage;
    private boolean dismissed;

	private boolean isLaunched;	
	private volatile DeviceScreen nextScreen;     
	final private ApplicationInitializer initializer;

	/**
	 * 
	 * @param splashImage
	 * @param initializer
	 */
    public SplashScreen(Image splashImage, ApplicationInitializer initializer) {

		super.setFullScreenMode(true);

		this.splashImage = splashImage;

		this.initializer = initializer;

	}
    
    /**
	 * 
	 */
    public void run() {
    	
    	// ohne 'synchronized' passiert hier nichts!
		synchronized (this) {
			
			this.nextScreen = this.initializer.initApp();
			
			try {
				wait(3000L); // 3 seconds
			} catch (InterruptedException e) {
				// can't happen in MIDP: no Thread.interrupt method
			}
			
			dismiss();
			
		}
		
	}

    /**
     * 
     */
    public void paint(Graphics g)
    {
    	
        new Thread(this).start();    	
    	
        int width = getWidth();
        int height = getHeight();
        
        g.setColor(0x00FFFFFF);  // white
        g.fillRect(0, 0, width, height);

        g.setColor(0x00FF0000);  // red
        g.drawRect(1, 1, width-3, height-3);  // red border one pixel from edge

        if (splashImage != null)
        {
            g.drawImage(splashImage,
                        width/2,
                        height/2,
                        Graphics.VCENTER | Graphics.HCENTER);

        }
        
        g.setColor(0, 0, 255);
        
//        Font font = Font.getFont(Font.FACE_MONOSPACE, Font.STYLE_BOLD, Font.SIZE_LARGE);

        g.setFont( Font.getFont(Font.FACE_MONOSPACE, Font.STYLE_BOLD, Font.SIZE_LARGE) );
        
        // Nutzen Sie den Dunst der Stunde.
        //  Geist ist geil.
        g.drawString ("Was ich nicht weiﬂ", getWidth () / 2, getHeight () / 2, 
                Graphics.HCENTER | Graphics.BASELINE);
        
        g.drawString ("macht mich heiﬂ.", getWidth () / 2, getHeight () / 2 + 20, 
                Graphics.HCENTER | Graphics.BASELINE);

        g.drawString ("-- Einstein --", getWidth () / 2, getHeight () / 2 + 40, 
                Graphics.HCENTER | Graphics.BASELINE);


        
    }

    /**
     * 
     */
    public final void keyPressed(final int keyCode) {
    	
		super.keyPressed(keyCode);
		this.dismiss();
		
	}

    /**
	 * 
	 */
	public final void pointerPressed(final int x, final int y) {
		
		super.pointerPressed(x, y);
		this.dismiss();
		
	}

    
	/**
	 * 
	 */
    private void dismiss()
    {

       	if (this.nextScreen == null) {    		
    		return;
    	}
    	
        if (!dismissed) {
        	
			dismissed = true;
			nextScreen.show();
			
		}
        
    }

    /**
	 * 
	 */
    public void showNotify() {
		
		if (!this.isLaunched) {
			
			this.isLaunched = true;
			
		}
    }
    
    
}
