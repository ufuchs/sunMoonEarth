/**
 * 
 */
package de.z35.sme;

import org.j4me.ui.DeviceScreen;
import org.j4me.ui.components.BoxedComposite;
import de.z35.posas.peng.PosasEngine;
import de.z35.posas.siteinfo.SiteInfoEngine;

/**
 * @author Uli Fuchs
 *
 */
public class LocationComposite extends BoxedComposite {

    private int defSiteInfoIndex;
    private int siteInfoCount;

    private SiteInfoEngine sieng;    
	
	/**
	 * 
	 */
	public LocationComposite() {
		
		super();
		
		initialize();
		
	}

	/**
	 * 
	 *
	 */
	private void initialize() {
	
		this.sieng = SiteInfoEngine.getInstance();		
		
        siteInfoCount = sieng.getSiteInfoCount() - 1;
        defSiteInfoIndex = sieng.getDefSiteInfoIndex();
		
	}
	
	/**
	 * 
	 */
	protected void handleKeys(final int keyCode) {
		
		super.handleKeys(keyCode);
		
        switch (keyCode) {

        //  Nach UNTEN
        case DeviceScreen.DOWN:
        	
            if (defSiteInfoIndex < siteInfoCount) {
            	defSiteInfoIndex++;
            } else {
            	defSiteInfoIndex = 0;
            }
            break;

        //  Nach OBEN    
        case DeviceScreen.UP:
     	
            if (defSiteInfoIndex > 0) {
            	defSiteInfoIndex--;
            } else {
            	defSiteInfoIndex = sieng.getSiteInfoCount() - 1;
            }
            break;

        }
        
        if (keyCode == DeviceScreen.UP || keyCode == DeviceScreen.DOWN) {
        	//  Neue SiteInfo speichern
        	sieng.setDefSiteInfoIndex(defSiteInfoIndex);
        	PosasEngine.getInstance().updateTimeBase();
        	repaint();
        }
		
	}
	
}
