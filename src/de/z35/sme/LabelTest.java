package de.z35.sme;

import java.io.IOException;

import javax.microedition.lcdui.Graphics;

import org.j4me.ui.DeviceScreen;
import org.j4me.ui.Dialog;
import org.j4me.ui.components.BoxedComponent;
import org.j4me.ui.components.BoxedLabelSimple;
import org.j4me.ui.components.BoxedComposite;
import org.j4me.ui.components.BoxedPicture;

public class LabelTest extends Dialog {

	private static final int MARGIN_LEFT = 4;
	
	private static final String TEXT = "Ny-Alesund";
	
	private DeviceScreen previous;
	
	private BoxedLabelSimple lblLocName;
	private BoxedLabelSimple lblLocLon;
	private BoxedLabelSimple lblLocLat;
	private BoxedLabelSimple lblSun;
	private BoxedLabelSimple lblSunRise;	
	private BoxedLabelSimple lblSunSet;	

	private BoxedLabelSimple lblSunEphem;	
	private BoxedLabelSimple lblSunEclLon;	
	private BoxedLabelSimple lblSunDist;
	private BoxedLabelSimple lblSunRightAsc;
	
	private BoxedPicture picTest;

	
	private BoxedComposite bcg1;
	private BoxedComposite bcg2;
	
	/**
	 * 
	 */
	public LabelTest(DeviceScreen previous) {
		
		super();
		
		this.previous = previous;		

		// Set the title and menu for this screen.
		setTitle("LabelTest");
		setMenuText("Back", null);
		
		initialize();
		
	}
	
