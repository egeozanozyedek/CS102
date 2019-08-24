package com.has.apps.windows.ui;

import java.util.Timer;
import java.util.TimerTask;

import com.has.base.Command;
import com.has.base.CommandFactory;
import com.has.base.Response;
import com.has.device.Device;
import com.has.network.Connection;

import javafx.fxml.FXML;

public class LiveTemperatureBoxController {

	Device device;


	public void update()
	{

// TODO
	}

	
	@FXML
	void viewDetails() {}
	
	void init( Device device) {
		this.device = device;
	}
}
