package de.z35.sme.prefs;

/**
 * @author  ufuchs
 */
public class SmePrefsService {

	public static final SmePrefsService sps = new SmePrefsService();
	
	private SmePrefs prefs;

    ////////////////////////////////////////////////////////////////////////////
    // constructor
    ////////////////////////////////////////////////////////////////////////////
	
	/**
	 * 
	 *
	 */
	private SmePrefsService() {
		
		super();
		
	}
	
	/**
	 * 
	 * @return
	 */
	public static SmePrefsService getInstance() {
		
		return sps;
		
	}
	
	/**
	 * 
	 *
	 */
	public void initialize() {

		retrieveAll();
		
	}

	/**
	 * 
	 *
	 */
	public void doFinalize() {

		if (prefs.hasChanged()) {
			updateAll();
		}	

	}
	
    ////////////////////////////////////////////////////////////////////////////
    // accessors
    ////////////////////////////////////////////////////////////////////////////
	
	/**
	 * 
	 * @return
	 */
	public int getSiteInfoIndex() {
		
		return prefs.getSiteInfoIndex();
		
	}

	
	
    ////////////////////////////////////////////////////////////////////////////
    // mutators
    ////////////////////////////////////////////////////////////////////////////
	
	/**
	 * 
	 * @return
	 */
	public void setSiteInfoIndex(int value) {
		
		prefs.setSiteInfoIndex(value);
		
	}

	/**
	 * 
	 * @return
	 */
	public void setLockChanged(boolean value) {
		
		prefs.setLockChanged(value);
		
	}
	
	
    ////////////////////////////////////////////////////////////////////////////
    // production
    ////////////////////////////////////////////////////////////////////////////
	
	/**
	 * 
	 *
	 */
	private void retrieveAll() {
		
		try {
			prefs = new SmePrefsDaoXml().retrieveAll();
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * 
	 *
	 */
	public void updateAll() {
		
		new SmePrefsDaoXml().updateAll(prefs);
		
	}
}
