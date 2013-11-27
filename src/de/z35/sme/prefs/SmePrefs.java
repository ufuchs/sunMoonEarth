package de.z35.sme.prefs;

/**
 * @author  Uli Fuchs
 */
public class SmePrefs {
	
	private int siteInfoIndex; 
	private String siteInfoName;
	private boolean changed;
	private boolean lockChanged;
	
    ////////////////////////////////////////////////////////////////////////////
    // constructor
    ////////////////////////////////////////////////////////////////////////////

	/**
	 * 
	 */
	public SmePrefs() {
		
		super();
		
		lockChanged = true;
		
	}
	
    ////////////////////////////////////////////////////////////////////////////
    // accessors
    ////////////////////////////////////////////////////////////////////////////

	/**
	 * @return  the siteInfoIndex
	 * @uml.property  name="siteInfoIndex"
	 */
	public final int getSiteInfoIndex() {
		return siteInfoIndex;
	}

	/**
	 * @return  the siteInfoName
	 * @uml.property  name="siteInfoName"
	 */
	public final String getSiteInfoName() {
		return siteInfoName;
	}
	
	/**
	 * 
	 * @return
	 */
	public final boolean hasChanged() {
		return changed;
	}
	
    ////////////////////////////////////////////////////////////////////////////
    // mutators
    ////////////////////////////////////////////////////////////////////////////

	/**
	 * @param siteInfoIndex  the siteInfoIndex to set
	 * @uml.property  name="siteInfoIndex"
	 */
	public final void setSiteInfoIndex(int siteInfoIndex) {
		this.siteInfoIndex = siteInfoIndex;
		setChanged(true);
	}
	
	/**
	 * @param siteInfoName  the siteInfoName to set
	 * @uml.property  name="siteInfoName"
	 */
	public final void setSiteInfoName(String siteInfoName) {
		this.siteInfoName = siteInfoName;
		setChanged(true);
	}

	/**
	 * @param  changed
	 * @uml.property  name="changed"
	 */
	public final void setChanged(boolean changed) {
		
		if (!lockChanged) {
			this.changed = changed;
		}
		
	}

	/**
	 * @param  changed
	 * @uml.property  name="lockChanged"
	 */
	public final void setLockChanged(boolean value) {
		this.lockChanged = value;
	}
	
	
}
