package de.z35.posas.test.siteinfo;

import de.z35.posas.interfaces.RmsPersistenceInt;
import java.io.*;

public class RmsPersistentSiteInfoMock implements RmsPersistenceInt {
	
	/**
	 * 
	 */
	public RmsPersistentSiteInfoMock() {
		
		super();
		
	}

	/**
	 * 
	 */
	public byte[] readRecord(int recId) {

		byte[] result = null;
		
		FileInputStream fis = null;
		
		try {
			
			fis = new FileInputStream("locations-in.xml");
			
			int len = fis.available();
			
			result = new byte[len];
			
			fis.read(result);
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			
			if (fis != null ) {
				
				try {
					fis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
		return result;
		
	}

	/**
	 * 
	 */
	public void writeRecord(byte[] rec, int recId) {
		
		FileOutputStream fos = null;
		
		try {
			
			fos = new FileOutputStream("locations-out.xml");
			fos.write(rec);
			fos.flush();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			
			if (fos != null) {
				
				try {
					fos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
				
			}
			
		}

	}

}
