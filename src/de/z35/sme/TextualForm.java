/* Copyright © 2006 Nokia. */
package de.z35.sme;

import org.j4me.ui.*;
import org.j4me.ui.components.BoxedComposite;
import org.j4me.ui.components.BoxedComponent;
import org.j4me.ui.components.BoxedLabelSimple;

import de.z35.posas.core.*;
import de.z35.posas.peng.PosasEngine;
import de.z35.posas.peng.PosasEnginePresentation;
import de.z35.posas.siteinfo.*;
import javax.microedition.lcdui.*;

/**
 * InstructionsScreen: Implements the instructions screen for a simple MIDlet game.
 */
final class TextualForm extends Dialog implements TimeBaseObserverInt {

	private static final int OFFS = 4;
	
	String LON = "Lon";
	String LAT = "Lat";
	String TIMEZONE = "Timezone";

	String RISE = "Rise";
	String SET = "Set";
	String TRANSIT = "Transit";
	String DAY_LENGTH = "Day length";

	String GMST = "Gmst";
	String JD2K = "J2000.0";
	String JD = "Julian date";
	
	String SUN = "Sun";
	String SUN_EPHEM = "Ephem of Sun";
	String APP_LON = "Ecl. Lon";
	String RADIUS_VEC = "Dist";
	String DECL = "Decl";
	String RA = "Rigth Asc";
//	String DELI_DC_NL = " : \n";
	String DELI_DC = " : ";	
	
	/**
	 * The previous screen.
	 */
	private DeviceScreen previous;

	private PosasEngine peng;
	private SiteInfoEngine sieng;
    private PosasEnginePresentation uidp;
//    private SiengUIDataProvider suidp;

    //  SiteInfo
    private BoxedLabelSimple lblSiteInfoName;
    private BoxedLabelSimple lblSiteInfoLon;
    private BoxedLabelSimple lblSiteInfoLat;
    private BoxedLabelSimple lblSiteInfoTimeZone;
    
    private BoxedLabelSimple lblSun;
    
    private BoxedLabelSimple lblSunRise;
    private BoxedLabelSimple lblSunTransit;
    private BoxedLabelSimple lblSunSet;    
    private BoxedLabelSimple lblSunTimeOverHorizont;
    
    //  Date and time
    private BoxedLabelSimple lblDateTimeTitle;    
    private BoxedLabelSimple lblDate;
//    private BoxedLabelSimple lblJd;
    private BoxedLabelSimple lblJdk2k0;
    private BoxedLabelSimple lblGmst;
    
    private BoxedLabelSimple lblSunEphem;
    private BoxedLabelSimple lblAppLon;
    private BoxedLabelSimple lblRadiusVector;
    private BoxedLabelSimple lblRa;
    private BoxedLabelSimple lblDecl;
    
    private LocationComposite loc;    
    private BoxedComposite time;
    private BoxedComposite ephem;
    
    private StringBuffer sb = new StringBuffer();
    
    private int currSiteInfoIndex;

    /**
     * 
     * @param midlet
     */
    TextualForm(DeviceScreen previous)
    {
    	
        super();
        
        this.previous = previous;
        
		// Set the title and menu for this screen.
		setTitle("Textual");
		setMenuText("Back", null);
		
        initialize();

    }

    /**
     * 
     * @param fst
     * @param snd
     * @param thd
     * @return
     */
    String mergeItem(String fst, String snd, String thd) {
    	
    	sb.setLength(0);
    	
    	sb.append(fst).append(snd);
    	
    	if (thd != null) {
    		sb.append(thd);
    	}
    	
    	return sb.toString();
    }
    
