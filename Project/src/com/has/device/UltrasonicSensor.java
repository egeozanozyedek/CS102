package com.has.device;

public class UltrasonicSensor extends Device
{
	private static final long INTRUSION_CHECK_DELA_MS = 12000; // every 12 seconds
	private static final long INTRUSION_TRESHOLD = 30; // if less than this then intrusion
	private static final long TIMEOUT = 100;
	private double distance;
	private long lastChecked;

	public UltrasonicSensor( long id, String name, int[] pins )
	{
		super(id, name, pins);
		getDistance();
	}

	public double getDistance()
	{
		return distance;
	}

	public void setDistance( double distance )
	{
		this.distance = distance;
		if ( !this.isVirtual() )
		{
			/*
			GpioPinDigitalOutput trig = Device.PIN_CONTROLLER.provisionDigitalOutputPin(RaspiPin.allPins()[pins[0]]);
			GpioPinDigitalInput  echo = Device.PIN_CONTROLLER.provisionDigitalInputPin(RaspiPin.allPins()[pins[1]]);

			trig.low();
			trig.high();
			trig.low();

			long startTime = System.currentTimeMillis();
			long stopTime;

			while ( echo.isLow() );

			long rawStart = System.currentTimeMillis();

			Thread thread = new Thread( () -> { while ( echo.isHigh() ); } );
			thread.start();

			while ( ( ( stopTime = System.currentTimeMillis() ) - startTime < TIMEOUT ) && thread.isAlive() );

			long raw = System.currentTimeMillis() - rawStart;

			if ( stopTime - startTime > TIMEOUT  )
			{
				this.distance = -1.0;
				return;
			}

			this.distance = raw / 58.31;
			*/
		}

	}

	public boolean isIntrusionDetected()
	{
		if ( System.currentTimeMillis() - lastChecked > INTRUSION_CHECK_DELA_MS )
		{
			lastChecked = System.currentTimeMillis();
			getDistance();
			return this.distance < INTRUSION_TRESHOLD && this.distance > 0;
		}
		return false;
	}

	@Override
	public String toString()
	{
		return "UltrasonicSensor.{name = " + this.name + " id = " + this.id + " intensity = " + this.distance + "}\n";
	}
}
