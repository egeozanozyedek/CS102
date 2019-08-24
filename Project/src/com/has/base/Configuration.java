package com.has.base;

import com.has.device.*;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Configuration
{
	public static final File CONFIG_FILE = new File("C:\\Users\\EgeOzan\\Desktop\\HAS-Framework-Merged\\res\\json\\configuration.json");

	private ArrayList<Device> devices;
	private ArrayList<Room> rooms;

	public Configuration()
	{
		devices = new ArrayList<>();
		rooms = new ArrayList<>();
		parse(this, getFileContent( CONFIG_FILE ) );
	}

	public ArrayList<Device> getDevices()
	{
		return devices;
	}

	public ArrayList<Room> getRooms()
	{
		return rooms;
	}

	public Device getDeviceById( long id )
	{
		for ( Device d : devices )
			if ( d.getId() == id )
				return d;
		return null;
	}

	public static void parse(Configuration config, String json )
	{
		try
		{
			JSONObject container = new JSONObject( json );
			JSONArray roomArray = container.getJSONArray( "rooms" );
			JSONArray deviceArray = container.getJSONArray( "devices" );

			for ( int i = 0; i < deviceArray.length(); i++ )
			{
				JSONObject device = deviceArray.getJSONObject(i);
				JSONArray jsonPins = device.getJSONArray("pins");

				int id = device.getInt("id");
				String displayName = device.getString("device_name");
				String deviceType = device.getString("device_type");
				int[] pins = new int[jsonPins.length()];

				for ( int j = 0; j < pins.length; j++ )
					pins[j] = jsonPins.getInt(j);

				if (deviceType.equals("HeaterDevice"))
					config.devices.add(new Heater(id, displayName, pins));
				else if (deviceType.equals("LightDevice"))
					config.devices.add(new Light(id, displayName, pins));
				else if (deviceType.equals("UltrasonicSensorDevice"))
					config.devices.add(new UltrasonicSensor(id, displayName, pins));
				else if (deviceType.equals("LightSensorDevice"))
					config.devices.add(new LightSensor(id, displayName, pins));
				else if (deviceType.equals("TempSensorDevice"))
					config.devices.add(new TempSensor(id, displayName, pins));
				else if (deviceType.equals("GasSensorDevice"))
					config.devices.add(new GasSensor(id, displayName, pins));
				else if (deviceType.equals("FanDevice"))
					config.devices.add(new Fan(id, displayName, pins));
			}

				for (int k = 0; k < roomArray.length(); k++)
				{
					JSONObject room = roomArray.getJSONObject( k );

					int roomId = room.getInt("id");
					String name = room.getString("display_name");

					Room tmpRoom = new Room( roomId, name );
					JSONArray roomDeviceIdArray = room.getJSONArray("devices_id");

					for ( int j = 0; j < roomDeviceIdArray.length(); j++ )
					{
						tmpRoom.getDevices().add( config.getDeviceById( roomDeviceIdArray.getInt( j ) ) );
					}
					config.getRooms().add( tmpRoom );
				}


		}
		catch (JSONException e)
		{
			e.printStackTrace();
		}
	}

	public void save()
	{
		save( CONFIG_FILE );
	}

	public void save( File file )
	{
		try
		{
			JSONObject container = new JSONObject();

			JSONArray roomsJsonArray = new JSONArray();

			JSONArray devicesJsonArray = new JSONArray();

			for ( Room room : rooms)
				roomsJsonArray.put( room.getJSONObject() );


			for ( Device device : devices )
				devicesJsonArray.put( device.getJSONObject() );

			container.put("devices", devicesJsonArray);
			container.put("rooms", roomsJsonArray);

			Configuration.setFileContent( file, container.toString() );
		}
		catch (JSONException e)
		{
			e.printStackTrace();
		}
	}

	public static String getFileContent( File file )
	{
		String content = null;
		try
		{
			BufferedReader reader = new BufferedReader( new FileReader( file ) );
			StringBuilder sb = new StringBuilder();
			String line;

			while ( (line = reader.readLine()) != null )
				sb.append( line );

			reader.close();
			content = sb.toString();
		}
		catch ( Exception e )
		{
			e.printStackTrace();
		}
		return content;
	}

	public static void setFileContent( File file, String content )
	{
		try
		{
			BufferedWriter writer = new BufferedWriter( new FileWriter( file ) );
			writer.write( content );
			writer.close();
		}
		catch ( Exception e )
		{
			e.printStackTrace();
		}
	}
}
