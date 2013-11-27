package de.z35.sme;

import javax.microedition.lcdui.Font;
import javax.microedition.lcdui.Graphics;

import org.j4me.ui.BoxedDialog;
import org.j4me.ui.DeviceScreen;
import org.j4me.ui.DialogLayout;
import org.j4me.ui.UIManager;
import org.j4me.ui.components.BoxedComponent;
import org.j4me.ui.components.BoxedComposite;
import org.j4me.ui.components.BoxedLabelSimple;
import org.j4me.ui.components.BoxedPicture;

import de.z35.posas.peng.PosasEngine;
import de.z35.posas.peng.PosasEnginePresentation;
import de.z35.posas.siteinfo.SiteInfo;
import de.z35.posas.siteinfo.SiteInfoEngine;

public class LocationForm extends BoxedDialog {

	private static final int BEVEL = 5;
	private static final int FRAME_WIDTH = 1;
	
	/**
	 * The previous screen.
	 */
	private DeviceScreen previous;
	
	SiteInfoEngine sieng;	
	
	/**
	 * 
	 * @param previous
	 */
	LocationForm(final DeviceScreen previous) {

		super();

		this.previous = previous;
		
		super.setLo(new DialogLayout(this));

		// Set the title and menu for this screen.
		setTitle("Locations");
		setMenuText("Back", null);

	}

	/**
	 * 
	 *
	 */
	private void initialize() {
		
		sieng = SiteInfoEngine.getInstance();
		
		for (int i = 0; i < sieng.getSiteInfoCount(); i++) {

			LocationFormItem lcli = new LocationFormItem(sieng.getSiteInfo(i), i);
			lcli.setAcceptsInput(true);
			lcli.setBevel(BEVEL);

			append(lcli);
			
		}
		
	}

	/**
	 * 
	 */
    public void showNotify() {
    	initialize();
	}
    
	/**
	 * Takes the user to the previous screen.
	 */
	protected void declineNotify() {
		previous.show();
		super.lo.cl.setSize(0);
	}
	
}

////////////////////////////////////////////////////////////////////////////////

final class LocationFormItem extends BoxedComposite {

	private static final int PIC_SIZE = 40;
	
	private SiteInfo si;
	
	BoxedLabelSimple title;
	BoxedLabelSimple lblRise;
	BoxedLabelSimple lblSet;
	
	BoxedPicture picSunClock;
	
	BoxedComposite bcg;
	
    private PosasEngine peng;
    
    private SiteInfoEngine sieng;
    private PosasEnginePresentation uidpPeng;
	
	/**
	 * 
	 *
	 */
	public LocationFormItem(SiteInfo si, int i) {
		
		super();
		
		this.si = si;
		
		initialize(i);
		
	}
	
	/**
	 * 
	 *
	 */
	private void initialize(int i) {
		
		this.peng = PosasEngine.getInstance();
		
		this.sieng = SiteInfoEngine.getInstance();
		
		uidpPeng = new PosasEnginePresentation(peng);		
		
		sieng.setDefSiteInfoIndex(i);
		peng.updateSiteInfoObserver(si);
		peng.updateTimeBase();
		
		bcg = new BoxedComposite();
		bcg.setWidth(35, BoxedComponent.UNIT_PERC);
		bcg.setMarginTop(0);
		bcg.setBorderWidth(1);
		bcg.setBevel(0);
		bcg.setFloating(BoxedComponent.FLOAT_LEFT);
		bcg.setMarginRight(5);
		
		title = new BoxedLabelSimple(si.getName() + " - " + uidpPeng.getTimeOverHorizont());
		
		//  margin
		title.setMarginTop(0);
		title.setMarginLeft(2);
		title.setMarginRight(2);
		title.setMarginBottom(4);
		
		//  padding
		title.setFontColor(0x00FFFFFF);
		
		Font font = Font.getFont(Font.FACE_SYSTEM, Font.STYLE_PLAIN, Font.SIZE_MEDIUM);

		title.setFont(font);
		
		title.setHorizontalAlignment(Graphics.HCENTER);
		title.setBorderStyle(BoxedComponent.BORDERSTYLE_NONE);
		
		append(title, false);
		
		////
		// Rise 
		////
		
		lblRise = new BoxedLabelSimple("SA : " + uidpPeng.getRise());
		font = Font.getFont(Font.FACE_SYSTEM, Font.STYLE_PLAIN, Font.SIZE_SMALL);
		lblRise.setFont(font);
		lblRise.setMarginLeft(7);
		bcg.append(lblRise, false);
		
		////
		// Set
		////
		
		lblSet = new BoxedLabelSimple("SU :" + uidpPeng.getSet());
		font = Font.getFont(Font.FACE_SYSTEM, Font.STYLE_PLAIN, Font.SIZE_SMALL);
		lblSet.setFont(font);
		
		lblSet.setMarginLeft(7);
		
		bcg.append(lblSet, false);
		
		append(bcg, false);
		
		////
		// SunClock
		////
		
		picSunClock = new BoxedPicture();
		picSunClock.setWidth(48 + 3 + 1, 0);
		picSunClock.setBevel(3);
		picSunClock.setBorderWidth(1);
		picSunClock.setMarginLeft(5);

		picSunClock.show(true);
		
		SunClockImage sci = new SunClockImage(UIManager.getTheme().getBackgroundColor(), PIC_SIZE, PIC_SIZE);
		
		int rise = (int)(peng.getRise() * 360.0);
		int set = (int)(peng.getSet() * 360.0);
		
		sci.setParams(rise - 12, rise - 6, rise, set, set + 6, set + 12, peng.getState());
		
		picSunClock.setImage(sci.getImage());
		
		append(picSunClock, false);
		
	}
	
}

