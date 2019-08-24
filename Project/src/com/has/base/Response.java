package com.has.base;

import java.io.Serializable;

public class Response implements Serializable
{
	private long commandId;
	private String commandName;
	private Arguments content;

	public Response( long id, String name, Arguments args )
	{
		this.commandId = id;
		this.commandName = name;
		this.content = args;
	}

	public Arguments getArguments()
	{
		return content;
	}

	public long getId()
	{
		return commandId;
	}

	public String getName()
	{
		return commandName;
	}

}

