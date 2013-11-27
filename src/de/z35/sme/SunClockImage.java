package de.z35.sme;

import de.z35.posas.core.RiseAndSet;

public class SunClockImage extends MutableImage {

	private final static int DAY_COLOR = 0x00FFFF00;
	private final static int NIGTH_COLOR = 0x00000000;
//	private final static int CIVIL_TWILIGHT = 0x00D3D3D3;
	private final static int NAUTICAL_TWILIGHT = 0x00708090;

	private int ntwr;
	private int ctwr;
	private int rise;
	private int set;
	private int ctws;
	private int ntws;
	
	private int state;
	
	
	//  nautical twilight at rise
	private int d_ntwrStart;
	private int d_ntwrLen;
	
	//  civil twilight at rise
	private int d_ctwrStart;
	private int d_ctwrLen;
	
	private int d_rise;
	private int d_set;

	//  civil twilight at set
	private int d_ctwsEnd;
	private int d_ctwsLen;
	
	//  nautical twilight at set
	private int d_ntwsEnd;
	private int d_ntwsLen;
	
	
	/**
	 * 
	 *
	 */
	public SunClockImage(int backgroundColor, int width, int heigth) {
		
		super(backgroundColor, width, heigth);
	}

	/**
	 * 
	 * @param atwr is astronomical twilight on sun rise 
	 * @param ntwr is nautical 
	 * @param ctwr is civil
	 * @param rise
	 * @param set
	 * @param atws is astronomical twilight on sun set
	 * @param ntws is nautic
	 * @param ctws is civil 
	 */
	public void setParams(int ntwr, int ctwr, int rise, int set, int ctws,
			int ntws, int state) {

		// nautical twilight at rise
		this.ntwr = ntwr;
		// civil twilight at rise
		this.ctwr = ctwr;
		// sunrise
		this.rise = rise;
		// sunset
		this.set = set;
		// civil twilight at set
		this.ctws = ctws;
		// nautical twilight at set
		this.ntws = ntws;

		this.state = state;

		calcLayout();

		paintImage();

	}

	/**
	 * 
	 * 
	 */
	private void calcLayout() {
		
//		d_atwr = 0;
//		d_ntwr = 0;
//		d_ctwr = 0;
//		d_rise = 0;
//		d_set = 0;
//		d_ctws = 0;
//		d_ntws = 0;
//		d_atws = 0;
		
		////////////////////////////////////////////////////////////////////////
		// rise ans set
		////////////////////////////////////////////////////////////////////////
		
		d_set = 270 - set;
		//  Bogenlänge
		d_rise = set - rise;

		////////////////////////////////////////////////////////////////////////
		// civil twilight at sunrise
		////////////////////////////////////////////////////////////////////////

		d_ctwrStart = 270 - ctwr;
		//  Bogenlänge
		d_ctwrLen = -(rise - ctwr);

		////////////////////////////////////////////////////////////////////////
		// civil twilight at sunset 
		////////////////////////////////////////////////////////////////////////

		d_ctwsEnd = 270 - ctws;
		//  Bogenlänge
		d_ctwsLen = ctws - set;
		
		////////////////////////////////////////////////////////////////////////
		// nautical twilight at sunrise
		////////////////////////////////////////////////////////////////////////

		d_ntwrStart = 270 - ntwr;
		//  Bogenlänge
		d_ntwrLen = ntwr - ctwr;

		////////////////////////////////////////////////////////////////////////
		// nautical twilight at sunset 
		////////////////////////////////////////////////////////////////////////

		d_ntwsEnd = 270 - ntws;
		//  Bogenlänge
		d_ntwsLen = ntws - ctws;
		
	}
	
	/**
	 * 
	 *
	 */
	public void paintImage() {
		
		int o = 1;
		
//		Graphics g = image.getGraphics();
		
		int height = image.getHeight() - o * 2;
		
		int width = image.getWidth() - o * 2;

        /***********************************************************************
         ** night part 
         **/
		
		g.setColor(NIGTH_COLOR);
		g.fillArc(o, o, width, height, 0, 360);
		
//		if(true) return;
		
//		System.out.println(heigth);
//		System.out.println(width);

        /***********************************************************************
		 ** day part
		 */
		
        if (state == RiseAndSet.ALL_DAY_ABOVE_HORIZ
				|| state == RiseAndSet.RISE_AND_SET_ON_DAY) {

        	g.setColor(DAY_COLOR);

			if (state == RiseAndSet.ALL_DAY_ABOVE_HORIZ) {

				d_set = 0;
				d_rise = 360;

			}
			
			//  Für 'ALL_DAY_ABOVE_HORIZ' werden startAngle = 0 und 
			//  arcAngle = 360 benutzt.
			g.fillArc(o, o, width, height, d_set, d_rise);

		}
		
        /***********************************************************************
		 ** twilight
		 */
        if (state == RiseAndSet.RISE_AND_SET_ON_DAY) {
        	

			//
			//  civil twilight 
			//
			g.setColor(NAUTICAL_TWILIGHT);
			
			// start of civil twilight UPTO sunrise
			g.fillArc(o, o, width, height, d_ctwrStart, d_ctwrLen);	
			// end of civil twilight FROM sunset
			g.fillArc(o, o, width, height, d_ctwsEnd, d_ctwsLen);	
			
			//
			//  nautical twilight 
			//
			g.setColor(NAUTICAL_TWILIGHT);
			
			// start of nautical twilight UPTO sunrise
			g.fillArc(o, o, width, height, d_ntwrStart, d_ntwrLen);	
			// end of nautical twilight FROM sunset
			g.fillArc(o, o, width, height, d_ntwsEnd, d_ntwsLen);
			
        }
        
	}
	
	
}
