package com.has.base;

import static com.has.base.Arguments.*;

@SuppressWarnings("unused")
public class CommandFactory
{
	private static long last = 0;


	public static Command createGetConfigCommand()
	{
		return new Command( last++ , Command.GET_CONFIG,
				new Arguments() );
	}

	public static Command createGetOptionsCommand()
	{
		return new Command( last++ , Command.GET_OPTIONS,
				new Arguments() );
	}

	public static Command createSetConfigCommand(  ) // !developer! //TODO
	{
		return new Command( last++ , Command.SET_CONFIG,
				new Arguments( CONFIG ).putOn( 0, null ) );
	}

	public static Command createSetOptionsCommand(   ) // !developer! //TODO
	{
		return new Command( last++ , Command.SET_OPTIONS,
				new Arguments( OPTIONS ).putOn( 0, null ) );
	}

	public static Command createSetNobodyHomeModeCommand( boolean on )
	{
		return new Command( last++ , Command.SET_NOBODY_HOME_MODE,
				new Arguments( BOOL ).putOn( 0, on ) );
	}

	public static Command createEnableDeveloperSessionCommand( String token )
	{
		return new Command( last++ , Command.ENABLE_DEVELOPER_SESSION,
				new Arguments( STRING ).putOn( 0, token ) );
	}

	public static Command createSetLightStateCommand( long deviceId, boolean state )
	{
		return new Command( last++ , Command.GET_LIGHT_STATE,
				new Arguments( LONG + BOOL ).putOn( 0, deviceId ).putOn( 1, state ) );
	}

	public static Command createGetLightStateCommand( long deviceId )
	{
		return new Command( last++ , Command.GET_LIGHT_STATE,
				new Arguments( LONG ).putOn( 0, deviceId ) );
	}

	public static Command createSetDistanceCommand( long deviceId, double distance ) // !developer!
	{
		return new Command( last++ , Command.SET_DISTANCE,
				new Arguments( LONG + DOUBLE ).putOn( 0, deviceId ).putOn( 1, distance ) );
	}

	public static Command createGetDistanceCommand( long deviceId )
	{
		return new Command( last++ , Command.GET_DISTANCE,
				new Arguments( LONG ).putOn( 0, deviceId ) );
	}

	public static Command createGetLightIntensityCommand( long deviceId )
	{
		return new Command( last++ , Command.GET_LIGHT_INTENSITY,
				new Arguments( LONG ).putOn( 0, deviceId ) );
	}

	public static Command createSetLightIntensityCommand( long deviceId, double intensity )
	{
		return new Command( last++ , Command.SET_LIGHT_INTENSITY,
				new Arguments( LONG + DOUBLE ).putOn( 0, deviceId ).putOn( 1, intensity ) );
	}

	public static Command createGetHeaterStateCommand( long deviceId )
	{
		return new Command( last++ , Command.GET_HEATER_STATE,
				new Arguments( LONG  ).putOn( 0, deviceId ) );
	}

	public static Command createSetHeaterStateCommand( long deviceId, boolean state )
	{
		return new Command( last++ , Command.SET_HEATER_STATE,
				new Arguments( LONG + BOOL ).putOn( 0, deviceId ).putOn( 1, state ) );
	}

	public static Command createGetFanStateCommand( long deviceId )
	{
		return new Command( last++ , Command.GET_FAN_STATE,
				new Arguments( LONG  ).putOn( 0, deviceId ) );
	}

	public static Command createSetFANStateCommand( long deviceId, boolean state )
	{
		return new Command( last++ , Command.SET_FAN_STATE,
				new Arguments( LONG + BOOL ).putOn( 0, deviceId ).putOn( 1, state ) );
	}

	public static Command createGetTemperatureCommand( long deviceId )
	{
		return new Command( last++ , Command.GET_TEMPERATURE,
				new Arguments( LONG  ).putOn( 0, deviceId ) );
	}

	public static Command createSetTemperatureCommand( long deviceId, double temp )
	{
		return new Command( last++ , Command.SET_TEMPERATURE,
				new Arguments( LONG + DOUBLE ).putOn( 0, deviceId ).putOn( 1, temp ) );
	}

	// TODO IMPEMENT GAS STUFF todo by EGE

}
