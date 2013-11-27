package de.z35.posas.test.siteinfo;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import de.z35.posas.interfaces.RmsPersistenceInt;

public class RmsPersistentPrefsMock implements RmsPersistenceInt {

	/**
	 * 
	 */
	public RmsPersistentPrefsMock() {
		
		super();
		
	}
	
	/**
	 * 
	 */
	public byte[] readRecord(int recId) {

		byte[] result = null;
		
		FileInputStream fis = null;
		
		try {
			
			fis = new FileInputStream("preferences-in.xml");
			
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
			
			fos = new FileOutputStream("preferences-out.xml");
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
