package com.uom.central_node;

import java.io.IOException;

import com.uom.central_node.model.Device;
import com.uom.central_node.services.RegisterDeviceHandler;
import com.uom.central_node.services.RegisterDeviceServer;
import com.uom.central_node.view.DeviceOverviewController;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class HydraCN extends Application {
	
	private Stage primaryStage;
    private BorderPane rootLayout;
    
	private ObservableList<Device> deviceData = FXCollections.observableArrayList();
	
	public ObservableList<Device> getDeviceData() {
        return deviceData;
    }
	
	public HydraCN() {
		
	}
	
	@Override
	public void start(Stage primaryStage) {
		this.primaryStage = primaryStage;
        this.primaryStage.setTitle("AddressApp");

        initRootLayout();

        showDeviceFeeds();

		RegisterDeviceHandler.hydraCN = this;
        RegisterDeviceServer registerDeviceServer = new RegisterDeviceServer();
		registerDeviceServer.start();
	}

	public void initRootLayout() {
        try {
            // Load root layout from fxml file.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(HydraCN.class.getResource("view/RootLayout.fxml"));
            rootLayout = (BorderPane) loader.load();

            // Show the scene containing the root layout.
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
	
	public void showDeviceFeeds() {
        try {
            // Load person overview.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(HydraCN.class.getResource("view/DeviceOverview.fxml"));
            AnchorPane personOverview = (AnchorPane) loader.load();

            // Set person overview into the center of root layout.
            rootLayout.setCenter(personOverview);
            
            DeviceOverviewController controller = loader.getController();
            controller.setMainApp(this);
            
    		
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
	
	public static void main(String[] args) {
		launch(args);
	}
}
