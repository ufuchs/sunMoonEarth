package de.z35.sme;

import de.z35.posas.core.AngleFormatter;
import de.z35.posas.siteinfo.SiteInfo;
import de.z35.posas.siteinfo.SiteInfoService;
import javax.microedition.lcdui.Choice;
import javax.microedition.lcdui.ChoiceGroup;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Form;
import javax.microedition.lcdui.TextField;

/**
 * @author  ufuchs
 */
public class LocationCoordForm extends Form implements CommandListener{
	
//	private LocationList locationList;
	private SiteInfo siteInfo;

	private TextField tfName;
	private TextField tfLon;
	private TextField tfLat;
	private TextField tfTimeZone;
	private ChoiceGroup choices;
	
	/**
	 * 
	 * @param midlet
	 * @param locationList
	 * @param siteInfo
	 */
	public LocationCoordForm(SiteInfo siteInfo, int mode) {
		
		super("");
		
/*		
		switch (mode) {
		case LocationList.FM_ADD :
			title = "New location";
			break;
		case LocationList.FM_EDIT :
			title = "Edit location";
			break;
		default :
			title = "oOPs - No mode defined";
		}

		setTitle(title);
		
		this.locationList = locationList;
*/		
		this.siteInfo = siteInfo;
		
		initialize();
		
	}
	
	/**
	 * 
	 * 
	 */
	private void initialize() {
		
		tfName = new TextField("Name", 
				siteInfo.getName(), 
				50, TextField.ANY);

		tfLon = new TextField("Longitude",
				AngleFormatter.angle2Ddmm(siteInfo.getLon(),
				AngleFormatter.DELI_SPACE | AngleFormatter.HEMI_LON),
				50, TextField.ANY);

		tfLat = new TextField("Latitude",
				AngleFormatter.angle2Ddmm(siteInfo.getLat(),
				AngleFormatter.DELI_SPACE | AngleFormatter.HEMI_LAT),
				50, TextField.ANY);

		tfTimeZone = new TextField("Timezone", 
				siteInfo.getTzOffsAsString(),
				50, TextField.ANY);

		
		choices = new ChoiceGroup("Dayligth saving",
			    Choice.MULTIPLE);
		
		choices.append("On", null);

		append(tfName);		
		append(tfLat);
		append(tfLon);
		append(tfTimeZone);
		append(choices);
		
//		addCommand(Commands.OK_COMMAND);
//		addCommand(Commands.CANCEL_COMMAND);
//		setCommandListener(this);

	}

	/**
	 * @return
	 * @uml.property  name="siteInfo"
	 */
	public SiteInfo getSiteInfo() {
		return siteInfo;
	}
	
	////////////////////////////////////////////////////////////////////////////
	// Command handler methodes
	////////////////////////////////////////////////////////////////////////////
	
	/**
	 * 
	 */
	private void okCommand() {

		//  Name
		String newName = tfName.getString();
		if (!newName.equals(siteInfo.getName())) {
			siteInfo.setName(newName);
			siteInfo.setState(SiteInfo.CHANGED, true);
		}
		
		//  Longitude
		double newLon = SiteInfoService.convertInput2Degree(tfLon.getString());
		if (newLon != siteInfo.getLon()) {
			siteInfo.setLon(newLon);
			siteInfo.setState(SiteInfo.CHANGED, true);
		}

		//  Latitude
		double newLat = SiteInfoService.convertInput2Degree(tfLat.getString());
		if (newLat != siteInfo.getLat()) {
			siteInfo.setLat(newLat);
			siteInfo.setState(SiteInfo.CHANGED, true);
		}
		
		int newTimeZone = SiteInfoService.convertInput2TimeZone(tfTimeZone.getString());
		if (newTimeZone != siteInfo.getTzOffs()) {
			siteInfo.setTzOffs(newTimeZone);
			siteInfo.setState(SiteInfo.CHANGED, true);
		}
		
//		locationList.locationCoordFormBack(Commands.OK_COMMAND);
	}
	
	/**
	 * 
	 *
	 */
	private void cancelCommand() {
//		locationList.locationCoordFormBack(Commands.CANCEL_COMMAND);
	}
	
	////////////////////////////////////////////////////////////////////////////
	// Command handler
	////////////////////////////////////////////////////////////////////////////
	
	/**
	 * 
	 */
	public void commandAction(Command c, Displayable d) {
		
//		if (c == Commands.OK_COMMAND) {
//			okCommand();
//		} else if (c == Commands.CANCEL_COMMAND) {
//			cancelCommand();
//		}
		

	}


}
