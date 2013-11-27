package de.z35.sme;

import java.util.Vector;

import javax.microedition.lcdui.Font;

import de.z35.posas.core.AngleFormatter;
import de.z35.posas.peng.PosasEngine;
import de.z35.posas.siteinfo.SiteInfo;
import de.z35.posas.siteinfo.SiteInfoEngine;
//import de.z35.posas.siteinfo.SiteInfoEngine;
import de.z35.util.MathEx;

public class SunClockPresentation {

	PosasEngine peng;
	
	SiteInfoEngine sieng;
	
	private Vector siteInfoItems;
	
	private Vector extInfoItems;

	private StringBuffer sb;	
	
	private int siteInfoItemsHeight;
	
	private int extInfoItemsHeight;
	
	private Font headLineFont;
	
	private Font contentFont;
	
	
    ////////////////////////////////////////////////////////////////////////////
    // constructor
    ////////////////////////////////////////////////////////////////////////////
	
	/**
	 * 
	 *
	 */
	public SunClockPresentation(PosasEngine peng, SiteInfoEngine sieng, Font contentFont ) {
		
		super();
		
		this.peng = peng;
		
		this.sieng = sieng;
		
		this.contentFont = contentFont;
		
		initialize();
		
	}

	/**
	 * 
	 *
	 */
	private void initialize() {
		
		sb = new StringBuffer();
		
		siteInfoItems = new Vector();
		
		extInfoItems = new Vector();
		
	}

	////////////////////////////////////////////////////////////////////////////
	//  accessors
	////////////////////////////////////////////////////////////////////////////
	
	/**
	 * 
	 * @param index
	 * @return
	 */
	public String getSiteInfoItem(int index) {
		
		if (index > siteInfoItems.size())
			throw new IllegalArgumentException();
		
		return (String) siteInfoItems.elementAt(index);
		
	}
	
	/**
	 * 
	 * @return
	 */
	public int getSiteInfoItemsSize() {
		
		return siteInfoItems.size();
		
	}

	/**
	 * 
	 * @param index
	 * @return
	 */
	public String getExtInfoItem(int index) {
		
		if (index > extInfoItems.size())
			throw new IllegalArgumentException();
		
		return (String) extInfoItems.elementAt(index);
		
	}
	
	/**
	 * 
	 * @return
	 */
	public int getExtInfoItemsSize() {
		
		return extInfoItems.size();
		
	}

	/**
	 * 
	 * @return
	 */
	public int getSiteInfoItemsHeight() {
		
		return siteInfoItemsHeight;
		
	}
	
	/**
	 * 
	 * @return
	 */
	public int getExtInfoItemsHeight() { 
	
		return extInfoItemsHeight;
		
	}
	
	////////////////////////////////////////////////////////////////////////////
	//  mutators
	////////////////////////////////////////////////////////////////////////////
	
	/**
	 * 
	 */
	public void setHeadLineFont(Font headLineFont) {
		
		this.headLineFont = headLineFont;
		
	}
	
	/**
	 * 
	 *
	 */
	private void buildSiteInfoItems() {
		
		String content;
		
		SiteInfo si = sieng.getDefSiteInfo();
		
		siteInfoItems.setSize(0);

		// Name
		content = si.getName();
		siteInfoItems.addElement(content);

		// Longitude
		content = mergeItemString("     Lon", AngleFormatter.angle2Ddmm(si.getLon(), AngleFormatter.HEMI_LON));
		siteInfoItems.addElement(content);

		// Latitude
		content = mergeItemString("     Lat", AngleFormatter.angle2Ddmm(si
				.getLat(), AngleFormatter.HEMI_LAT));
		siteInfoItems.addElement(content);

		//  Timezone
//		content = mergeItemString("Timezone", si.getTzOffsAsString());
//		siteInfoItems.addElement(content);
		
		if (siteInfoItemsHeight == 0) {
			siteInfoItemsHeight = calcContentHeight(siteInfoItems,
					headLineFont, contentFont);
		}
		
	}

	
	/**
	 * 
	 * @return
	 */
	private void buildExtInfoItems() {

		String content;
		
		extInfoItems.setSize(0);		
		
		//	Right Ascension
		content = mergeItemString("RA", AngleFormatter.angle2Time(peng.getRaOfSun(), AngleFormatter.TIME_ANALOG));
		extInfoItems.addElement(content);
		
		//  Local Hour Angle
		double LHA = peng.getGmst0() + sieng.getDefSiteInfo().getLon() + peng.getRaOfSun();
		LHA = MathEx.normalizeAngle(LHA, 360.0);
		content = mergeItemString("LHA", AngleFormatter.angle2Time(LHA, AngleFormatter.TIME_ANALOG));
		extInfoItems.addElement(content);
		
		if (extInfoItemsHeight == 0) {
			extInfoItemsHeight = calcContentHeight(extInfoItems, null,
					contentFont);
		}
		
	}
	
	////////////////////////////////////////////////////////////////////////////
	//  helpers
	////////////////////////////////////////////////////////////////////////////
	
	/**
	 * 
	 * @param s
	 * @return
	 */
	private String mergeItemString(String label, String text) {
		
		sb.setLength(0);
		sb.append(label).append(" : ").append(text);
		return sb.toString();
		
	}

	/**
	 * 
	 * @return
	 */
	public int calcContentHeight(Vector content, Font headLineFont, Font contentFont) {
		
		int height = 0;
		int corr = 0;
		
		if (headLineFont != null) {
			height = headLineFont.getHeight();
			corr = 1;
		}
		
		height += (content.size() - corr) * contentFont.getHeight();  
		
		return height;
	}

	/**
	 * 
	 *
	 */
	public void update() {

		buildSiteInfoItems();
		buildExtInfoItems();
		
		
	}
	
}