    /**
     * 
     *
     */
    private void initialize() {

    	Font lblFont = Font.getFont(Font.FACE_SYSTEM, Font.STYLE_PLAIN, Font.SIZE_MEDIUM);
    	
    	peng = PosasEngine.getInstance();
    	
    	sieng = SiteInfoEngine.getInstance();

    	currSiteInfoIndex = sieng.getDefSiteInfoIndex();
    	
    	uidp = new PosasEnginePresentation(peng) ;
    	
//    	suidp = new SiengUIDataProvider(sieng);

    	SiteInfo si = sieng.getDefSiteInfo();    	
    	
    	////////////////////////////////////////////////////////////////////////
    	//  siteInfo
    	////////////////////////////////////////////////////////////////////////

    	loc = new LocationComposite();
    	loc.setAcceptsInput(true);
		loc.setBevel(4);
		loc.setBorderWidth(1);
  
    	time = new BoxedComposite();
    	time.setBevel(4);
    	time.setBorderWidth(1);		
    	
    	ephem = new BoxedComposite();
    	ephem.setBevel(4);
    	ephem.setBorderWidth(1);		
		
    	Theme x = UIManager.getTheme();
    	
    	//  Name
    	lblSiteInfoName = new BoxedLabelSimple(si.getName());
    	lblSiteInfoName.setFontColor(x.getMenuFontColor());
    	lblSiteInfoName.setHorizontalAlignment(Graphics.HCENTER); 
    	lblSiteInfoName.setFont(lblFont);
    	lblSiteInfoName.setBorderStyle(BoxedComponent.BORDERSTYLE_NONE);
    	lblSiteInfoName.setMarginLeft(1);
    	lblSiteInfoName.setMarginRight(1);
    	
    	//  Lon
    	lblSiteInfoLon = new BoxedLabelSimple(mergeItem(LON, DELI_DC, AngleFormatter
				.angle2Ddmm(si.getLon(), AngleFormatter.HEMI_LON)));
    	lblSiteInfoLon.setFont(lblFont);
//    	lblSiteInfoLon.setWidth(100, BoxedComponent.UNIT_PERC);
    	lblSiteInfoLon.setMarginLeft(OFFS * 3);
    	lblSiteInfoLon.setMarginRight(OFFS * 3);
    	
    	
    	//  Lat
    	lblSiteInfoLat = new BoxedLabelSimple(mergeItem(LAT, DELI_DC, AngleFormatter
				.angle2Ddmm(si.getLat(), AngleFormatter.HEMI_LAT)));
    	lblSiteInfoLat.setFont(lblFont);    	
//    	lblSiteInfoLat.setWidth(, BoxedComponent.UNIT_PERC);
    	lblSiteInfoLat.setMarginLeft(OFFS * 3);
    	lblSiteInfoLat.setMarginRight(OFFS * 3);
    	
    	//  Timezone
    	lblSiteInfoTimeZone = new BoxedLabelSimple(mergeItem(TIMEZONE, DELI_DC, si
				.getTzOffsAsString()));  
    	lblSiteInfoTimeZone.setFont(lblFont);
//    	lblSiteInfoTimeZone.setWidth(50, BoxedComponent.UNIT_PERC);
    	lblSiteInfoTimeZone.setMarginLeft(OFFS * 3);
    	lblSiteInfoTimeZone.setMarginRight(OFFS * 3);

    	
    	//  Title column
    	lblSun = new BoxedLabelSimple(SUN);
    	lblSun.setHorizontalAlignment(Graphics.HCENTER);
    	lblSun.setFontColor(x.getMenuFontColor());
//    	lblSun.setFontColor(0x00707070);    	
    	lblSun.setWidth(100, BoxedComponent.UNIT_PERC);
    	lblSun.setBorderStyle(BoxedComponent.BORDERSTYLE_NONE);
    	lblSun.setMarginLeft(OFFS * 2);
    	lblSun.setMarginRight(OFFS * 2);
    	
    	//  Sun rise
    	lblSunRise = new BoxedLabelSimple(mergeItem(RISE, DELI_DC, null));
    	lblSunRise.setWidth(50, BoxedComponent.UNIT_PERC);
    	lblSunRise.setMarginLeft(OFFS * 3);
    	lblSunRise.setMarginRight(OFFS * 3);
    	
    	//  Sun set
    	lblSunSet = new BoxedLabelSimple(mergeItem(SET, DELI_DC, null));
    	lblSunSet.setWidth(50, BoxedComponent.UNIT_PERC);
    	lblSunSet.setMarginLeft(OFFS * 3);
    	lblSunSet.setMarginRight(OFFS * 3);

    	//  Transit of Sun
    	lblSunTransit = new BoxedLabelSimple(mergeItem(TRANSIT, DELI_DC, null));
    	lblSunTransit.setWidth(50, BoxedComponent.UNIT_PERC);
    	lblSunTransit.setMarginLeft(OFFS * 3);
    	lblSunTransit.setMarginRight(OFFS * 3);
    	
    	//  Day lenght
    	lblSunTimeOverHorizont = new BoxedLabelSimple(mergeItem(DAY_LENGTH, DELI_DC, null));
    	lblSunTimeOverHorizont.setWidth(50, BoxedComponent.UNIT_PERC);
    	lblSunTimeOverHorizont.setMarginLeft(OFFS * 3);
    	lblSunTimeOverHorizont.setMarginRight(OFFS * 3);
    	
    	////////////////////////////////////////////////////////////////////////
    	//  Date and time
    	////////////////////////////////////////////////////////////////////////
    	
    	//  Title
    	lblDateTimeTitle = new BoxedLabelSimple("Date and Time");
    	lblDateTimeTitle.setHorizontalAlignment(Graphics.HCENTER);
    	lblDateTimeTitle.setFontColor(x.getMenuFontColor());
    	lblDateTimeTitle.setFont(lblFont);
    	lblDateTimeTitle.setBorderStyle(BoxedComponent.BORDERSTYLE_NONE);
    	lblDateTimeTitle.setMarginLeft(1);
    	lblDateTimeTitle.setMarginRight(1);
       
    	//  Days since Epoche 2000.0
        lblJdk2k0 = new BoxedLabelSimple(mergeItem(JD2K, DELI_DC, null));
        lblJdk2k0.setMarginLeft(OFFS * 3);
        lblJdk2k0.setMarginRight(OFFS * 3);
    	
    	//  Siderial time at Greenwich
    	lblGmst = new BoxedLabelSimple(mergeItem(GMST, DELI_DC, null));
    	lblGmst.setMarginLeft(OFFS * 3);
    	lblGmst.setMarginRight(OFFS * 3);
    	
    	
    	////////////////////////////////////////////////////////////////////////
    	//  Ephem of Sun
    	////////////////////////////////////////////////////////////////////////
    	
    	// Title
        lblSunEphem = new BoxedLabelSimple(SUN_EPHEM);
        lblSunEphem.setHorizontalAlignment(Graphics.HCENTER);
        lblSunEphem.setFontColor(x.getMenuFontColor());
        lblSunEphem.setFont(lblFont);
        lblSunEphem.setBorderStyle(BoxedComponent.BORDERSTYLE_NONE);
        lblSunEphem.setMarginLeft(1);
        lblSunEphem.setMarginRight(1);
        
        //  Apparent lon of Sun
        lblAppLon = new BoxedLabelSimple(mergeItem(APP_LON, DELI_DC, null));
        lblAppLon.setMarginLeft(OFFS * 3);
        lblAppLon.setMarginRight(OFFS * 3);
        
        //  Radius vector of Sun
        lblRadiusVector = new BoxedLabelSimple(mergeItem(RADIUS_VEC, DELI_DC, null));
        lblRadiusVector.setMarginLeft(OFFS * 3);
        lblRadiusVector.setMarginRight(OFFS * 3);
        
        //  Rigth Ascension of Sun
        lblRa = new BoxedLabelSimple(mergeItem(RA, DELI_DC, null));
        lblRa.setMarginLeft(OFFS * 3);
        lblRa.setMarginRight(OFFS * 3);
        
        //  Declination of Sun
        lblDecl = new BoxedLabelSimple(mergeItem(DECL, DELI_DC, null));
        lblDecl.setMarginLeft(OFFS * 3);
        lblDecl.setMarginRight(OFFS * 3);

    	loc.append(lblSiteInfoName, false);
    	loc.append(lblSiteInfoLat, false);
    	loc.append(lblSiteInfoLon, false);
    	loc.append(lblSiteInfoTimeZone, false);
    	
    	loc.append(lblSun, false);
    	loc.append(lblSunRise, false);
    	loc.append(lblSunSet, false);
    	loc.append(lblSunTransit, false);
    	loc.append(lblSunTimeOverHorizont, false);
        
        append(loc);
        
    	time.append(lblDateTimeTitle, false);
//    	time.append(lblDate);
//    	append(lblJd);
    	time.append(lblJdk2k0, false);
    	time.append(lblGmst, false);
    	
    	append(time);
    	
    	ephem.append(lblSunEphem, false);
    	ephem.append(lblAppLon, false);
    	ephem.append(lblRadiusVector, false);
    	ephem.append(lblRa, false);
    	ephem.append(lblDecl, false);
    	
    	append(ephem);
    	
    }
    
