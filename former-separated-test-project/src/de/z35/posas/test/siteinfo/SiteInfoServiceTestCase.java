package de.z35.posas.test.siteinfo;

import de.z35.posas.siteinfo.SiteInfo;
import de.z35.posas.siteinfo.SiteInfoService;
import de.z35.posas.test.util.J4METestCase;
import j2meunit.framework.Test;
import j2meunit.framework.TestCase;
import j2meunit.framework.TestMethod;
import j2meunit.framework.TestSuite;


public class SiteInfoServiceTestCase extends J4METestCase {

	////////////////////////////////////////////////////////////////////////////
	//  constructors
	////////////////////////////////////////////////////////////////////////////
	
	/**
	 * 
	 * @param arg0
	 * @param arg1
	 */
	public SiteInfoServiceTestCase(String arg0, TestMethod arg1) {
		super(arg0, arg1);
	}
	
	/**
	 * 
	 *
	 */
	public SiteInfoServiceTestCase() {
		super();
	}
	
	////////////////////////////////////////////////////////////////////////////
	//  suite
	////////////////////////////////////////////////////////////////////////////
	
	public Test suite() {

		TestSuite ts = new TestSuite();

		//  testDoFinalize()
		ts.addTest(new SiteInfoServiceTestCase("testDoFinalize()", new TestMethod() {
			public void run(TestCase tc) {
				((SiteInfoServiceTestCase) tc).testDoFinalize();
			}
		}));
		
		//  testRetrieveAll()
		ts.addTest(new SiteInfoServiceTestCase("testRetrieveAll()", new TestMethod() {
			public void run(TestCase tc) {
				((SiteInfoServiceTestCase) tc).testRetrieveAll();
			}
		}));
		
		//  testAddLocation()
		ts.addTest(new SiteInfoServiceTestCase("testAddLocation()", new TestMethod() {
			public void run(TestCase tc) {
				((SiteInfoServiceTestCase) tc).testAddLocation();
			}
		}));
		
				
		//  testAddLocation()
		ts.addTest(new SiteInfoServiceTestCase("testLocationsWasChanged_CHANGED()", new TestMethod() {
			public void run(TestCase tc) {
				((SiteInfoServiceTestCase) tc).testLocationsWasChanged_CHANGED();
			}
		}));
		
		
		//  testAddLocation()
		ts.addTest(new SiteInfoServiceTestCase("testLocationsWasChanged_DROPPED()", new TestMethod() {
			public void run(TestCase tc) {
				((SiteInfoServiceTestCase) tc).testLocationsWasChanged_DROPPED();
			}
		}));
		
				
		//  testLocationsWasChanged_INSERTED()
		ts.addTest(new SiteInfoServiceTestCase("testLocationsWasChanged_INSERTED()", new TestMethod() {
			public void run(TestCase tc) {
				((SiteInfoServiceTestCase) tc).testLocationsWasChanged_INSERTED();
			}
		}));
		
		//  testDropLocation_BySiteInfo()
		ts.addTest(new SiteInfoServiceTestCase("testDropLocation_BySiteInfo()", new TestMethod() {
			public void run(TestCase tc) {
				((SiteInfoServiceTestCase) tc).testDropLocation_BySiteInfo();
			}
		}));
		
		//  testDropLocation_ByName()
		ts.addTest(new SiteInfoServiceTestCase("testDropLocation_ByName()", new TestMethod() {
			public void run(TestCase tc) {
				((SiteInfoServiceTestCase) tc).testDropLocation_ByName();
			}
		}));

		//  testChangeLocation()
		ts.addTest(new SiteInfoServiceTestCase("testChangeLocation()", new TestMethod() {
			public void run(TestCase tc) {
				((SiteInfoServiceTestCase) tc).testChangeLocation();
			}
		}));
		
		//  testConvertInput2Degree()
		ts.addTest(new SiteInfoServiceTestCase("testConvertInput2Degree()", new TestMethod() {
			public void run(TestCase tc) {
				((SiteInfoServiceTestCase) tc).testConvertInput2Degree();
			}
		}));
		
		//  testConvertInput2TimeZone_plus8()
		ts.addTest(new SiteInfoServiceTestCase("testConvertInput2TimeZone_plus8()", new TestMethod() {
			public void run(TestCase tc) {
				((SiteInfoServiceTestCase) tc).testConvertInput2TimeZone_plus8();
			}
		}));
		
		//  testConvertInput2TimeZone_minus8()
		ts.addTest(new SiteInfoServiceTestCase("testConvertInput2TimeZone_minus8()", new TestMethod() {
			public void run(TestCase tc) {
				((SiteInfoServiceTestCase) tc).testConvertInput2TimeZone_minus8();
			}
		}));

		//  testConvertInput2TimeZone_minus8()
		ts.addTest(new SiteInfoServiceTestCase("testConvertInput2TimeZone_minus8()", new TestMethod() {
			public void run(TestCase tc) {
				((SiteInfoServiceTestCase) tc).testConvertInput2TimeZone_minus8();
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
	protected void testRetrieveAll() {
		
		SiteInfoService siv = SiteInfoService.getInstance();
		
		try {
			siv.initialize(new RmsPersistentSiteInfoMock());
		} catch (Exception e) {
			e.printStackTrace();
		}

		for (int i = 0; i < siv.getItemCount(); i++) {

			SiteInfo l = siv.getItemByIdx(i);

			if (i == 0) {

				assertEquals("Berlin", l.getName());
				assertEquals("", -13.47d, l.getLon(), 0.001d);
				assertEquals("", 52.50d, l.getLat(), 0.001d);
				assertEquals(0, l.getDayLightSaving());
				assertEquals(1, l.getTzOffs());

			} else if (i == 1) {

				assertEquals("Bad Elster", l.getName());
				assertEquals("", -12.14d, l.getLon(), 0.001d);
				assertEquals("", 50.17d, l.getLat(), 0.001d);
				assertEquals(0, l.getDayLightSaving());
				assertEquals(1, l.getTzOffs());

			}

		}
		
	}
	
	/**
	 * 
	 */
	public final void testAddLocation() {

		SiteInfoService siv = SiteInfoService.getInstance();
		
		try {
			
			siv.initialize(new RmsPersistentSiteInfoMock());

			SiteInfo si = new SiteInfo("Stuttgart", -9.18, 48.78, 1, 0); 
			
			siv.addItem(si);
	
			si = siv.getItemByName("Stuttgart");
			
			assertEquals("", si != null, true);
			
//			siv.updateAll();
			
		} catch (Exception e) {
			fail("Exception wurde geworfen...");
		}
		
	}
	
	/**
	 * 
	 */
	public final void testLocationsWasChanged_CHANGED() {

		SiteInfoService siv = SiteInfoService.getInstance();
		
		try {
			siv.initialize(new RmsPersistentSiteInfoMock());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		for (int i = 0; i < siv.getItemCount(); i++) {

			SiteInfo l = siv.getItemByIdx(i);

			if (i == 0) {
				l.setState(SiteInfo.CHANGED, true);
			} 


		}
		
//		boolean actual = siv.locationsWasChanged();
		
//		assertEquals("", true, actual);		
		
	}
	
	/**
	 * 
	 */
	public final void testLocationsWasChanged_DROPPED() {

		SiteInfoService siv = SiteInfoService.getInstance();
		
		try {
			siv.initialize(new RmsPersistentSiteInfoMock());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		for (int i = 0; i < siv.getItemCount(); i++) {

			SiteInfo l = siv.getItemByIdx(i);

			if (i == 0) {
				l.setState(SiteInfo.DROPPED, true);
			} 


		}
		
//		boolean actual = siv.locationsWasChanged();
//		
//		assertEquals("", true, actual);		
		
	}

	/**
	 * 
	 */
	public final void testLocationsWasChanged_INSERTED() {

		SiteInfoService siv = SiteInfoService.getInstance();
		
		try {
			siv.initialize(new RmsPersistentSiteInfoMock());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		for (int i = 0; i < siv.getItemCount(); i++) {

			SiteInfo l = siv.getItemByIdx(i);

			if (i == 0) {
				l.setState(SiteInfo.INSERTED, true);
			} 

		}
		
//		boolean actual = siv.locationsWasChanged();
//		
//		assertEquals("", true, actual);		
		
	}
	
	/**
	 * 
	 */
	public final void testDropLocation_BySiteInfo() {

		SiteInfoService siv = SiteInfoService.getInstance();
		
		try {
			siv.initialize(new RmsPersistentSiteInfoMock());
		} catch (Exception e) {
			e.printStackTrace();
		}

		SiteInfo l = siv.getItemByIdx(0);
		
		siv.dropItem(l.getName());
		
		l = siv.getItemByName(l.getName());
		
		assertEquals(l, null);
		
	}

	/**
	 * 
	 */
	public final void testDropLocation_ByName() {

		SiteInfoService.shutdown();
		
		SiteInfoService siv = SiteInfoService.getInstance();
		
		try {
			siv.initialize(new RmsPersistentSiteInfoMock());
		} catch (Exception e) {
			e.printStackTrace();
		}

		SiteInfo l = siv.getItemByIdx(0);
		
		siv.dropItem("Berlin");
		
		l = siv.getItemByName("Berlin");
		
		assertEquals(l, null);
		
	}
	
	/**
	 * 
	 */
	public final void testChangeLocation() {

		SiteInfoService siv = SiteInfoService.getInstance();
		
		try {
			siv.initialize(new RmsPersistentSiteInfoMock());
		} catch (Exception e) {
			e.printStackTrace();
		}

		SiteInfo l = siv.getItemByIdx(0);
		
		l.setName("Timbuktu");
		
		siv.changeItem(l);
		
		l = siv.getItemByName("Timbuktu");
		
		assertEquals("", true, l != null);
		
	}

	/**
	 * 
	 */
	public final void testConvertInput2Degree() {

		double actual = SiteInfoService.convertInput2Degree("13 28 O");
		
		assertEquals("", -13.47d, actual, 0.01);
	}
	
	/**
	 * 
	 */
	public final void testConvertInput2TimeZone_plus8() {

		double actual = SiteInfoService.convertInput2TimeZone("GMT + 8h");
		
		assertEquals("", 8, actual, 0.01);
	}

	/**
	 * 2008-01-04  z35 Bugfixing: ?bergabe negativer Timezone liefert positiven Wert 
	 */
	public final void testConvertInput2TimeZone_minus8() {

		double actual = SiteInfoService.convertInput2TimeZone("GMT - 8h");
		
		assertEquals("", -8, actual, 0.01);
	}
	

	/**
	 * 
	 */
	public final void testDoFinalize() {

		SiteInfoService siv = SiteInfoService.getInstance();
		
		try {
			siv.initialize(new RmsPersistentSiteInfoMock());
			siv.doFinalize();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}
	
	
}
