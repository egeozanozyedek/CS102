package com.has.apps.windows.ui;

import com.has.apps.windows.DesktopApplication;
import com.has.base.Options;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

import java.io.File;
import java.net.URL;

public class SettingsController
{
	@FXML
	private TextField videoLimit;

	@FXML
	private CheckBox sendEmailNotification;

	@FXML
	private TextField startingHour;

	@FXML
	private TextField smartTemperature;

	@FXML
	private TextField videoDownloadPath;

	@FXML
	private TextField endingHour;

	@FXML
	private TextField videoLength;

	@FXML
	private TextField endingMinutes;

	@FXML
	private ComboBox<Options.EmailFrequency> emailFrequency;

	@FXML
	private TextField startingMinutes;

	@FXML
	private TextField notificationEmail;

	public void init()
	{
		Options options = DesktopApplication.getInstance().getOptions();

		videoLimit.setText(options.getGlobal().getGoogleDriveVideosSizeLimit() + "");
		sendEmailNotification.setSelected(options.getGlobal().getSendEmailNotifications());
		System.out.println(options);
		System.out.println(options.getGlobal());
		System.out.println(options.getGlobal().getSleepingTime());

		startingHour.setText((options.getGlobal().getSleepingTime().getStartingHour() + ""));
		endingHour.setText((options.getGlobal().getSleepingTime().getEndingHour() + ""));
		endingMinutes.setText((options.getGlobal().getSleepingTime().getEndingMinute() + ""));
		startingMinutes.setText((options.getGlobal().getSleepingTime().getStartingMinute() + ""));


		smartTemperature.setText(options.getGlobal().getTemperatureForSmartControl() + "");
		videoDownloadPath.setText(options.getLocal().getVideoDownloadPath());

		videoLength.setText(options.getGlobal().getIntrusionVideoLength() + "");
		notificationEmail.setText((String) options.getGlobal().getNotificationEmail());
		//emailFrequency = new ComboBox();

		emailFrequency.getItems().setAll(Options.EmailFrequency.values());
		emailFrequency.setValue(Options.EmailFrequency.values()[(options.getGlobal().getEmailFrequency())] );

	}

	@FXML
	public void handleComboBoxAction()
	{
		Options options = DesktopApplication.getInstance().getOptions();


		options.getGlobal().setEmailFrequency( emailFrequency.getSelectionModel().getSelectedItem().getValue() );
		//EmailFrequency selectedPerson = emailFrequency.getSelectionModel().getSelectedItem();
	}

	@FXML
	public void save()
	{
		Options options = DesktopApplication.getInstance().getOptions();

		int sh = Integer.parseInt(startingHour.getText());
		int sm = Integer.parseInt(startingMinutes.getText());
		int eh = Integer.parseInt(endingHour.getText());
		int em = Integer.parseInt(endingMinutes.getText());

		options.getGlobal().getSleepingTime().setStartingHour(sh);
		options.getGlobal().getSleepingTime().setEndingHour(eh);
		options.getGlobal().getSleepingTime().setEndingMinute(em);
		options.getGlobal().getSleepingTime().setStartingMinute(sm);

		options.getGlobal().setSendEmailNotifications(sendEmailNotification.isSelected());
		options.getGlobal().setGoogleDriveVideosSizeLimit(Integer.parseInt(videoLimit.getText()));
		options.getGlobal().setNotificationEmail(notificationEmail.getText());
		options.getGlobal().setEmailFrequency(emailFrequency.getSelectionModel().getSelectedIndex());
		options.getGlobal().setIntrusionVideoLength(Integer.parseInt(videoLength.getText()));
		options.getGlobal().setTemperatureForSmartControl(Integer.parseInt(smartTemperature.getText()));

		options.getLocal().setVideoDownloadPath(videoDownloadPath.getText());
		options.createJsonFiles();
	}

	@FXML
	public void changePin()
	{
		try
		{
			Options options = DesktopApplication.getInstance().getOptions();
			FXMLLoader loader;
			String folder = DesktopApplication.getParent(getClass().getResource("SettingsController.class") + "", 9) + "/res/fxml/ChangePin.fxml";

			loader = new FXMLLoader(new URL(folder));
			Pane toDisplay = loader.load();

			ChangePinController controller = loader.getController();

			Stage newWindow = new Stage();

			controller.init(options, newWindow);

			Scene secondScene = new Scene(toDisplay, 263, 400);

			newWindow.setTitle("Change Pin");
			newWindow.setScene(secondScene);
			newWindow.show();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}


	}

	@FXML
	public void showChooseFolderDialog()
	{
		DirectoryChooser directoryChooser = new DirectoryChooser();
		directoryChooser.setTitle("Select Download Path for videos");
		File defaultDirectory = new File(videoDownloadPath.getText());
		directoryChooser.setInitialDirectory(defaultDirectory);
		File selectedDirectory = directoryChooser.showDialog(DesktopApplication.MainApplication.getPrimaryStage());
		videoDownloadPath.setText(selectedDirectory.getAbsolutePath());
	}
}
