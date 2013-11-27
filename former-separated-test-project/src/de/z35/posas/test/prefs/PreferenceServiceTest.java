package de.z35.posas.test.prefs;

import j2meunit.framework.Test;
import j2meunit.framework.TestCase;
import j2meunit.framework.TestMethod;
import j2meunit.framework.TestSuite;
import de.z35.posas.test.siteinfo.SiteInfoServiceTestCase;
import de.z35.posas.test.util.J4METestCase;

import de.z35.posas.prefs.*;
import de.z35.posas.test.siteinfo.*;

public class PreferenceServiceTest extends J4METestCase {

	////////////////////////////////////////////////////////////////////////////
	//  constructors
	////////////////////////////////////////////////////////////////////////////
	
	/**
	 * 
	 * @param arg0
	 * @param arg1
	 */
	public PreferenceServiceTest(String arg0, TestMethod arg1) {
		super(arg0, arg1);
	}
	
	/**
	 * 
	 *
	 */
	public PreferenceServiceTest() {
		
		super();
		
	}
	
	////////////////////////////////////////////////////////////////////////////
	//  suite
	////////////////////////////////////////////////////////////////////////////
	
	public Test suite() {

		TestSuite ts = new TestSuite();

		// testCtor_1()
		ts.addTest(new PreferenceServiceTest("testCtor_1()", new TestMethod() {
			public void run(TestCase tc) {
				((PreferenceServiceTest) tc).testCtor_1();
			}
		}));

		// testCtor_2()
		ts.addTest(new PreferenceServiceTest("testCtor_2()", new TestMethod() {
			public void run(TestCase tc) {
				((PreferenceServiceTest) tc).testCtor_2();
			}
		}));
		
		// testCtor_2()
		ts.addTest(new PreferenceServiceTest("testCtor_2()", new TestMethod() {
			public void run(TestCase tc) {
				((PreferenceServiceTest) tc).testCtor_2();
			}
		}));

		// testInitialize()
		ts.addTest(new PreferenceServiceTest("testInitialize()", new TestMethod() {
			public void run(TestCase tc) {
				((PreferenceServiceTest) tc).testInitialize();
			}
		}));
		
		
		// testDoFinalize()
		ts.addTest(new PreferenceServiceTest("testDoFinalize()", new TestMethod() {
			public void run(TestCase tc) {
				((PreferenceServiceTest) tc).testDoFinalize();
			}
		}));

		
		return ts;
		
	}
	
	////////////////////////////////////////////////////////////////////////////
	//  test methodes
	////////////////////////////////////////////////////////////////////////////
	
	/**
	 * 
	 */
	protected void testCtor_1() {
		
		PreferenceService ps = null;
		
		try {
			ps = new PreferenceService(null);
		} catch (Exception e) {
		}
		
		assertEquals("", true, ps == null);
		
	}
	
	/**
	 * 
	 */
	protected void testCtor_2() {
		
		PreferenceService ps = null;
		
		try {
			ps = new PreferenceService(new RmsPersistentPrefsMock());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		assertEquals("", true, ps != null);
		
	}

	/**
	 * 
	 */
	void testInitialize() {
		
		PreferenceService ps = null;
		
		try {
			
			ps = new PreferenceService(new RmsPersistentPrefsMock());
			ps.initialize();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		Preference pref = ps.getPreference(); 
		
		assertEquals("", true, pref != null);
		
		assertEquals("", true, pref.getCurrSiteInfoIndex() == 0);
	}
	
	/**
	 * 
	 */
	void testDoFinalize() {
		
		PreferenceService ps = null;
		
		try {
			
			ps = new PreferenceService(new RmsPersistentPrefsMock());
			ps.initialize();
			ps.doFinalize();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		assertEquals("", true, ps != null);

		
	}
	
	
}
