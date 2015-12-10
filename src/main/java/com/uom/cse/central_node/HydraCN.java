package com.uom.cse.central_node;

import java.io.IOException;

import com.uom.cse.central_node.model.Device;
import com.uom.cse.central_node.model.FilterData;
import com.uom.cse.central_node.model.ProcessInfo;
import com.uom.cse.central_node.services.RegisterDeviceHandler;
import com.uom.cse.central_node.services.RegisterDeviceServer;
import com.uom.cse.central_node.view.AllDeviceFilterController;
import com.uom.cse.central_node.view.DataViewerController;
import com.uom.cse.central_node.view.DeviceOverviewController;
import com.uom.cse.central_node.view.FilterDetailsController;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class HydraCN extends Application {
	
	private Stage primaryStage;
    private BorderPane rootLayout;
    
	private ObservableList<Device> deviceData = FXCollections.observableArrayList();
	private ObservableList<ProcessInfo> infoData = FXCollections.observableArrayList();
	private ObservableList<FilterData> filterData = FXCollections.observableArrayList();
	
	public ObservableList<Device> getDeviceData() {
        return deviceData;
    }
	
	public ObservableList<FilterData> getFilterData() {
        return filterData;
    }
	
	public void setInfoData(ObservableList<ProcessInfo> infoData) {
        this.infoData = infoData;
    }
	
	public ObservableList<ProcessInfo> getInfoData() {
        return infoData;
    }
	
	public HydraCN() {
		
	}
	
	@Override
	public void start(Stage primaryStage) {
		//set primary stage
		this.primaryStage = primaryStage;
		//set title
        this.primaryStage.setTitle("HydraCN");

        initRootLayout();

        showDeviceFeeds();

		RegisterDeviceHandler.hydraCN = this;
        RegisterDeviceServer registerDeviceServer = new RegisterDeviceServer();
		registerDeviceServer.start();
	}

	@Override
	public void stop() throws Exception {
		// TODO Auto-generated method stub
		RegisterDeviceServer.stopServer();
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
	
	public void showDataViewer(){
		try {
            // Load the fxml file and create a new stage for the popup dialog.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(HydraCN.class.getResource("view/DataViewer.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            // Create the dialog Stage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Data Viewer");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            // Set the person into the controller.
            DataViewerController controller = loader.getController();
            controller.setDialogStage(dialogStage);

            // Show the dialog and wait until the user closes it
            dialogStage.showAndWait();

        } catch (IOException e) {
            e.printStackTrace();
        }
	}
	
	public void showAllDeviceFilter(){
		try {
            // Load the fxml file and create a new stage for the popup dialog.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(HydraCN.class.getResource("view/AllDeviceFilter.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            // Create the dialog Stage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Filter Viewer");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            // Set the person into the controller.
            AllDeviceFilterController controller = loader.getController();
            controller.setDialogStage(dialogStage);

            // Show the dialog and wait until the user closes it
            dialogStage.showAndWait();

        } catch (IOException e) {
            e.printStackTrace();
        }
	}
	
	public void showFilterDetails(){
		try {
            // Load the fxml file and create a new stage for the popup dialog.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(HydraCN.class.getResource("view/FilterDetails.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            // Create the dialog Stage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Filter Viewer");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            // Set the person into the controller.
            FilterDetailsController controller = loader.getController();
            //controller.setDialogStage(dialogStage);

            // Show the dialog and wait until the user closes it
            dialogStage.showAndWait();

        } catch (IOException e) {
            e.printStackTrace();
        }
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
