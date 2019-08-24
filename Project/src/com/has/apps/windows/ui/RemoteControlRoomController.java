package com.has.apps.windows.ui;

import java.util.ArrayList;

import com.has.base.Command;
import com.has.base.CommandFactory;
import com.has.base.Configuration;
import com.has.base.Room;
import com.has.device.Device;
import com.has.device.Fan;
import com.has.device.Heater;
import com.has.device.Light;
import com.has.network.Connection;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public class RemoteControlRoomController
{

	@FXML
	VBox lightList;
	@FXML
	TextField roomName;

	ArrayList<Light> lightControllers = new ArrayList<Light>();
	ArrayList<Heater> heaterControllers = new ArrayList<Heater>();
	ArrayList<Fan> fanControllers = new ArrayList<Fan>();


	public void addLight(Light light)
	{
		CheckBox lightSwitch = new CheckBox("Light");
		lightList.getChildren().add(lightSwitch);
		lightSwitch.selectedProperty().addListener(new ChangeListener<Boolean>()
		{
			@Override
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue)
			{
				// TODO CHANGED STATE

			}
		});
	}

	public void init(Room r)
	{
		ArrayList<Device> devices = r.getDevices();
		roomName.setText(r.getName());
		for (Device device : devices)
		{
			if ( device instanceof Light )
			{
				lightControllers.add((Light) device );
			}
			else if ( device instanceof Fan)
			{
				fanControllers.add((Fan) device );
			}
			if ( device instanceof Heater )
			{
				heaterControllers.add((Heater) device );
			}
		}
		for (Light l : lightControllers)
		{
			addLight(l);
		}

	}

}
