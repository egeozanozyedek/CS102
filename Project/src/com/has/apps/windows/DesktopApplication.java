package com.has.apps.windows;

import com.has.apps.windows.ui.LoginController;
import com.has.base.Configuration;
import com.has.base.Options;
import com.has.google.GoogleSessionManager;
import com.has.network.Connection;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class DesktopApplication
{
	private static DesktopApplication instance;

	private Options options;
	private Configuration config;
	private Connection connection;
	private GoogleSessionManager session;




	public static class MainApplication extends Application
	{
		private Pane login;
		private LoginController controller;
		private FXMLLoader loader;
		private static Stage pStage;

		@Override
		public void start(Stage primaryStage) throws Exception
		{
			pStage = primaryStage;
			String folder = getParent(getClass().getResource( "DesktopApplication.class" ) + "", 8) + "/res/fxml/Login.fxml";
			//System.out.println(getClass().getResource( "DesktopApplication.class" ));
			loader = new FXMLLoader( new URL( folder));
			login = loader.load();
			controller = loader.getController();

			controller.init( primaryStage);
			Scene scene = new Scene(login,1200, 675);

			folder = getParent(getClass().getResource( "DesktopApplication.class" ) + "", 8) + "/res/css/application.css";
			System.out.println( getParent(getClass().getResource( "DesktopApplication.class" ) + "", 8) + "/res/css/application.css" );
			scene.getStylesheets().add( folder);
			primaryStage.setScene(scene);
			primaryStage.show();
			System.out.println("focuse" + Options.EmailFrequency.values().length);
			for( int i = 0; i <Options.EmailFrequency.values().length; i++ ) {
				System.out.println( Options.EmailFrequency.values()[i]);
			}
		}
		public static void setPrimarayStage( Stage stage ) {
			System.out.println( "pstage is set");
			pStage = stage;
		}

		public static Stage getPrimaryStage() {
			return pStage;
		}
	}

	public DesktopApplication()
	{
		try
		{
			this.options = new Options();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		this.config = new Configuration();
		instance = this;
		Application.launch( DesktopApplication.MainApplication.class , new String[]{} );
	}


	public static DesktopApplication getInstance()
	{
		return instance;
	}

	public Configuration getConfig()
	{
		return config;
	}

	public Options getOptions()
	{
		return options;
	}

	public Connection getConnection()
	{
		return connection;
	}

	public GoogleSessionManager getSession()
	{
		return session;
	}

	public static String getParent(String resourcePath) {
		int index = resourcePath.lastIndexOf('/');
		if (index > 0) {
			return resourcePath.substring(0, index);
		}
		return "/";
	}

	public static String getParent(String resourcePath, int n) {
		String temp = resourcePath;
		for( int i = 0; i < n; i++) {
			temp = getParent( temp);
		}
		return temp;
	}
}
