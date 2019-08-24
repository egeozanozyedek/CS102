package com.has.base;

import java.io.Serializable;

@SuppressWarnings( "unused" )
public class Arguments implements Serializable
{
	public static final String NO_ARGS = "";
	// PRIMITIVE
	public static final String STRING = "STRING ";
	public static final String INT = "INT ";
	public static final String BOOL = "BOOL ";
	public static final String LONG = "LONG ";
	public static final String DOUBLE = "DOUBLE ";
	// ADVANCED
	public static final String CONFIG = "CONFIG ";
	public static final String OPTIONS = "OPTIONS ";

	private String[] structure;
	private Object[] data;
	private int count;

	public Arguments( String structure )
	{
		if ( structure == null || structure.length() == 0 )
			return;

		structure = structure.trim().toUpperCase();

		this.structure = structure.split( " " );
		this.data = new Object[ this.structure.length ];
		this.count = this.structure.length;
	}

	public Arguments()
	{
		this( NO_ARGS );
	}

	public int count()
	{
		return this.count;
	}

	public Arguments putOn( int i, Object o )
	{
		if ( i > -1 && i < this.count )
			this.data[ i ] = o;
		return this;
	}

	public Object getOn( int i )
	{
		if ( i > -1 && i < this.count )
			return this.data[ i ];
		return null;
	}

	@Override
	public String toString()
	{
		StringBuilder sb = new StringBuilder( "{" );

		if ( this.count() == 0 )
			return "{}";

		for ( int i = 0; i < this.count - 1; i++ )
		{
			sb.append( "'" );
			sb.append( this.structure[i] );
			sb.append( "':'" );
			sb.append( this.data[i] );
			sb.append( "', " );
		}

		sb.append( "'" );
		sb.append( this.structure[ this.count - 1 ] );
		sb.append( "':'" );
		sb.append( this.data[ this.count - 1 ] );
		sb.append( "'}" );

		return sb.toString();
	}

	public boolean equals( String str )
	{
		StringBuilder sb = new StringBuilder("");

		for ( int i = 0; i < count; i++ )
		{
			sb.append(structure[i]);
			sb.append(" ");
		}

		return sb.toString().equals( str );
	}
}






