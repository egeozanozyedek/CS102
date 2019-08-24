package com.has.apps.windows.ui;

import java.io.IOException;
import java.net.URL;
import java.sql.DriverManager;

import com.has.apps.windows.DesktopApplication;
import com.has.base.Configuration;
import com.has.base.Options;
import com.has.google.*;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

public class MainGUIController
{

	@FXML
	BorderPane root;

	Pane liveFeed;
	Pane realTimeData;
	Pane sensorInformation;
	Pane remoteControl;
	Pane intrusionHistory;
	Pane help;
	Pane settings;

	LiveFeedController liveFeedcontroller;
	RealTimeDataController realTimeDataController;
	SensorInformationController sensorInformationController;
	RemoteControlController remoteControlController;
	IntrusionHistoryController intrusionHistoryController;
	HelpController helpController;
	SettingsController settingsController;

	GoogleSessionManager session;


	public MainGUIController() throws Exception
	{
		FXMLLoader loader;
		Configuration configuration = new Configuration();

		String folder = DesktopApplication.getParent(getClass().getResource("MainGUIController.class") + "", 9);
		folder += "/res/fxml/LiveFeed.fxml";
		loader = new FXMLLoader(new URL(folder));
		liveFeed = loader.load();
		liveFeedcontroller = loader.<LiveFeedController>getController();

		folder = DesktopApplication.getParent(getClass().getResource("MainGUIController.class") + "", 9);
		folder += "/res/fxml/RealTimeData.fxml";
		loader = new FXMLLoader(new URL(folder));
		realTimeData = loader.load();
		realTimeDataController = loader.<RealTimeDataController>getController();
		realTimeDataController.init();

		folder = DesktopApplication.getParent(getClass().getResource("MainGUIController.class") + "", 9);
		folder += "/res/fxml/SensorInformation.fxml";
		loader = new FXMLLoader(new URL(folder));
		sensorInformation = loader.load();
		sensorInformationController = loader.<SensorInformationController>getController();

		folder = DesktopApplication.getParent(getClass().getResource("MainGUIController.class") + "", 9);
		folder += "/res/fxml/RemoteControl.fxml";
		loader = new FXMLLoader(new URL(folder));
		remoteControl = loader.load();
		remoteControlController = loader.<RemoteControlController>getController();
		remoteControlController.init();

		folder = DesktopApplication.getParent(getClass().getResource("MainGUIController.class") + "", 9);
		folder += "/res/fxml/IntrusionHistory.fxml";
		loader = new FXMLLoader(new URL(folder));
		intrusionHistory = loader.load();
		intrusionHistoryController = loader.<IntrusionHistoryController>getController();

		folder = DesktopApplication.getParent(getClass().getResource("MainGUIController.class") + "", 9);
		folder += "/res/fxml/Help.fxml";
		loader = new FXMLLoader(new URL(folder));
		help = loader.load();
		helpController = loader.<HelpController>getController();

		folder = DesktopApplication.getParent(getClass().getResource("MainGUIController.class") + "", 9);
		folder += "/res/fxml/Settings.fxml";
		loader = new FXMLLoader(new URL(folder));
		settings = loader.load();
		settingsController = loader.<SettingsController>getController();
		settingsController.init();

	}

	void init(GoogleSessionManager session) throws IOException
	{
		this.session = session;
		intrusionHistoryController.init(session);

	}


	@FXML
	void openLiveFeed(ActionEvent e)
	{
		root.setCenter(liveFeed);
	}

	@FXML
	void openRealTimeData(ActionEvent e)
	{
		root.setCenter(realTimeData);
	}

	@FXML
	void openRemoteControl(ActionEvent e) throws IOException
	{
		root.setCenter(remoteControl);
	}

	@FXML
	void openIntrusionHistory(ActionEvent e) throws IOException
	{
		root.setCenter(intrusionHistory);
//  intrusionHistoryController.addIntrusion();

	}

	@FXML
	void openSensorInformation(ActionEvent e) throws IOException
	{
		root.setCenter(sensorInformation);
	}

	@FXML
	void openHelp(ActionEvent e) throws IOException
	{
		root.setCenter(help);
	}

	@FXML
	void openSettings(ActionEvent e) throws IOException
	{
		root.setCenter(settings);
	}

}
