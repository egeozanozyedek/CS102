package com.has.device;


public class Heater extends Device
{
	private boolean state;

	public Heater( long id, String name, int[] pins )
	{
		super(id, name, pins);
	}

	public boolean getState()
	{
		return state;
	}

	public void setState( boolean state )
	{
		this.state = state;
		if ( !this.isVirtual() )
		{
			// Device.PIN_CONTROLLER.provisionDigitalOutputPin(RaspiPin.allPins()[pins[0]]).setState(state);
		}

	}

	@Override
	public String toString()
	{
		return "Heater.{name = " + name + " id = " + id + " state = " + state + "}\n";
	}

}
