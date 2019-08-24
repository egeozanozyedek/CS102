package com.has.apps.windows.ui;

import com.has.apps.windows.DesktopApplication;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Worker.State;
import javafx.concurrent.*;
import javafx.concurrent.Worker.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import com.has.google.*;

public class LoginController
{
	public Stage stage;

	public void init(Stage stage)
	{
		this.stage = stage;

	}

	@FXML
	public void login(ActionEvent event)
	{
		WebView browser = new WebView();
		WebEngine engine = browser.getEngine();
		engine.load(GoogleSessionManager.AUTH_URL);

		StackPane secondaryLayout = new StackPane();
		secondaryLayout.getChildren().add(browser);

		Scene secondScene = new Scene(secondaryLayout, 800, 800);

		// New window (Stage)
		Stage newWindow = new Stage();
		newWindow.setTitle("Login");
		newWindow.setScene(secondScene);


		// Set position of second window, related to primary window.
		//newWindow.setX(primaryStage.getX() - 100);
		//newWindow.setY(primaryStage.getY() - 100);

		newWindow.show();
		browser.getEngine().getLoadWorker().stateProperty().addListener(new ChangeListener<State>()
		{
			@Override
			public void changed(ObservableValue ov, State oldState, State newState)
			{
				GoogleSessionManager session = DesktopApplication.getInstance().getSession();

				String code = null;
				if (newState == Worker.State.SUCCEEDED)
				{
					code = browser.getEngine().getTitle(); //New  Title
				}
				if (code != null)
					if (code.substring(0, 7).equals("Success"))
					{
						code = code.substring(13);
						session = new GoogleSessionManager(code);
					}
				System.out.println(session);
				if (session != null)
				{
					String folder = DesktopApplication.getParent(getClass().getResource("LoginController.class") + "", 9);
					folder += "/res/fxml/MainGUI.fxml";
					FXMLLoader loader = null;
					try
					{
						loader = new FXMLLoader(new URL(folder));
					}
					catch (MalformedURLException e1)
					{
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					try
					{
						System.out.println("not null in login controller");
						Pane mainGUI = loader.load();
						MainGUIController controller = loader.<MainGUIController>getController();
						controller.init(session);
						Scene scene = new Scene(mainGUI, 1000, 675);
						folder = DesktopApplication.getParent(getClass().getResource("LoginController.class") + "", 9);
						folder += "/res/css/application.css";
						scene.getStylesheets().add(folder);
						stage.setScene(scene);
						stage.show();
					}
					catch (IOException e)
					{
						// TODO Auto-generated catch block
						e.printStackTrace();
					}


				}
			}
		});


	}

}
