package com.has.apps.windows.ui;

import java.io.IOException;

import com.has.apps.windows.DesktopApplication;
import com.has.base.Options;
import com.has.google.GoogleDriveManager;
import com.has.google.GoogleSessionManager;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

public class IntrusionBoxController
{
	
	@FXML Pane root;
	@FXML Label date;
	@FXML Label time;
	GoogleSessionManager session;
	String path;


	public void init(String text1, String text2, GoogleSessionManager session) {
		date.setText(text1);
		time.setText(text2);
		this.session = session;
	}
	
	@FXML
    void stream(ActionEvent e) {
		String url = GoogleDriveManager.getStreamUrl( date.getText() + " " + time.getText() + ".mp4", session.getAccessToken() );
		
		 WebView browser = new WebView();
         WebEngine engine = browser.getEngine();
         
		 browser.getEngine().load( url );
		 
		 StackPane secondaryLayout = new StackPane();
         secondaryLayout.getChildren().add(browser);
         
         Scene secondScene = new Scene(secondaryLayout, 800, 800);
         
         // New window (Stage)
         Stage newWindow = new Stage();
         newWindow.setTitle(date.getText() + " " + time.getText());
         newWindow.setScene(secondScene);
         newWindow.show();
	}
		
	@FXML
	void download(ActionEvent e) throws IOException {
		Options options = DesktopApplication.getInstance().getOptions();

		GoogleDriveManager.download(date.getText() + " " + time.getText(), session.getAccessToken(),
				options.getLocal().getVideoDownloadPath());
	}
	
	@FXML
    void delete(ActionEvent e) {
		Alert alert = new Alert(AlertType.CONFIRMATION, "Delete Video?", ButtonType.YES, ButtonType.CANCEL);
		alert.showAndWait();

		if (alert.getResult() == ButtonType.YES) {
            //delete from drive
			GoogleDriveManager.delete(date.getText() + " " + time.getText(), session.getAccessToken());
			((Pane) root.getParent()).getChildren().remove(root);
			
		}
		
	}
	
}
