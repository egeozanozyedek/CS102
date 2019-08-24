package com.has.apps.windows.ui;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

import com.has.apps.windows.DesktopApplication;
import com.has.base.Configuration;
import com.has.base.Room;
import com.has.device.Device;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;

public class RemoteControlController {

	@FXML
	FlowPane roomList;
	
	public void addRoom( Room r, Configuration configuration) throws IOException
	{
		String folder = DesktopApplication.getParent(getClass().getResource( "RemoteControlController.class" ) + "", 9);
   		folder += "/res/fxml/RemoteControlRoom.fxml";

		FXMLLoader loader = new FXMLLoader( new URL( folder));
		Pane toAdd = loader.load();
		RemoteControlRoomController controller = loader.getController();
		controller.init( r );
		roomList.getChildren().add(toAdd);
	}
	
	public void init()
	{
		for( Room r : DesktopApplication.getInstance().getConfig().getRooms() )
		{
			try {
				addRoom( r, DesktopApplication.getInstance().getConfig() );
			} catch (IOException e)
			{
				e.printStackTrace();
			}
		}
	}
 	
}
