package com.has.device;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public abstract class Device
{
	// public static final GpioController PIN_CONTROLLER = GpioFactory.getInstance();

	protected long id;
	protected String name;
	protected int[] pins;

	public Device( long id, String name, int[] pins )
	{
		this.id = id;
		this.name = name;
		this.pins = pins;
	}

	public long getId()
	{
		return id;
	}

	public String getName()
	{
		return name;
	}

	public int getPin( int index )
	{
		return pins[index];
	}

	public boolean isVirtual()
	{
		return pins.length == 0;
	}

	public int pinCount()
	{
		return pins.length;
	}

	@Override
	public String toString()
	{
		return "Device.{name = " + name + " id = " + id + "}\n";
	}

	public JSONObject getJSONObject()
	{
		JSONObject object = new JSONObject();
		try
		{
			object.put("id", this.id );
			object.put("device_name", this.name );

			if ( this instanceof Heater )
				object.put( "device_type", "HeaterDevice" );
			else if ( this instanceof LightSensor )
				object.put( "device_type", "LightSensorDevice" );
			else if ( this instanceof Light )
				object.put( "device_type", "LightDevice" );
			else if ( this instanceof UltrasonicSensor )
				object.put( "device_type", "UltrasonicSensorDevice" );

			JSONArray pinArray = new JSONArray();

			for ( int i = 0; i < pins.length; i++ )
				pinArray.put(pins[i]);

			object.put("pins", pinArray);
		}
		catch ( JSONException e )
		{
			e.printStackTrace();
		}
		return object;
	}
}