    /**
     * 
     *
     */
    public void updateTimeBaseObservers() {
    	
//    	System.out.println("TextualForm.updateTimeBaseObservers()");

    	int siteInfoIndex = sieng.getDefSiteInfoIndex();

    	////////////////////////////////////////////////////////////////////////
    	//  SiteInfo
    	////////////////////////////////////////////////////////////////////////
    	
		if (siteInfoIndex != currSiteInfoIndex) {    	
			
			currSiteInfoIndex = siteInfoIndex; 
			
	    	SiteInfo si = sieng.getDefSiteInfo(); 
	    	
	    	lblSiteInfoName.setLabel(si.getName());
	
	    	lblSiteInfoLat.setLabel(mergeItem(LAT, DELI_DC, AngleFormatter
					.angle2Ddmm(si.getLat(), AngleFormatter.HEMI_LAT)));

			lblSiteInfoLon.setLabel(mergeItem(LON, DELI_DC, AngleFormatter
					.angle2Ddmm(si.getLon(), AngleFormatter.HEMI_LON)));

			lblSiteInfoTimeZone.setLabel(mergeItem(TIMEZONE, DELI_DC, si
					.getTzOffsAsString()));

		}
		
    	//  Sun rise
    	lblSunRise.setLabel(mergeItem(RISE, DELI_DC, uidp.getRise()));
    	
    	// Sun set
    	lblSunSet.setLabel(mergeItem(SET, DELI_DC, uidp.getSet()));
    	
    	//  Sun transit
    	lblSunTransit.setLabel(mergeItem(TRANSIT, DELI_DC, uidp.getTransit()));
    	
    	//  Day length
    	lblSunTimeOverHorizont.setLabel(mergeItem(DAY_LENGTH, DELI_DC, uidp.getTimeOverHorizont()));
		
    	////////////////////////////////////////////////////////////////////////
    	//  Date and time
    	////////////////////////////////////////////////////////////////////////
    	
//    	sb.setLength(0);
//    	sb.append("Date").append(DELI_DC).append(uidp.getDateTime());
//    	lblDate.setLabel(sb.toString());

//    	sb.setLength(0);
//    	sb.append(JD).append(" : \r").append(uidp.getJd());
//    	lblJd.setLabel(sb.toString());
    	
    	sb.setLength(0);
    	sb.append(JD2K).append(DELI_DC).append(uidp.getJd2k());
    	lblJdk2k0.setLabel(sb.toString());
    	
    	//  Siderial time at Greenwich
    	lblGmst = new BoxedLabelSimple();
    	sb.setLength(0);
    	sb.append(GMST).append(DELI_DC).append(uidp.getGmst());
    	lblGmst.setLabel(sb.toString());

    	////////////////////////////////////////////////////////////////////////
    	//  Sun
    	////////////////////////////////////////////////////////////////////////
    	
        // Apparent lon of Sun
		lblAppLon.setLabel(mergeItem(APP_LON, DELI_DC, uidp
				.getTrueLonOfSun()));

		// Radius vector of Sun
		lblRadiusVector.setLabel(mergeItem(RADIUS_VEC, DELI_DC, uidp
				.getRadiusVectorOfSun()));
		
		// Rigth Ascension of Sun
		lblRa.setLabel(mergeItem(RA, DELI_DC, uidp.getRaOfSun(2)));

		// Declination of Sun
		lblDecl.setLabel(mergeItem(DECL, DELI_DC, uidp.getDeclOfSun()));

//    	repaint();    	
    	
    }
    
    public void showNotify() {
    	
//    	System.out.println("acceptNotify()");
    	
    	super.setSelected(0);
    	
    	loc.setState(BoxedComponent.SELECTED);
    	
        synchronized (this) {
			peng.addRollerClient((TimeBaseObserverInt)this);
        }

		peng.updateTimeBase();        
        
	}
    
	/**
	 * Takes the user to the previous screen.
	 */
	protected void declineNotify ()
	{
		
//		System.out.println("declineNotify");
		
        synchronized (this) {
			peng.removeRollerClient((TimeBaseObserverInt)this);
        }

		previous.show();
	}
    
    
    
}
