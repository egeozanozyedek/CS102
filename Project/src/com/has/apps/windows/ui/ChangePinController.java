package com.has.apps.windows.ui;

import java.util.*;

import com.has.apps.windows.DesktopApplication;
import com.has.base.Options;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.text.Text;
import javafx.stage.Stage;

@SuppressWarnings("unused")
public class ChangePinController {

	private Options options;

	private ArrayList<String> pin;

	private boolean hidden;

	private Text[] chars;

	private boolean authorized;

	private Stage stage;
    
    @FXML
    private Text charOne;

    @FXML
    private Text charTwo;
    
    @FXML
    private Text charThree;

    @FXML
    private Text charFour;

    @FXML
    private Text message;
    
    public ChangePinController()
	{
    	pin = new ArrayList();
    	hidden = false;
    	authorized = false;
    }
    
    public void init( Options options, Stage stage) {
    	chars = new Text[]{charOne, charTwo, charThree, charFour};
    	this.options = options;
    	this.stage = stage;
    }
    
    void input( String in) {
    	if( pin.size() < 4) {
    		pin.add(in);
    		display();
    	}
    }

    void display() {
    		
		if( hidden) {
			for( int i = 0; i < pin.size(); i++)
				chars[i].setText( "*");
		} else {
			for( int i = 0; i < pin.size(); i++)
				chars[i].setText( pin.get(i) + "");
		}
		for( int i = pin.size(); i < 4; i++) {
			chars[i].setText( "_");
		}
		if( authorized) {
			message.setText("Enter Your New Pin");
		} else {
			message.setText("Enter Your Old Pin");
		}
	}

	@FXML
    void zero(ActionEvent event) {
    	input( 0 + "");
    }

    @FXML
    void one(ActionEvent event) {
    	input( 1 + "");
    }
    
    @FXML
    void two(ActionEvent event) {
    	input( 2 + "");
    }
    
    @FXML
    void three(ActionEvent event) {
    	input( 3 + "");
    }

    @FXML
    void four(ActionEvent event) {
    	input( 4 + "");
    }

    @FXML
    void five(ActionEvent event) {
    	input( 5 + "");
    }

    @FXML
    void six(ActionEvent event) {
    	input( 6 + "");
    }
    
    @FXML
    void seven(ActionEvent event) {
    	input( 7 + "");
    }
    
    @FXML
    void eight(ActionEvent event) {
    	input( 8 + "");
    }

    @FXML
    void nine(ActionEvent event) {
    	input( 9 + "");
    }

    @FXML
    void enter(ActionEvent event)
	{
		Options options = DesktopApplication.getInstance().getOptions();

    	if( !authorized )
    	{
    		if( pin.size() == 4 && options.getLocal().getSecurityPin().equals( pin))
    		{
    			authorized = true;
    		}
    		else {
	    		Alert alert = new Alert(AlertType.ERROR, "Pin entered was wrong please retry?", ButtonType.OK);
	    		alert.showAndWait();
	    	}
	    	pin = new ArrayList<String>();
	    	display();
	    	options.createJsonFiles();
    	}
    	else
    	{
    		if( pin.size() == 4)
    			options.getLocal().setSecurityPin(pin);
    		options.createJsonFiles();
    		stage.close();
    	}

    	pin = new ArrayList();
    }

    @FXML
    void delete(ActionEvent event)
	{
    	if( pin.size() > 0) 
    		pin.remove( pin.size() - 1);
    	display();
    }

}

