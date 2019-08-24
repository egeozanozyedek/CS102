package com.has.apps.windows.ui;

import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import com.google.api.services.drive.model.File;
import com.has.apps.windows.DesktopApplication;
import com.has.google.GoogleDriveManager;
import com.has.google.GoogleSessionManager;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class IntrusionHistoryController
{

	@FXML
	VBox intrusionList;
	Pane toAdd;
	IntrusionBoxController controller;
	GoogleSessionManager session;
	ArrayList<File> files;

	@FXML
	public void addIntrusion(File f) throws IOException
	{
		FXMLLoader loader;
		String name = f.getTitle();

		int i = name.indexOf(' ');
		System.out.println(i + "\n" + name);
		String date = name.substring(0, i);
		String time = name.substring(i + 1, name.length() - 4);

		String folder = DesktopApplication.getParent(getClass().getResource("IntrusionHistoryController.class") + "", 9);
		folder += "/res/fxml/IntrusionBox.fxml";
		System.out.println(folder);
		loader = new FXMLLoader(new URL(folder));
		toAdd = loader.load();
		controller = loader.<IntrusionBoxController>getController();

		intrusionList.getChildren().add(0, toAdd);
		controller.init(date, time, session);
	}

	public void init(GoogleSessionManager session) throws IOException
	{
		this.session = session;
		if (session != null)
		{
			System.out.println("helloboooeojweif");
		}
		files = GoogleDriveManager.getListOfAllFiles(session.getAccessToken());
		for (File f : files)
		{
			if (f.getTitle().endsWith(".mp4"))
			{
				addIntrusion(f);
			}
		}
	}

}
