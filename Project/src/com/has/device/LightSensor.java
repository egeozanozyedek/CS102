package com.has.device;


public class LightSensor extends Device
{
	private double intensity;

	public LightSensor( long id, String name, int[] pins )
	{
		super(id, name, pins);
	}

	public double getIntensity()
	{
		return intensity;
	}

	public void setIntensity( double intensity )
	{
		this.intensity = intensity;
		if ( !this.isVirtual() )
		{
			// Device.PIN_CONTROLLER.provisionDigitalOutputPin(RaspiPin.allPins()[pins[0]]).setState(state);
		}

	}

	@Override
	public String toString()
	{
		return "LightSensor.{name = " + name + " id = " + id + " intensity = " + intensity + "}\n";
	}
}
