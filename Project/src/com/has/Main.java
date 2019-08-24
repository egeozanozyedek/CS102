package com.has;

import com.has.apps.linux.LinuxApplication;
import com.has.apps.windows.DesktopApplication;
import com.has.base.*;
import com.has.device.Fan;
import com.has.device.GasSensor;
import com.has.device.Heater;
import com.has.network.Connection;

import java.io.IOException;

public class Main
{
    public static void main(String[] args)
    {
    	// Bypass this to run debug
    	 args = new String[]{ "-d" };

		 /*
		new Thread( () ->
		{
			LinuxApplication app = new LinuxApplication();
		}).start();

		new Thread( () ->
		{
			// DesktopApplication desktopApplication = new DesktopApplication();
			Connection connection = Connection.waitForServerConnection("localhost", Connection.PORT);

			Command command = CommandFactory.createGetConfigCommand();
			System.out.println(command);
			connection.send( command );

			Response response = ( Response ) connection.fetch();
			Configuration configuration = (Configuration) response.getArguments().getOn( 1 );

			System.out.println( configuration.getRooms() );

		}).start();

		// */
		// /*


		if ( args != null && args.length != 0 )
		{
			if ( args[0].equals("-l") )
			{
				// RUNS THE LINUX APPLICATION
				LinuxApplication application = new LinuxApplication();
			}
			else if ( args[0].equals("-e") )
			{
				// RUNS THE EMULATOR APPLICATION
			}
			else if ( args[0].equals("-d") )
			{
				DesktopApplication application = new DesktopApplication();
				// RUNS THE DESKTOP APPLICATION
			}
			else
			{
				System.out.println( "Program run type is not valid" );
				System.out.println( "Allowed types are '-d': desktop, '-l' : linux, '-e' : emulator" );
			}
		}
		else
		{
			System.out.println( "Program run type is not specified!" );
			System.out.println( "Allowed types are '-d': desktop, '-l' : linux, '-e' : emulator" );
		}
		System.out.println( "Program stopped" );


		Configuration configuration = new Configuration();
		Options options = null;
		try
		{
			options = new Options();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}

		//EmailUtils.sendFromGMail(options.getGlobal().getNotificationEmail(), "HELLO THIS IS A MESSAGE FROM HAS SERVICES", "You are fucked");
		// */

		// System.out.println( configuration.getDevices().size() );
		// configuration.getDevices().remove( 6 );
    }
}
