package com.uom.cse.central_node;

import java.io.IOException;

import com.uom.cse.central_node.data_objects.Filter;
import com.uom.cse.central_node.model.CEPRuleData;
import com.uom.cse.central_node.model.Device;
import com.uom.cse.central_node.model.FilterData;
import com.uom.cse.central_node.model.ProcessInfo;
import com.uom.cse.central_node.model.WindowsLogData;
import com.uom.cse.central_node.services.RegisterDeviceHandler;
import com.uom.cse.central_node.services.RegisterDeviceServer;
import com.uom.cse.central_node.view.AlertMessageBoxController;
import com.uom.cse.central_node.view.AllDeviceFilterController;
import com.uom.cse.central_node.view.AppliedFilterViewController;
import com.uom.cse.central_node.view.AppliedLogRuleViewController;
import com.uom.cse.central_node.view.CEPRuleEditorController;
import com.uom.cse.central_node.view.DataViewerController;
import com.uom.cse.central_node.view.DeviceOverviewController;
import com.uom.cse.central_node.view.FilterCreateFormController;
import com.uom.cse.central_node.view.FilterDetailsController;
import com.uom.cse.central_node.view.WindowsLogRuleCreateFormController;
import com.uom.cse.central_node.view.WindowsLogRulesViewerController;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class HydraCN extends Application {
	
	private Stage primaryStage;
	private Stage filterStage;
    private BorderPane rootLayout;
    
	private ObservableList<Device> deviceData = FXCollections.observableArrayList();
	private ObservableList<ProcessInfo> infoData = FXCollections.observableArrayList();
	private ObservableList<FilterData> filterData = FXCollections.observableArrayList();
	private ObservableList<WindowsLogData> windowsLogData = FXCollections.observableArrayList();
	private ObservableList<CEPRuleData> cepRuleData = FXCollections.observableArrayList();
	
	private Stage windowsLogRuleViewer;
	
	public ObservableList<Device> getDeviceData() {
        return deviceData;
    }
	
	public ObservableList<FilterData> getFilterData() {
        return filterData;
    }

	public ObservableList<WindowsLogData> getWindowsLogData() {
        return windowsLogData;
    }

	public ObservableList<CEPRuleData> getCEPRuleData() {
        return cepRuleData;
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

            //HydraCN.class.getResourceAsStream("icon.png");
            primaryStage.getIcons().add(new Image("icon.png"));
            
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

            // Create the filter Stage.
            filterStage = new Stage();
            filterStage.setTitle("Rule Viewer");
            filterStage.initModality(Modality.WINDOW_MODAL);
            filterStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            filterStage.setScene(scene);

            // Set the person into the controller.
            FilterDetailsController controller = loader.getController();
            controller.setDialogStage(filterStage);

            // Show the dialog and wait until the user closes it
            filterStage.showAndWait();

        } catch (IOException e) {
            e.printStackTrace();
        }
	}
	
	public void showFilterCreateForm(){
		try {
            // Load the fxml file and create a new stage for the popup dialog.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(HydraCN.class.getResource("view/FilterCreateForm.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            // Create the filter Stage.
            Stage newFilterStage = new Stage();
            newFilterStage.setTitle("Performance Data");
            newFilterStage.initModality(Modality.WINDOW_MODAL);
            newFilterStage.initOwner(filterStage);
            Scene scene = new Scene(page);
            newFilterStage.setScene(scene);

            // Set the person into the controller.
            FilterCreateFormController controller = loader.getController();
            controller.setDialogStage(newFilterStage);

            // Show the dialog and wait until the user closes it
            newFilterStage.showAndWait();

        } catch (IOException e) {
            e.printStackTrace();
        }
	}
	

	public void showWindowsLogRuleViewer(){
		try {
            // Load the fxml file and create a new stage for the popup dialog.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(HydraCN.class.getResource("view/WindowsLogRulesViewer.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            // Create the filter Stage.
            windowsLogRuleViewer = new Stage();
            windowsLogRuleViewer.setTitle("Windows Log Rules");
            windowsLogRuleViewer.initModality(Modality.WINDOW_MODAL);
            windowsLogRuleViewer.initOwner(primaryStage);
            Scene scene = new Scene(page);
            windowsLogRuleViewer.setScene(scene);

            // Set the person into the controller.
            WindowsLogRulesViewerController controller = loader.getController();
            controller.setDialogStage(windowsLogRuleViewer);

            // Show the dialog and wait until the user closes it
            windowsLogRuleViewer.showAndWait();

        } catch (IOException e) {
            e.printStackTrace();
        }
	}
	
	public void showCEPRuleEditor(){
		try {
            // Load the fxml file and create a new stage for the popup dialog.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(HydraCN.class.getResource("view/CEPRuleEditor.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            // Create the filter Stage.
            windowsLogRuleViewer = new Stage();
            windowsLogRuleViewer.setTitle("CEP Rule Editor");
            windowsLogRuleViewer.initModality(Modality.WINDOW_MODAL);
            windowsLogRuleViewer.initOwner(primaryStage);
            Scene scene = new Scene(page);
            windowsLogRuleViewer.setScene(scene);

            // Set the person into the controller.
            CEPRuleEditorController controller = loader.getController();
            controller.setDialogStage(windowsLogRuleViewer);

            // Show the dialog and wait until the user closes it
            windowsLogRuleViewer.showAndWait();

        } catch (IOException e) {
            e.printStackTrace();
        }
	}
	
	public void showWindowsLogRuleCreateForm(){
		try {
            // Load the fxml file and create a new stage for the popup dialog.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(HydraCN.class.getResource("view/WindowsLogRuleCreateForm.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            // Create the filter Stage.
            Stage newFilterStage = new Stage();
            newFilterStage.setTitle("Performance Data");
            newFilterStage.initModality(Modality.WINDOW_MODAL);
            newFilterStage.initOwner(windowsLogRuleViewer);
            Scene scene = new Scene(page);
            newFilterStage.setScene(scene);

            // Set the person into the controller.
            WindowsLogRuleCreateFormController controller = loader.getController();
            controller.setDialogStage(newFilterStage);

            // Show the dialog and wait until the user closes it
            newFilterStage.showAndWait();

        } catch (IOException e) {
            e.printStackTrace();
        }
	}
	
	public void showAlertMessageBox(String msg){
		try {
            // Load the fxml file and create a new stage for the popup dialog.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(HydraCN.class.getResource("view/AlertMessageBox.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            // Create the filter Stage.
            Stage msgStage = new Stage();
            msgStage.setTitle("HYDRA Alert");
            msgStage.initModality(Modality.WINDOW_MODAL);
            msgStage.initOwner(filterStage);
            Scene scene = new Scene(page);
            msgStage.setScene(scene);

            // Set the person into the controller.
            AlertMessageBoxController controller = loader.getController();
            controller.setDialogStage(msgStage);
            controller.setMessage(msg);

            // Show the dialog and wait until the user closes it
            msgStage.showAndWait();

        } catch (IOException e) {
            e.printStackTrace();
        }
	}
	
	public void showAlertMessageBox(String title, String msg){
		try {
            // Load the fxml file and create a new stage for the popup dialog.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(HydraCN.class.getResource("view/AlertMessageBox.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            // Create the filter Stage.
            Stage msgStage = new Stage();
            msgStage.setTitle("HYDRA Alert");
            msgStage.initModality(Modality.WINDOW_MODAL);
            msgStage.initOwner(filterStage);
            Scene scene = new Scene(page);
            msgStage.setScene(scene);

            // Set the person into the controller.
            AlertMessageBoxController controller = loader.getController();
            controller.setDialogStage(msgStage);
            controller.setTitle(title);
            controller.setMessage(msg);

            // Show the dialog and wait until the user closes it
            msgStage.showAndWait();

        } catch (IOException e) {
            e.printStackTrace();
        }
	}
	
	public void showAppliedRuleView(Filter filter){
		try {
            // Load the fxml file and create a new stage for the popup dialog.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(HydraCN.class.getResource("view/AppliedFilterView.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            // Create the filter Stage.
            Stage msgStage = new Stage();
            msgStage.setTitle("Selected Rule");
            msgStage.initModality(Modality.WINDOW_MODAL);
            msgStage.initOwner(filterStage);
            Scene scene = new Scene(page);
            msgStage.setScene(scene);

            // Set the person into the controller.
            AppliedFilterViewController controller = loader.getController();
            controller.setStage(msgStage);
            controller.setFilter(filter);

            // Show the dialog and wait until the user closes it
            msgStage.showAndWait();

        } catch (IOException e) {
            e.printStackTrace();
        }
	}
	
	public void showAppliedRuleView(WindowsLogData logData){
		try {
            // Load the fxml file and create a new stage for the popup dialog.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(HydraCN.class.getResource("view/AppliedLogRuleView.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            // Create the filter Stage.
            Stage msgStage = new Stage();
            msgStage.setTitle("Selected Rule");
            msgStage.initModality(Modality.WINDOW_MODAL);
            msgStage.initOwner(windowsLogRuleViewer);
            Scene scene = new Scene(page);
            msgStage.setScene(scene);

            // Set the person into the controller.
            AppliedLogRuleViewController controller = loader.getController();
            controller.setStage(msgStage);
            controller.setLogData(logData);

            // Show the dialog and wait until the user closes it
            msgStage.showAndWait();

        } catch (IOException e) {
            e.printStackTrace();
        }
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
