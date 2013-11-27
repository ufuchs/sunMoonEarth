package de.z35.sme;

import org.j4me.ui.DeviceScreen;
import org.j4me.ui.Dialog;

public class DialogTemplate extends Dialog {

	private DeviceScreen previous;
	
	/**
	 * 
	 */
	public DialogTemplate(DeviceScreen previous) {
		
		super();
		
		this.previous = previous;		

		// Set the title and menu for this screen.
		setTitle("Locations");
		setMenuText("Back", null);
		
		initialize();
		
	}
	
	/**
	 * 
	 */
	private void initialize() {
		
	}

	/**
	 * 
	 */
    public void showNotify() {
	}
	
	/**
	 * Takes the user to the previous screen.
	 */
	protected void declineNotify ()
	{
		previous.show();
	}
	
}
