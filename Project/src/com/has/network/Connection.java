package com.has.network;

import com.has.base.Command;
import com.has.base.Response;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

@SuppressWarnings("unused")
public class Connection
{
	public static final int PORT = 8809;
	private static long lastId = 0;
	private static ArrayList<Response> responsePool = new ArrayList<>();

	private ObjectOutputStream out;
	private ObjectInputStream in;
	private Socket socket;
	private long id;

	private static Response tmp = null; // BAD PROBLEM SOLVING

	public Connection( Socket socket, long id )
	{
		try
		{
			this.id = id;
			this.socket = socket;
			out = new ObjectOutputStream( this.socket.getOutputStream() );
			in = new ObjectInputStream( this.socket.getInputStream() );
		}
		catch ( IOException e )
		{
			e.printStackTrace();
		}
	}

	public long getId()
	{
		return id;
	}

	public Serializable fetch()
	{
		Serializable tmp = null;
		try
		{
			while ( in.available() <= 0 );

			tmp = ( Serializable ) in.readObject();
		}
		catch ( Exception e )
		{
			e.printStackTrace();
		}
		return tmp;
	}

	public void send( Serializable serializable )
	{
		try
		{
			out.writeObject( serializable );
			out.flush();
		}
		catch ( Exception e )
		{
			e.printStackTrace();
		}
	}

	public boolean isClosed()
	{
		return socket.isClosed();
	}

	public void close()
	{
		try
		{
			socket.close();
			out.close();
			in.close();
		}
		catch ( Exception e )
		{
			e.printStackTrace();
		}
	}

	public static Connection waitForClientConnection( int port )
	{
		try
		{
			return new Connection( new ServerSocket( port).accept(), Connection.lastId++ );
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		return null;
	}

	public static Connection waitForServerConnection( String host , int port )
	{
		try
		{
			Connection.lastId++;
			return new Connection( new Socket( host, port), Connection.lastId );
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		return null;
	}



	public static void waitForResponse( Connection c, long timeout )
	{
		new Thread( () -> tmp = ( Response ) c.fetch() ).start();

		try
		{
			Thread.sleep( timeout );
		}
		catch (InterruptedException e)
		{
			e.printStackTrace();
		}

		if ( tmp != null )
			responsePool.add( tmp );
	}

	public static void waitForResponse( Connection c )
	{
		waitForResponse( c,5000 );
	}

	public static Response getFromPoolById( long id )
	{
		for ( Response response : responsePool )
			if ( response.getId() == id )
			{
				responsePool.remove( response );
				return response;
			}

		return null;
	}

}
