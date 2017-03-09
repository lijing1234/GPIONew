package com.example.lijing.gpionew;

import android.content.Context;

import com.ieimobile.io.Lib;


/**
 * @brief A class to handle DIO, switch RS232/422/485 mode and etc.  
 */

/**
 * The class use IEIMobile run time library built-in your device, so you
 * have to add the following line to your application's manifest
 * (AndroidManifest.xml).
 * 
 * <uses-library android:name="com.gpiohw.server" android:required="false" />
 * 
 * This tells the system about the IEIMobile hardware library used by the
 * application, so that it can be properly loaded and linked to the app when the
 * app is initialized.
 */
public class Gpio {
	public static final String TAG = "IEIMobile_HW";
	
	public static final int MODE_RS232 = Lib.MODE_RS232;
	public static final int MODE_RS422 = Lib.MODE_RS422;
	public static final int MODE_RS485 = Lib.MODE_RS485;
	
	public static final String SERIALPORT_DEVICE1 = Lib.SERIALPORT_DEVICE1;
	public static final String SERIALPORT_DEVICE2 = Lib.SERIALPORT_DEVICE2;
				
	private final Lib mLib; 
		
	public Gpio(Context context) {
		mLib = new Lib(context);
	}
	
	public boolean configureDIO(int index, boolean input) {
		return mLib.configureDIO_Direction(index, input);
	}
	
	/**
	 * Get DIO INPUT/OUTPUT Mode
	 * 
	 * @param index
	 * @return 0 if DIO configured as Output; return 1 if DIO configured as Input. Otherwise
	 *         return -1;
	 */
	public int getDIODirection(int index) {
		return mLib.getDIO_Direction(index);
	}
	
	/**
	 * Set the value of digital output
	 * @param index digital output index, based from 0 
	 * @param value 1=HIGH or 0=LOW
	 */
	public void setDO(int index, int value) {
		mLib.setDigitalOutput(index, value);
	}
	
	/**
	 * Get the value of digital input
	 * @param index digital input index, based from 0
	 * @return 1 if digital input= HIGH; otherwise return 0;
	 */
	public int getDI(int index) {		
		return mLib.getDigitalInput(index);
	}
	
	/**
	 * Set serial communication mode
	 * @param mode MODE_RS232, MODE_RS422 or MODE_RS485
	 */
	public boolean setSerialMode(int mode) {
		return mLib.setSerialMode(mode);
	}
	
	public int getSerialMode() {
		return mLib.getSerialMode();
	}

}
