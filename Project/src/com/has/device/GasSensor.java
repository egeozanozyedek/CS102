package com.has.device;

@SuppressWarnings("unused")
public class GasSensor extends Device
{

	private double lpg;
	private double methane;
	private double ethane;
	private double propane;
	private double co;
	private double smoke;

	public GasSensor( int id, String displayName, int[] pin )
	{
		super( id, displayName, pin );
		lpg = 0.0d;
		methane = 0.0d;
		ethane = 0.0d;
		propane = 0.0d;
		co = 0.0d;
		smoke = 0.0d;
	}

	public double Concentration()
	{
		return lpg;
	}

	public double getMethaneConcentration()
	{
		return methane;
	}

	public double getEthaneConcentration()
	{
		return ethane;
	}

	public double getPropaneConcentration()
	{
		return propane;
	}

	public double getCOConcentration()
	{
		return co;
	}

	public double getSmokeConcentration()
	{
		return smoke;
	}

	public void setLPGConcentration( double lpg )
	{
		this.lpg = lpg;
	}

	public void setMethaneConcentration( double methane )
	{
		this.methane = methane;
	}

	public void setEthaneConcentration( double ethane )
	{
		this.ethane = ethane;
	}

	public void setPropaneConcentration( double propane )
	{
		this.propane = propane;
	}

	public void setCOConcentration( double co )
	{
		this.co = co;
	}

	public void setSmokeConcentration( double smoke )
	{
		this.smoke = smoke;
	}

	@Override
	public String toString()
	{
		return "GasSensor.{name = " + name + " id = " + id + "}\n";
	}
}
