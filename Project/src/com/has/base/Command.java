package com.has.base;

import java.io.Serializable;

@SuppressWarnings("unused")
public class Command implements Serializable
{																						// Argument Structure
	public static final String GET_CONFIG = "GET_CONFIG"; 								//
	public static final String GET_OPTIONS = "GET_OPTIONS"; 							//
	public static final String SET_CONFIG = "SET_CONFIG"; 								// CONFIG !developer!
	public static final String SET_OPTIONS = "SET_OPTIONS"; 							// OPTIONS !developer!
	public static final String SET_NOBODY_HOME_MODE = "SET_NOBODY_HOME_MODE"; 			// BOOL
	public static final String ENABLE_DEVELOPER_SESSION = "ENABLE_DEVELOPER_SESSION"; 	// STRING

	public static final String SET_LIGHT_STATE = "SET_LIGHT_STATE"; 					// LONG BOOL
	public static final String GET_LIGHT_STATE = "GET_LIGHT_STATE"; 					// LONG

	public static final String GET_DISTANCE = "GET_DISTANCE"; 							// LONG
	public static final String SET_DISTANCE = "SET_DISTANCE"; 							// LONG DOUBLE !developer!

	public static final String GET_LIGHT_INTENSITY = "GET_LIGHT_INTENSITY"; 			// LONG
	public static final String SET_LIGHT_INTENSITY = "SET_LIGHT_INTENSITY"; 			// LONG DOUBLE !developer!

	public static final String GET_HEATER_STATE = "GET_HEATER_STATE"; 					// LONG
	public static final String SET_HEATER_STATE = "SET_HEATER_STATE"; 					// LONG BOOL

	public static final String GET_FAN_STATE = "GET_FAN_STATE"; 					// LONG
	public static final String SET_FAN_STATE = "SET_FAN_STATE"; 					// LONG BOOL


	public static final String GET_TEMPERATURE = "GET_TEMPERATURE"; 					// LONG
	public static final String SET_TEMPERATURE = "SET_TEMPERATURE"; 					// LONG DOUBLE !developer!



	public static final String COMMAND_SUCCESSFUL = "Command evaluated successfully";

	private Arguments commandArguments;
	private long id;
	private String name;

	public Command( long id, String name, Arguments args )
	{
		this.commandArguments = args;
		this.id = id;
		this.name = name;
	}

	public long getId()
	{
		return id;
	}

	public Arguments getArguments()
	{
		return commandArguments;
	}

	public String getName()
	{
		return name;
	}

	public boolean isNameEquals( String name )
	{
		return this.name.equals( name );
	}

	public boolean equals( String name, String structure )
	{
		return isNameEquals( name ) && commandArguments.equals( structure );
	}

	@Override
	public String toString()
	{
		StringBuilder sb = new StringBuilder( name );
		sb.append("_COMMAND ");
		sb.append("id = ");
		sb.append( this.id );
		sb.append( commandArguments );
		return sb.toString();
	}
}
