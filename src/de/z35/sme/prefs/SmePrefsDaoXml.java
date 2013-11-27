package de.z35.sme.prefs;

import de.z35.sme.SmeRecordStore;
import de.z35.util.Commons;
import java.io.ByteArrayInputStream;
import java.util.Stack;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public final class SmePrefsDaoXml {

	static final String PREFS = "prefs";
	static final String SITEINFO = "siteinfo";	
	static final String INDEX = "index";
	static final String NAME = "name";
	
	/**
	 * 
	 *
	 */
	public SmePrefsDaoXml() {
		
		super();
		
	}
	
	/**
	 * 
	 *
	 */
	public SmePrefs retrieveAll() throws Exception {
		
		SmePrefs prefs = new SmePrefs(); 
		
		byte[] xml = SmeRecordStore.retrievePrefs();
		
		SAXParser saxParser = SAXParserFactory.newInstance().newSAXParser();
		
		saxParser.parse(new ByteArrayInputStream(xml), new EventHandler(prefs));
		
		return prefs;
		
	}
	
	/**
	 * 
	 *
	 */
	public void updateAll(SmePrefs prefs) {

		StringBuffer xml = new StringBuffer();
		
		//  <?xml version=\"1.0\" encoding=\"ISO-8859-1\"?>
		xml.append(Commons.XML_PREAMPLE);

		//  <prefs>		
		xml.append("<").append(PREFS).append(">");

			//  <siteinfo index="1">
			xml.append("<").append(SITEINFO).append(Commons.addAttr(INDEX, String.valueOf(prefs.getSiteInfoIndex()))).append(">");
	
				//  <name>Berlin</name>
				xml.append("<").append(NAME).append(">"); 
				xml.append(prefs.getSiteInfoName());
				xml.append("</").append(NAME).append(">");		
	
			//  </siteinfo>
			xml.append("</").append(SITEINFO).append(">");
		
		//  </prefs>
		xml.append("</").append(PREFS).append(">");
		
		SmeRecordStore.updateAll(xml.toString().getBytes(), SmeRecordStore.PREFS);
		
	}
	
	/**
	 * 2007-OCT-01  z35  Quelle: Aus Beispiel für S60 SDK EventHandler extends DefaultHandler, which is the default base class for SAX2 event handlers. It utilises the following methods: startDocument(), startElement(), characters(), endElement()  and endDocument().
	 */
	private class EventHandler extends DefaultHandler {

		SmePrefs prefs;
		private Stack qNameStack = new Stack(); // keep track of QName
		
		/**
		 * 
		 * @param locations
		 * @throws IllegalArgumentException - falls <code>prefs<code> = null
		 */
		public EventHandler(SmePrefs prefs) throws IllegalArgumentException {
			
			if (prefs == null)
				throw new IllegalArgumentException();

			this.prefs = prefs;
			
		}
		
		/**
		 * 
		 */
	    public void startDocument() throws SAXException {}

	    /**
	     * Receive notification of the start of an element.
	     * @param uri
	     * @param localName
	     * @param qName is the qualified name (with prefix), in this case "phone".
	     * @param attributes
	     */
	    public void startElement(String uri, String localName, String qName,
				Attributes attributes) throws SAXException {
	    	
			if (SITEINFO.equals(qName)) {

				String siteInfoIndex = attributes.getValue(INDEX); 
				prefs.setSiteInfoIndex(Integer.parseInt(siteInfoIndex));

			} 

			// Keep track of qNames
			qNameStack.push(qName);			
			
		}

	    /**
		 * 
		 * @param ch
		 *            is an array of the characters to be parsed.
		 * @param start
		 *            is start value used for creating a String chars.
		 * @param length
		 *            is end value used for creating a String chars.
		 * @throws SAXException
		 * 
		 */
	    public void characters(char[] ch, int start, int length)
				throws SAXException {
	    	
	    	String qName;
	        String chars = new String(ch, start, length);

			// Get current QName
			qName = (String) qNameStack.peek();

			if (SITEINFO.equals(qName)) {
		          // Nothing to process			
			} else if (NAME.equals(qName)) {
				prefs.setSiteInfoName(chars);
			}
			
		}

	    /**
		 * 
		 * @param uri
		 * @param localName
		 * @param qName
		 * @param attributes
		 * @throws SAXException
		 */
	    public void endElement(String uri, String localName, String qName,
				Attributes attributes) throws SAXException {

			// Pop QName, since we are done with it
			qNameStack.pop();
			
		}

	    /**
		 * 
		 * @throws SAXException
		 */
	    public void endDocument() throws SAXException {}
		
	}
	
	
}
