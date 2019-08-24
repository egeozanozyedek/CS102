package com.has.apps.linux;

import com.has.base.*;
import com.has.device.*;
import com.has.network.Connection;

import static com.has.base.Arguments.*;
import static com.has.base.Command.*;

import java.io.IOException;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;

public class LinuxApplication
{
	private static final String DEVELOPER_PASSWORD = "123";
	private Options options;
	private Configuration config;
	private ArrayList<Connection> connections;
	private boolean nobodyHomeMode;

	public LinuxApplication()
	{
		try
		{
			options = new Options();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		config = new Configuration();
		connections = new ArrayList<>();
		nobodyHomeMode = false;

		new Thread( new EnvironmentChecker( this ) ).start();

		// WAIT NEW CONNECTION
		while ( true )
		{
			connections.add( Connection.waitForClientConnection( Connection.PORT ) );
			new Thread( new ConnectionListener( this, connections.size() - 1 ) ).start();
		}

	}

	public Connection getConnection( int id )
	{
		return connections.get( id );
	}

	public Options getOptions()
	{
		return options;
	}

	public Configuration getConfig()
	{
		return config;
	}

	public void setConfig(Configuration config)
	{
		this.config = config;
	}

	public void setGlobalOptions( Options options )
	{
		this.options.setGlobal( options.getGlobal() );
	}

	public boolean isNobodyHomeModeEnabled()
	{
		return nobodyHomeMode;
	}

	public void setNobodyHomeMode( boolean nobodyHomeMode )
	{
		this.nobodyHomeMode = nobodyHomeMode;
	}

	public class ConnectionListener implements Runnable
	{
		private LinuxApplication appInstance;
		private int connectionID;
		private boolean developerSession;

		public ConnectionListener( LinuxApplication appInstance, int connectionID )
		{
			this.appInstance = appInstance;
			this.connectionID = connectionID;
			this.developerSession = false;
		}

		@Override
		public void run()
		{
			while ( true )
			{
				Object data = appInstance.getConnection( connectionID ).fetch();
				if ( data instanceof Command )
					appInstance.getConnection( connectionID ).send ( runCommand( ( Command ) data ) );
				else if ( data instanceof Response )
					evaluateResponse( ( Response ) data );
				else
				{
					System.out.println( "Unable to identify fetched object: ");
					System.out.println( data );
				}

			}
		}

		public Response runCommand(Command cmd )
		{
			Arguments args;

			if ( cmd.equals( GET_CONFIG, NO_ARGS ) ) // DONE
			{
				args = new Arguments( STRING + CONFIG );
				args.putOn( 1, appInstance.getConfig() );
				args.putOn( 0 , COMMAND_SUCCESSFUL );
				System.out.println( args.getOn( 0 ) + cmd.toString() );
			}
			else if ( cmd.equals( GET_OPTIONS, NO_ARGS ) ) // DONE
			{
				args = new Arguments( STRING + OPTIONS );
				args.putOn( 1, appInstance.getOptions() );
				args.putOn( 0 , COMMAND_SUCCESSFUL );
				System.out.println( args.getOn( 0 ) + cmd.toString() );
			}
			else if ( cmd.equals( SET_CONFIG, CONFIG ) ) // DONE
			{
				args = new Arguments( STRING );
				if ( developerSession )
				{
					appInstance.setConfig( (Configuration) cmd.getArguments().getOn( 0 ) );
					args.putOn( 0 , COMMAND_SUCCESSFUL );
					System.out.println( args.getOn( 0 ) + cmd.toString()  );
				}
				else
				{
					args.putOn( 0 , "You do not have permission to use this command: " + cmd.getName() );
					System.out.println( args.getOn( 0 ) );
				}
			}
			else if ( cmd.equals( SET_OPTIONS, OPTIONS ) )
			{
				args = new Arguments( STRING );
				if ( developerSession )
				{
					appInstance.setGlobalOptions( ( Options) cmd.getArguments().getOn( 0 ) );
					args.putOn( 0 , COMMAND_SUCCESSFUL );
					System.out.println( args.getOn( 0 ) + cmd.toString()  );
				}
				else
				{
					args.putOn( 0 , "You do not have permission to use this command: " + cmd.getName() );
					System.out.println( args.getOn( 0 ) );
				}
			}
			else if ( cmd.equals( SET_NOBODY_HOME_MODE, BOOL ) )
			{
				args = new Arguments( STRING );
				appInstance.setNobodyHomeMode( ( Boolean ) cmd.getArguments().getOn( 0 ) );

				args.putOn(0, COMMAND_SUCCESSFUL );
				System.out.println(args.getOn(0) + cmd.toString());
			}
			else if ( cmd.equals( ENABLE_DEVELOPER_SESSION, STRING ) )
			{
				args = new Arguments( STRING );
				developerSession = cmd.getArguments().getOn( 0 ).equals( DEVELOPER_PASSWORD );

				args.putOn(0, developerSession ? COMMAND_SUCCESSFUL : "Given pass is incorrect");
				System.out.println(args.getOn(0) + cmd.toString());
			}
			else if ( cmd.equals( SET_LIGHT_STATE, LONG + BOOL ) )
			{
				args = new Arguments( STRING );
				Device device = appInstance.getConfig().getDeviceById( ( Long ) cmd.getArguments().getOn( 0 ) );

				if ( device != null && device instanceof Light )
				{
					( ( Light ) device).setState( (Boolean) args.getOn( 1 ) );
					args.putOn(0, COMMAND_SUCCESSFUL);
					System.out.println(args.getOn(0) + cmd.toString());
				}
				else
				{
					args.putOn(0, "Requested operation is not valid on the device: " + device);
					System.out.println(args.getOn(0));
				}
			}
			else if ( cmd.equals( GET_LIGHT_STATE, LONG ) )
			{
				args = new Arguments( STRING + BOOL );
				Device device = appInstance.getConfig().getDeviceById( ( Long ) cmd.getArguments().getOn( 0 ) );
				boolean state;

				if ( device != null && device instanceof Light )
				{
					state = (( Light ) device).getState();
					args.putOn( 1, state);
					args.putOn(0, COMMAND_SUCCESSFUL);
					System.out.println(args.getOn(0) + cmd.toString());
				}
				else
				{
					args.putOn(0, "Requested operation is not valid on the device: " + device);
					System.out.println(args.getOn(0));
				}
			}
			else if ( cmd.equals( SET_DISTANCE, LONG + DOUBLE ) )
			{
				args = new Arguments( STRING );
				if ( developerSession )
				{
					Device device = appInstance.getConfig().getDeviceById( ( Long ) cmd.getArguments().getOn( 0 ) );

					if ( device != null && device instanceof UltrasonicSensor )
					{
						( ( UltrasonicSensor ) device).setDistance( (Double) args.getOn( 1 ) );
						args.putOn(0, COMMAND_SUCCESSFUL);
						System.out.println(args.getOn(0) + cmd.toString());
					}
					else
					{
						args.putOn(0, "Requested operation is not valid on the device: " + device);
						System.out.println(args.getOn(0));
					}
				}
				else
				{
					args.putOn( 0 , "You do not have permission to use this command: " + cmd.getName() );
					System.out.println( args.getOn( 0 ) );
				}
			}
			else if ( cmd.equals( GET_DISTANCE, LONG ) )
			{
				args = new Arguments( STRING + DOUBLE );
				Device device = appInstance.getConfig().getDeviceById( ( Long ) cmd.getArguments().getOn( 0 ) );
				double distance;

				if ( device != null && device instanceof UltrasonicSensor )
				{
					distance = (( UltrasonicSensor ) device).getDistance();
					args.putOn( 1, distance);
					args.putOn(0, COMMAND_SUCCESSFUL);
					System.out.println(args.getOn(0) + cmd.toString());
				}
				else
				{
					args.putOn(0, "Requested operation is not valid on the device: " + device);
					System.out.println(args.getOn(0));
				}
			}
			else if ( cmd.equals( SET_LIGHT_INTENSITY, LONG + DOUBLE ) )
			{
				args = new Arguments( STRING );
				Device device = appInstance.getConfig().getDeviceById( ( Long ) cmd.getArguments().getOn( 0 ) );

				if ( device != null && device instanceof LightSensor )
				{
					( (LightSensor) device).setIntensity( (Double) args.getOn( 1 ) );
					args.putOn(0, COMMAND_SUCCESSFUL);
					System.out.println(args.getOn(0) + cmd.toString());
				}
				else
				{
					args.putOn(0, "Requested operation is not valid on the device: " + device);
					System.out.println(args.getOn(0));
				}
			}
			else if ( cmd.equals( GET_LIGHT_INTENSITY, LONG ) )
			{
				args = new Arguments( STRING + DOUBLE );
				Device device = appInstance.getConfig().getDeviceById( ( Long ) cmd.getArguments().getOn( 0 ) );
				double intensity;

				if ( device != null && device instanceof LightSensor )
				{
					intensity = (( LightSensor ) device).getIntensity();
					args.putOn( 1, intensity);
					args.putOn(0, COMMAND_SUCCESSFUL);
					System.out.println(args.getOn(0) + cmd.toString());
				}
				else
				{
					args.putOn(0, "Requested operation is not valid on the device: " + device);
					System.out.println(args.getOn(0));
				}
			}
			else if ( cmd.equals( SET_HEATER_STATE, LONG + BOOL ) )
			{
				args = new Arguments( STRING );
				Device device = appInstance.getConfig().getDeviceById( ( Long ) cmd.getArguments().getOn( 0 ) );

				if ( device != null && device instanceof Heater )
				{
					( (Heater) device).setState( (Boolean) args.getOn( 1 ) );
					args.putOn(0, COMMAND_SUCCESSFUL);
					System.out.println(args.getOn(0) + cmd.toString());
				}
				else
				{
					args.putOn(0, "Requested operation is not valid on the device: " + device);
					System.out.println(args.getOn(0));
				}
			}
			else if ( cmd.equals( GET_HEATER_STATE, LONG ) )
			{
				args = new Arguments( STRING + BOOL );
				Device device = appInstance.getConfig().getDeviceById( ( Long ) cmd.getArguments().getOn( 0 ) );
				boolean state;

				if ( device != null && device instanceof Heater )
				{
					state = (( Heater) device).getState();
					args.putOn( 1, state);
					args.putOn(0, COMMAND_SUCCESSFUL);
					System.out.println(args.getOn(0) + cmd.toString());
				}
				else
				{
					args.putOn(0, "Requested operation is not valid on the device: " + device);
					System.out.println(args.getOn(0));
				}
			}

			else if ( cmd.equals( SET_FAN_STATE, LONG + BOOL ) )
			{
				args = new Arguments( STRING );
				Device device = appInstance.getConfig().getDeviceById( ( Long ) cmd.getArguments().getOn( 0 ) );

				if ( device != null && device instanceof Fan )
				{
					( (Fan) device).setState( (Boolean) args.getOn( 1 ) );
					args.putOn(0, COMMAND_SUCCESSFUL);
					System.out.println(args.getOn(0) + cmd.toString());
				}
				else
				{
					args.putOn(0, "Requested operation is not valid on the device: " + device);
					System.out.println(args.getOn(0));
				}
			}
			else if ( cmd.equals( GET_FAN_STATE, LONG ) )
			{
				args = new Arguments( STRING + BOOL );
				Device device = appInstance.getConfig().getDeviceById( ( Long ) cmd.getArguments().getOn( 0 ) );
				boolean state;

				if ( device != null && device instanceof Fan )
				{
					state = (( Fan) device).getState();
					args.putOn( 1, state);
					args.putOn(0, COMMAND_SUCCESSFUL);
					System.out.println(args.getOn(0) + cmd.toString());
				}
				else
				{
					args.putOn(0, "Requested operation is not valid on the device: " + device);
					System.out.println(args.getOn(0));
				}
			}





			else if ( cmd.equals( SET_TEMPERATURE, LONG + DOUBLE ) )
			{
				args = new Arguments( STRING );

				if ( developerSession )
				{
					Device device = appInstance.getConfig().getDeviceById( ( Long ) cmd.getArguments().getOn( 0 ) );

					if ( device != null && device instanceof TempSensor )
					{
						( (TempSensor) device).setTemperature( (Double) args.getOn( 1 ) );
						args.putOn(0, COMMAND_SUCCESSFUL);
						System.out.println(args.getOn(0) + cmd.toString());
					}
					else
					{
						args.putOn(0, "Requested operation is not valid on the device: " + device);
						System.out.println(args.getOn(0));
					}
				}
				else
				{
					args.putOn( 0 , "You do not have permission to use this command: " + cmd.getName() );
					System.out.println( args.getOn( 0 ) );
				}

			}
			else if ( cmd.equals( GET_TEMPERATURE, LONG ) )
			{
				args = new Arguments( STRING + DOUBLE );
				Device device = appInstance.getConfig().getDeviceById( ( Long ) cmd.getArguments().getOn( 0 ) );
				double temp;

				if ( device != null && device instanceof TempSensor )
				{
					temp = (( TempSensor) device).getTemperature();
					args.putOn( 1, temp);
					args.putOn(0, COMMAND_SUCCESSFUL);
					System.out.println(args.getOn(0) + cmd.toString());
				}
				else
				{
					args.putOn(0, "Requested operation is not valid on the device: " + device);
					System.out.println(args.getOn(0));
				}
			}
			else
			{
				args = new Arguments( STRING );
				args.putOn( 0 , "Could not parse command: " + cmd.toString() );
				System.out.println( args.getOn( 0 ) );
			}

			System.out.println("args: " + args.toString() );
			return new Response( cmd.getId(), cmd.getName(), args );
		}

		public void evaluateResponse( Response response )
		{

		}
	}

	public class EnvironmentChecker implements Runnable
	{
		private LinuxApplication appInstance;

		public EnvironmentChecker( LinuxApplication appInstance )
		{
			this.appInstance = appInstance;
		}

		@Override
		public void run()
		{
			while ( true )
			{
				for ( Device device : appInstance.getConfig().getDevices() )
				{
					if ( device instanceof UltrasonicSensor )
					{
						if (((UltrasonicSensor) device).isIntrusionDetected())
						{
							String title = "Intrusion detected at you home!";
							String body = "Time stamp " + new Timestamp(System.currentTimeMillis());
							EmailUtils.sendFromGMail((String) appInstance.getOptions().getGlobal().getNotificationEmail() , title, body);

						}
					}
				}
			}
		}
	}

}
