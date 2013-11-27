package de.z35.sme;

import de.z35.posas.peng.PosasEngine;
import de.z35.posas.siteinfo.SiteInfoEngine;
import de.z35.sme.prefs.SmePrefsService;
import de.z35.sme.splash.SplashScreen;
import de.z35.util.Commons;

import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.*;
import javax.microedition.midlet.MIDlet;
import javax.microedition.midlet.MIDletStateChangeException;
import org.j4me.ui.*;

/**
 * @author  ufuchs
 */
public final class SunMoonEarthMIDlet extends MIDlet implements ApplicationInitializer {

	// The first screen is a menu to choose among the example screens.
	BoxedMenuForm menu;
	
	/**
	 * 
	 */
	protected void startApp() throws MIDletStateChangeException {

		UIManager.init(this);

		final Display display = Display.getDisplay(this);

		if (display.getCurrent() == null) {

			display.setCurrent(new SplashScreen(Commons
					.createImage("/composite_earth1_red.png"), this));

		} else {

			menu.show();

		}

	}
	
	/**
	 * 
	 */
	protected void pauseApp() {}
	
	/**
	 * 
	 */
	protected void destroyApp(boolean arg0) {
		
		PosasEngine.getInstance().doFinialize();
		SiteInfoEngine.getInstance().doFinalize();
		SmePrefsService.getInstance().doFinalize();
		
		super.notifyDestroyed();
		
	}
	
    /*
     * (non-Javadoc)
     * @see org.j4me.ui.ApplicationInitializer#initApp()
     */
    public DeviceScreen initApp() {

    	////
    	// engines
    	////
    	
		try {
			
			////////////////////////////////////////////////////////////////
			// Preferences
			////////////////////////////////////////////////////////////////
			final SmePrefsService sps = SmePrefsService.getInstance();
	        sps.initialize();

	        ////////////////////////////////////////////////////////////////
	        // PosasEngine
	        ////////////////////////////////////////////////////////////////
			
			final PosasEngine peng = PosasEngine.getInstance();
			peng.initialize();
	        
	        ////////////////////////////////////////////////////////////////
	        // SiteInfoEngine
	        ////////////////////////////////////////////////////////////////
	        
			final SiteInfoEngine sieng = SiteInfoEngine.getInstance();
			sieng.initialize();

			sieng.attach(peng.getRiseAndSetSvc());
			// 'setDefSiteInfoIndex' aktualisiert gleichzeitig alle Observer
			// Letzte gewählte Location
			sieng.setDefSiteInfoIndex(sps.getSiteInfoIndex());
			
			////////////////////////////////////////////////////////////////
			// Preferences
			////////////////////////////////////////////////////////////////
			
			//  Ab nun Aktualisierung ermöglichen
			sps.setLockChanged(false);
			
		} catch (Exception e) {
			e.printStackTrace();
		}

    	////
    	// menu
    	////
		
		this.menu = new BoxedMenuForm("SunMoonEarth");		
		
		//  Test form
		Image img = Commons.createImage("/clock-48x48.png");
		
		this.menu.appendMenuItem(new LabelTest(this.menu), img);

		//  Textual form
		img = Commons.createImage("/weather-48x48.png");		
		
		menu.appendMenuItem(new TextualForm(menu), img);

		//  Location list
		img = Commons.createImage("/map-48x48.png");
		
		menu.appendMenuItem(new LocationForm(menu), img);

		//  Location list
		img = Commons.createImage("/map-48x48.png");
		
		menu.appendMenuItem(new LocationForm(menu), img);
		
		
		// Attach an exit option.
		img = Commons.createImage("/tools-48x48.png");		
		
		menu.appendMenuItem(new MenuItemInt() {
			
			public String getText() {
				return "Verlassen";
			}

			public void onSelection() {
		        destroyApp(false);
			}
			
		}, img);

		
		
		return menu;
    	
    }
    
}

