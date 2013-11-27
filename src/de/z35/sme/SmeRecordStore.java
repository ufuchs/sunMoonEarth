package de.z35.sme;

import javax.microedition.rms.InvalidRecordIDException;
import javax.microedition.rms.RecordStore;
import javax.microedition.rms.RecordStoreException;
import javax.microedition.rms.RecordStoreFullException;
import javax.microedition.rms.RecordStoreNotFoundException;
import javax.microedition.rms.RecordStoreNotOpenException;

import de.z35.util.Commons;

// 

/**
 * 
 * @author Uli Fuchs
 * @link http://www.java2s.com/Code/Java/J2ME/Readandwritetotherecordstore.htm
 * 
 */
public class SmeRecordStore {
	
	public final static int LOCATIONS = 1;

	public final static int PREFS = 2;	
	
	private final static String RS_NAME = "sme-rs";
	
	private RecordStore rs; 
	
//	private final static String LOCATIONS = "<?xml version=\"1.0\" encoding=\"ISO-8859-1\"?><locations><location name=\"Berlin\"><coordinates lon=\"-13.47\" lat=\"52.50\"/><timezone offset=\"1\"/></location><location name=\"Bad Elster\"><coordinates lon=\"-12.14\" lat=\"50.17\"/><timezone offset=\"1\"/></location><location name=\"Stuttgart\"><coordinates lon=\"-9.18\" lat=\"48.78\"/><timezone offset=\"1\"/></location><location name=\"Madrid\"><coordinates lon=\"3.7\" lat=\"40.42\"/><timezone offset=\"0\"/></location></locations>";

	private final static String DEF_LOCATION_START = "<locations>";
	
	private final static String DEF_LOCATION_0 = "<location name=\"Berlin\"><coordinates lon=\"-13.47\" lat=\"52.50\"/><timezone offset=\"1\"/></location>";
	private final static String DEF_LOCATION_1 = "<location name=\"Stuttgart\"><coordinates lon=\"-9.18\" lat=\"48.78\"/><timezone offset=\"1\"/></location>";
	private final static String DEF_LOCATION_9 = "<location name=\"Paris\"><coordinates lon=\"-2.20\" lat=\"48.52\"/><timezone offset=\"0\"/></location>";
	private final static String DEF_LOCATION_2 = "<location name=\"New York\"><coordinates lon=\"74.00\" lat=\"40.72\"/><timezone offset=\"-5\"/></location>";	
	private final static String DEF_LOCATION_3 = "<location name=\"Kapstadt\"><coordinates lon=\"-18.48\" lat=\"-33.83\"/><timezone offset=\"2\"/></location>";
	private final static String DEF_LOCATION_4 = "<location name=\"Rio de Janeiro\"><coordinates lon=\"43.18\" lat=\"-22.90\"/><timezone offset=\"-3\"/></location>";	
	private final static String DEF_LOCATION_5 = "<location name=\"Kinshasa\"><coordinates lon=\"-15.32\" lat=\"-4.33\"/><timezone offset=\"1\"/></location>";	
	private final static String DEF_LOCATION_6 = "<location name=\"Ny-Alesund\"><coordinates lon=\"-11.93\" lat=\"78.92\"/><timezone offset=\"1\"/></location>";
	private final static String DEF_LOCATION_7 = "<location name=\"Dar es Salam\"><coordinates lon=\"-39.28\" lat=\"-6.80\"/><timezone offset=\"3\"/></location>";
	private final static String DEF_LOCATION_8 = "<location name=\"McMurdo\"><coordinates lon=\"-166.67\" lat=\"-77.85\"/><timezone offset=\"12\"/></location>";
	

	private final static String DEF_LOCATION_END = "</locations>";
	
	private final static String DEF_PREFS = "<prefs><siteinfo index=\"0\"><name>Berlin</name></siteinfo></prefs>";
	
	/**
	 * 
	 *
	 */
	public SmeRecordStore() {
		
		super();
		
	}
	
	/**
	 * 
	 * @return
	 */
	public static byte[] retrieveLocations() {

		byte[] result = null;
		
		SmeRecordStore sirs = new SmeRecordStore();
		sirs.openStore();
		result = sirs.readRecord(LOCATIONS);
		sirs.closeStore();
		sirs = null;

		return result;
		
	}

	/**
	 * 
	 * @return
	 */
	public static byte[] retrievePrefs() {

		byte[] result = null;
		
		SmeRecordStore sirs = new SmeRecordStore();
		sirs.openStore();
		result = sirs.readRecord(PREFS);
		sirs.closeStore();
		sirs = null;

		return result;
		
	}
	
	
	/**
	 * 
	 * @param xml
	 */
	public static void updateAll(byte[] xml, int recId) {
		
		SmeRecordStore sirs = new SmeRecordStore();
		sirs.openStore();
		sirs.writeRecord(xml, recId);
		sirs.closeStore();
		sirs = null;
		
	}
	
	/**
	 * 
	 *
	 */
	public void openStore() {

		try {
			rs = RecordStore.openRecordStore(RS_NAME, true);
			
			if (rs.getNumRecords() == 0) {
				firstTouch();
			}
			
		} catch (RecordStoreFullException e) {
			e.printStackTrace();
		} catch (RecordStoreNotFoundException e) {
			e.printStackTrace();
		} catch (RecordStoreException e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * 
	 *
	 */
	public void closeStore() {
		
		try {
			rs.closeRecordStore();
		} catch (RecordStoreNotOpenException e) {
			e.printStackTrace();
		} catch (RecordStoreException e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * 
	 *
	 */
	private void firstTouch() {
		
		StringBuffer sb = new StringBuffer();

		////////////////////////////////////////////////////////////////////////
		//  locations
		////////////////////////////////////////////////////////////////////////		
		
		sb.append(Commons.XML_PREAMPLE);
		
		
		
		sb.append(DEF_LOCATION_START);
		sb.append(DEF_LOCATION_0);
		sb.append(DEF_LOCATION_1);
		sb.append(DEF_LOCATION_9);		
		sb.append(DEF_LOCATION_2);
		sb.append(DEF_LOCATION_3);
		sb.append(DEF_LOCATION_4);
		sb.append(DEF_LOCATION_5);
		sb.append(DEF_LOCATION_6);
		sb.append(DEF_LOCATION_7);
		sb.append(DEF_LOCATION_8);
		
		sb.append(DEF_LOCATION_END);
		try {
			rs.addRecord(sb.toString().getBytes(), 0, sb.length());
		} catch (Exception e) {
			e.printStackTrace();
		}

		////////////////////////////////////////////////////////////////////////
		//  preferences
		////////////////////////////////////////////////////////////////////////		
		
		sb.setLength(0);
		
		sb.append(Commons.XML_PREAMPLE);
		sb.append(DEF_PREFS);
		
		try {
			rs.addRecord(sb.toString().getBytes(), 0, sb.length());
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	/**
	 * 
	 * @param str
	 */
	public void writeRecord(final byte[] rec, final int recId) {
		
		try {
			
			rs.setRecord(recId, rec, 0, rec.length);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}	
	
	/**
	 * 
	 * @return
	 */
	public byte[] readRecord(final int recId) {

		byte[] result = null;
		
		try {
			
			result = new byte[rs.getRecordSize(recId)];
			
			rs.getRecord(recId, result, 0);
			
		} catch (RecordStoreNotOpenException e1) {
			e1.printStackTrace();
		} catch (InvalidRecordIDException e1) {
			e1.printStackTrace();			
		} catch (RecordStoreException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}		
		
		return result;
		
	}	
	
}
