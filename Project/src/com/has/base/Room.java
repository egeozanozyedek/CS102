package com.has.base;

import com.has.device.*;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

@SuppressWarnings( "unused" )
public class Room
{
	private ArrayList<Device> devices;
	private String name;
	private long id;

	public Room( long id, String name )
	{
		this.devices = new ArrayList<>();
		this.name = name;
	}

	public long getId()
	{
		return id;
	}

	public String getName()
	{
		return name;
	}

	public ArrayList<Device> getDevices()
	{
		return devices;
	}

	public Device getDeviceByIndex(int index )
	{
		return devices.get( index );
	}

	public boolean contains( long id )
	{
		for ( Device dev : devices )
			if ( dev.getId() == id )
				return true;
		return false;
	}

	public boolean contains( Device device )
	{
		for ( Device dev : devices )
			if ( dev.getId() == device.getId() )
				return true;
		return false;
	}

	@Override
	public String toString()
	{
		return "Room.name='" + name + "'"; // "Room.{name = " + name + " id = " + id + " devices length = " + devices.size() + "}";
	}

	public JSONObject getJSONObject()
	{
		JSONObject object = new JSONObject();
		try
		{
			object.put("id", this.id );
			object.put("display_name", this.name );
			JSONArray deviceIds = new JSONArray();

			for ( Device device : devices )
				deviceIds.put( device.getId() );

			object.put( "devices_id", deviceIds );
		}
		catch ( JSONException e )
		{
			e.printStackTrace();
		}
		return object;
	}
}