	/**
	 * 
	 */
	private void initialize() {
		
		picTest = new BoxedPicture();
		picTest.setWidth(48 + 3 + 1, 0);
		picTest.setBevel(3);
		picTest.setBorderWidth(1);
		picTest.setMarginLeft(5);
		picTest.setMarginBottom(5);
		
		picTest.setHorizontalAlignment(Graphics.LEFT);
		try {
			picTest.setImage("/desktop2.png");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		////
		// lblLocName
		////
		
		lblLocName = new BoxedLabelSimple("Berlin");

//		lblLocName.setBorderStyle(BoxedComponent.BORDERSTYLE_NONE);
		lblLocName.setBevel(4);
		lblLocName.setBorderWidth(1);
		lblLocName.setHorizontalAlignment(Graphics.LEFT);
		lblLocName.setWidth(400, 0);
		
		//  margin
		lblLocName.setMargin(0);		
		lblLocName.setMarginTop(0);
		lblLocName.setMarginLeft(1 + MARGIN_LEFT);
		lblLocName.setMarginRight(1 + MARGIN_LEFT);
		lblLocName.setMarginBottom(0);
		
		//  padding
//		lblLocName.setPaddingTop(-3);
//		lblLocName.setPaddingBottom(-3);
		
		lblLocName.setPaddingLeft(0);
		lblLocName.setPaddingRight(1);
		
		////
		// lblLocLon
		////
		
		lblLocLon = new BoxedLabelSimple("Lon : 13° 28.2'");

		lblLocLon.setBevel(0);
		lblLocLon.setBorderWidth(0);
		lblLocLon.setHorizontalAlignment(Graphics.LEFT);
		lblLocLon.setWidth(50, BoxedComponent.UNIT_PERC);
		
		//  margin
		lblLocLon.setMarginTop(0);
		lblLocLon.setMarginLeft(3 * MARGIN_LEFT);
		lblLocLon.setMarginRight(0);
		lblLocLon.setMarginBottom(0);
		
		//  padding
		lblLocLon.setPadding(0);
		lblLocLon.setPaddingLeft(0);
		lblLocLon.setPaddingRight(0);
		lblLocLon.setPaddingTop(0);
		lblLocLon.setPaddingBottom(0);

		////
		// lblLocLat
		////
		
		lblLocLat = new BoxedLabelSimple("Lat : 52° 30.0'");

		lblLocLat.setBevel(0);
		lblLocLat.setBorderWidth(0);
		lblLocLat.setHorizontalAlignment(Graphics.LEFT);
		lblLocLat.setWidth(50, BoxedComponent.UNIT_PERC);
		
		//  margin
		lblLocLat.setMarginTop(0);
		lblLocLat.setMarginLeft(3 * MARGIN_LEFT);
		lblLocLat.setMarginRight(0);
		lblLocLat.setMarginBottom(0);
		
		//  padding
		lblLocLat.setPadding(0);
		lblLocLat.setPaddingLeft(0);
		lblLocLat.setPaddingRight(0);
		
		////
		// lblSun
		////

		lblSun = new BoxedLabelSimple("Sun");

		lblSun.setBorderStyle(BoxedComponent.BORDERSTYLE_NONE);

		//  bevel
		lblSun.setBevel(3);
		
		//  border
		lblSun.setBorderWidth(0);
		
		lblSun.setHorizontalAlignment(Graphics.HCENTER);

		lblSun.setWidth(100, BoxedComponent.UNIT_PERC);		
		
		//  margin
		lblSun.setMarginTop(0);
		lblSun.setMarginLeft(2 * MARGIN_LEFT);
		lblSun.setMarginRight(2 * MARGIN_LEFT);
		lblSun.setMarginBottom(0);
		
		//  padding
		lblSun.setPadding(0);
		lblSun.setPaddingLeft(0);
		lblSun.setPaddingRight(0);
		lblSun.setPaddingTop(-3);
		lblSun.setPaddingBottom(-3);
		
		////
		// Label14
		////
		
		lblSunEclLon = new BoxedLabelSimple("SA : 07.30");

		//  bevel
		lblSunEclLon.setBevel(0);
		
		//  border
		lblSunEclLon.setBorderWidth(0);
		
		lblSunEclLon.setHorizontalAlignment(Graphics.LEFT);

		lblSunEclLon.setWidth(50, BoxedComponent.UNIT_PERC);		
		
		//  margin
		lblSunEclLon.setMarginTop(0);
		lblSunEclLon.setMarginLeft(3 * MARGIN_LEFT );
		lblSunEclLon.setMarginRight(0);
		lblSunEclLon.setMarginBottom(0);
		
		//  padding
		lblSunEclLon.setPadding(0);
		lblSunEclLon.setPaddingLeft(0);
		lblSunEclLon.setPaddingRight(0);
		
		////
		// Label15
		////
		
		lblSunDist = new BoxedLabelSimple("SU : 17.30");

		//  bevel
		lblSunDist.setBevel(0);
		
		//  border
		lblSunDist.setBorderWidth(0);
		
		lblSunDist.setHorizontalAlignment(Graphics.LEFT);

		lblSunDist.setWidth(50, BoxedComponent.UNIT_PERC);		
		
		//  margin
		lblSunDist.setMarginTop(0);
		lblSunDist.setMarginLeft(3 * MARGIN_LEFT);
		lblSunDist.setMarginRight(0);
		lblSunDist.setMarginBottom(0);
		
		//  padding
		lblSunDist.setPadding(0);
		lblSunDist.setPaddingLeft(0);
		lblSunDist.setPaddingRight(0);
		
		////
		// lblSunRightAsc
		////
		
		lblSunRightAsc = new BoxedLabelSimple(TEXT + "-16");

		//  bevel
		lblSunRightAsc.setBevel(4);
		
		//  border
		lblSunRightAsc.setBorderWidth(-1);
		
		lblSunRightAsc.setBorderStyle(-1);
		
		lblSunRightAsc.setHorizontalAlignment(Graphics.LEFT);
		
		lblSunRightAsc.setWidth(50, BoxedComponent.UNIT_PERC);		
		
		//  margin
		lblSunRightAsc.setMarginTop(0);
		lblSunRightAsc.setMarginLeft(0);
		lblSunRightAsc.setMarginRight(0);
		lblSunRightAsc.setMarginBottom(0);
		
		//  padding
		lblSunRightAsc.setPadding(0);
		lblSunRightAsc.setPaddingLeft(0);
		lblSunRightAsc.setPaddingRight(0);

		////
		// lblSunRightAsc
		////

		
		
		lblSunEphem = new BoxedLabelSimple(TEXT + "-16");

		lblSunEphem.setBorderStyle(BoxedComponent.BORDERSTYLE_NONE);		
		
		//  bevel
		lblSunEphem.setBevel(0);
		
		//  border
		lblSunEphem.setBorderWidth(0);
		
//		lblSunEphem.setBorderStyle(0);
		
		lblSunEphem.setHorizontalAlignment(Graphics.HCENTER);
		
		lblSunEphem.setWidth(400, 0);		
		
		//  margin
		lblSunEphem.setMarginTop(0);
		lblSunEphem.setMarginBottom(0);
		
		lblSunEphem.setMarginLeft(1 + MARGIN_LEFT);
		lblSunEphem.setMarginRight(1 + MARGIN_LEFT);
		
		
		////////////////////////////////////////////////////////////////////////
		
		bcg1 = new BoxedComposite();
		
		bcg1.setState(BoxedComponent.SELECTED);
		
		bcg1.setAcceptsInput(true);

		bcg1.setWidth(300, 0);
		bcg1.setBorderWidth(1);
		bcg1.setBevel(4);
		bcg1.setPadding(0);
//		bcg.setPaddingLeft(6);
//		bcg.setPaddingRight(6);
		bcg1.setPaddingBottom(0);
		
		bcg1.setMarginLeft(0);
		bcg1.setMarginRight(0);
		
		bcg1.append(picTest, false);
		bcg1.append(lblLocName, false);
//		bcg1.append(lblLocLon);
//		bcg1.append(lblLocLat);
//		bcg1.append(lblSun);
////		bcg1.append(lblSunEclLon);
//		bcg1.append(lblSunDist);
		
		
		append(bcg1);

//		append(new HorizontalRule());		
		
/*
		bcg2 = new BoxedComponentGroup();

		bcg2.setAcceptsInput(true);		
		
		bcg2.setWidth(100, BoxedComponent.UNIT_PERC);
		bcg2.setBorderWidth(0);
		bcg2.setBevel(0);
		bcg2.setPadding(0);
//		bcg.setPaddingLeft(6);
//		bcg.setPaddingRight(6);
		bcg2.setPaddingBottom(2);
		
		bcg2.setMarginLeft(0);
		bcg2.setMarginRight(0);
		
		bcg2.append(lblSunEphem);
		bcg2.append(lblSunEclLon);
		
		append(bcg2);
*/		
		
	}
	
	/**
	 * 
	 */
    public void showNotify() {
        	
//        	System.out.println("acceptNotify()");
    	
    	super.setSelected(0);
    	
    	bcg1.setState(BoxedComponent.SELECTED);
        	
	}
	
	/**
	 * Takes the user to the previous screen.
	 */
	protected void declineNotify ()
	{
		previous.show();
	}
	
}
