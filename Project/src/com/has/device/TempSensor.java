package com.has.device;

public class TempSensor extends Device
{
	private double temp;

	public TempSensor( long id, String name, int[] pins )
	{
		super(id, name, pins);
		temp = 0;
	}

	public double getTemperature()
	{
		return temp;
	}

	public void setTemperature( double temp )
	{
		this.temp = temp;
	}

	@Override
	public String toString()
	{
		return "TempSensor.{name = " + name + " id = " + id + " temperature = " + temp + "}\n";
	}
}
