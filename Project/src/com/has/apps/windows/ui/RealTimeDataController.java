package com.has.apps.windows.ui;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Timer;
import java.util.TimerTask;

import com.has.apps.windows.DesktopApplication;
import com.has.base.Command;
import com.has.base.CommandFactory;
import com.has.base.Response;
import com.has.device.Device;
import com.has.device.GasSensor;
import com.has.device.TempSensor;
import com.has.network.Connection;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

public class RealTimeDataController
{

	@FXML
	GridPane boxList;
	ArrayList<LiveTemperatureBoxController> tempBoxControllers;
	ArrayList<LiveGasBoxController> gasBoxControllers;
	int updateIndex;

	Timer timer = new Timer();
	TimerTask dataRefresher = new TimerTask()
	{

		@Override
		public void run()
		{
			if (updateIndex < tempBoxControllers.size())
			{
				tempBoxControllers.get(updateIndex).update();
			} else if (updateIndex < tempBoxControllers.size() + gasBoxControllers.size() - 1)
			{
				gasBoxControllers.get(updateIndex - tempBoxControllers.size()).update();
			} else
			{
				updateIndex = 0;
			}
			updateIndex += 1;
		}


	};


	public void init() throws IOException
	{
		boxList.setHgap(50);
		boxList.setVgap(50);
		tempBoxControllers = new ArrayList<>();
		gasBoxControllers = new ArrayList<>();
		timer.schedule(dataRefresher, 100000);

		for (Device device : DesktopApplication.getInstance().getConfig().getDevices() )
		{
			if (device instanceof TempSensor)
			{
				addTemperatureBox(device);
			} else if (device instanceof GasSensor)
			{
				addGasBox(device);
			}
		}
	}


	public void addTemperatureBox(Device device) throws IOException
	{
		FXMLLoader loader;
		String folder = DesktopApplication.getParent(getClass().getResource("RealTimeDataController.class") + "", 9);
		folder += "/res/fxml/LiveTemperatureBox.fxml";
		loader = new FXMLLoader(new URL(folder));
		Pane toAdd = loader.load();
		LiveTemperatureBoxController controller = loader.<LiveTemperatureBoxController>getController();
		controller.init(device);
		boxList.add(toAdd, 0, tempBoxControllers.size());
		tempBoxControllers.add(controller);
	}

	public void addGasBox(Device device) throws IOException
	{
		FXMLLoader loader;
		String folder = DesktopApplication.getParent(getClass().getResource("RealTimeDataController.class") + "", 9);
		folder += "/res/fxml/LiveGasBox.fxml";
		loader = new FXMLLoader(new URL(folder));
		Pane toAdd = loader.load();
		LiveGasBoxController controller = loader.<LiveGasBoxController>getController();
		controller.init(device);
		boxList.add(toAdd, 1, gasBoxControllers.size());
		gasBoxControllers.add(controller);

	}


}
