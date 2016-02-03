package com.uom.cse.central_node;

import java.io.IOException;

import com.uom.cse.central_node.dataobjects.Filter;
import com.uom.cse.central_node.model.CEPRuleData;
import com.uom.cse.central_node.model.Device;
import com.uom.cse.central_node.model.FilterData;
import com.uom.cse.central_node.model.InteractionData;
import com.uom.cse.central_node.model.ProcessInfo;
import com.uom.cse.central_node.model.WindowsLogData;
import com.uom.cse.central_node.services.RegisterDeviceHandler;
import com.uom.cse.central_node.services.RegisterDeviceServer;
import com.uom.cse.central_node.view.AboutUsController;
import com.uom.cse.central_node.view.AlertMessageBoxController;
import com.uom.cse.central_node.view.AllDeviceFilterController;
import com.uom.cse.central_node.view.AppliedFilterViewController;
import com.uom.cse.central_node.view.AppliedLogRuleViewController;
import com.uom.cse.central_node.view.CEPRuleEditorController;
import com.uom.cse.central_node.view.DataViewerController;
import com.uom.cse.central_node.view.DeviceInteractionViewerController;
import com.uom.cse.central_node.view.DeviceOverviewController;
import com.uom.cse.central_node.view.FilterCreateFormController;
import com.uom.cse.central_node.view.FilterDetailsController;
import com.uom.cse.central_node.view.WindowsLogRuleCreateFormController;
import com.uom.cse.central_node.view.WindowsLogRulesViewerController;

import javafx.animation.FadeTransition;
import javafx.application.Application;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.concurrent.Worker;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ProgressBar;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

public class HydraCN extends Application {

	private Stage primaryStage;
	private Stage filterStage;
	private BorderPane rootLayout;

	private ObservableList<Device> deviceData = FXCollections.observableArrayList();
	private ObservableList<ProcessInfo> infoData = FXCollections.observableArrayList();
	private ObservableList<FilterData> filterData = FXCollections.observableArrayList();
	private ObservableList<WindowsLogData> windowsLogData = FXCollections.observableArrayList();
	private ObservableList<CEPRuleData> cepRuleData = FXCollections.observableArrayList();
	private ObservableList<InteractionData> interationData = FXCollections.observableArrayList();

	public static final String APPLICATION_ICON = "http://cdn1.iconfinder.com/data/icons/Copenhagen/PNG/32/people.png";
	public static final String SPLASH_IMAGE = "splash.png";

	private Pane splashLayout;
	private ProgressBar loadProgress;
	private Label progressText;
	private Stage mainStage;
	private static final int SPLASH_WIDTH = 676;
	private static final int SPLASH_HEIGHT = 227;

	DeviceOverviewController deviceOverviewController;

	private Stage windowsLogRuleViewer;

	@Override
	public void init() {
		ImageView splash = new ImageView(new Image(SPLASH_IMAGE));
		loadProgress = new ProgressBar();
		loadProgress.setPrefWidth(SPLASH_WIDTH - 20);
		progressText = new Label("Will find friends for peanuts . . .");
		splashLayout = new VBox();
		
		splashLayout.getChildren().addAll(splash, loadProgress, progressText);
		progressText.setAlignment(Pos.CENTER);
		splashLayout.setStyle(
				"-fx-padding: 5; " + "-fx-background-color: #FFF; " + "-fx-border-width:5; " + "-fx-border-color: "
						+ "linear-gradient(" + "to bottom, " + "#5a0241, " + "derive(#5a0241, 50%)" + ");");
		splashLayout.setEffect(new DropShadow());
	}

	@Override
	public void start(final Stage initStage) throws Exception {
		final Task<ObservableList<String>> initTask = new Task<ObservableList<String>>() {
			@Override
			protected ObservableList<String> call() throws InterruptedException {

				updateMessage("Loading...");
				for (int i = 0; i < 4; i++) {
                    Thread.sleep(800);
                }
				updateMessage("Done.");
				Thread.sleep(350);
				return null;
			}
		};

		showSplash(initStage, initTask, () -> showMainStage(initTask.valueProperty()));
		new Thread(initTask).start();
	}

	private void showSplash(final Stage initStage, Task<?> task, InitCompletionHandler initCompletionHandler) {
		progressText.textProperty().bind(task.messageProperty());
		loadProgress.progressProperty().bind(task.progressProperty());
		task.stateProperty().addListener((observableValue, oldState, newState) -> {
			if (newState == Worker.State.SUCCEEDED) {
				loadProgress.progressProperty().unbind();
				loadProgress.setProgress(1);
				initStage.toFront();
				FadeTransition fadeSplash = new FadeTransition(Duration.seconds(1.2), splashLayout);
				fadeSplash.setFromValue(1.0);
				fadeSplash.setToValue(0.0);
				fadeSplash.setOnFinished(actionEvent -> initStage.hide());
				fadeSplash.play();

				initCompletionHandler.complete();
			} 
		});
		// todo add code to gracefully handle other task states.
		Scene splashScene = new Scene(splashLayout);
		initStage.initStyle(StageStyle.UNDECORATED);
		initStage.getIcons().add(new Image("icon.png"));
		final Rectangle2D bounds = Screen.getPrimary().getBounds();
		initStage.setScene(splashScene);
		initStage.setX(bounds.getMinX() + bounds.getWidth() / 2 - SPLASH_WIDTH / 2);
		initStage.setY(bounds.getMinY() + bounds.getHeight() / 2 - SPLASH_HEIGHT / 2);
		initStage.show();
	}

	public interface InitCompletionHandler {
		public void complete();
	}

	private void showMainStage(ReadOnlyObjectProperty<ObservableList<String>> friends) {
		// set primary stage
		this.primaryStage = new Stage(StageStyle.DECORATED);
		// set title
		this.primaryStage.setTitle("HydraCN");

		initRootLayout();

		showDeviceFeeds();

		RegisterDeviceHandler.hydraCN = this;
		RegisterDeviceServer registerDeviceServer = new RegisterDeviceServer();
		registerDeviceServer.start();
	}

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

	public ObservableList<InteractionData> getInterationData() {
		return interationData;
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

			// HydraCN.class.getResourceAsStream("icon.png");
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

			deviceOverviewController = loader.getController();
			deviceOverviewController.setMainApp(this);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void showInfoMessage(String ipAddress, String deviceName, String message, String shortInfo) {
		if (!message.contains("null") && ipAddress != null && deviceName != null){
			deviceOverviewController.showInfoMessage(ipAddress, deviceName, message, shortInfo);
		}
		
	}

	public void showDataViewer() {
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
			dialogStage.getIcons().add(new Image("icon.png"));
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
	
	public void showInterationDataViewer() {
		try {
			// Load the fxml file and create a new stage for the popup dialog.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(HydraCN.class.getResource("view/DeviceInteractionDataViewer.fxml"));
			AnchorPane page = (AnchorPane) loader.load();

			// Create the dialog Stage.
			Stage dialogStage = new Stage();
			dialogStage.setTitle("Device Interaction Data Viewer");
			dialogStage.initModality(Modality.WINDOW_MODAL);
			dialogStage.initOwner(primaryStage);
			dialogStage.getIcons().add(new Image("icon.png"));
			Scene scene = new Scene(page);
			dialogStage.setScene(scene);

			// Set the person into the controller.
			DeviceInteractionViewerController controller = loader.getController();
			controller.setDialogStage(dialogStage);

			// Show the dialog and wait until the user closes it
			dialogStage.showAndWait();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void showAllDeviceFilter(DeviceOverviewController deviceOverviewController) {
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
			dialogStage.getIcons().add(new Image("icon.png"));
			Scene scene = new Scene(page);
			dialogStage.setScene(scene);

			// Set the person into the controller.
			AllDeviceFilterController controller = loader.getController();
			controller.setDialogStage(dialogStage, deviceOverviewController);

			// Show the dialog and wait until the user closes it
			dialogStage.showAndWait();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void showFilterDetails() {
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
			filterStage.getIcons().add(new Image("icon.png"));
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

	public void showFilterCreateForm() {
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
			newFilterStage.getIcons().add(new Image("icon.png"));
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

	public void showWindowsLogRuleViewer() {
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
			windowsLogRuleViewer.getIcons().add(new Image("icon.png"));
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

	public void showCEPRuleEditor() {
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
			windowsLogRuleViewer.getIcons().add(new Image("icon.png"));
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

	public void showWindowsLogRuleCreateForm() {
		try {
			// Load the fxml file and create a new stage for the popup dialog.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(HydraCN.class.getResource("view/WindowsLogRuleCreateForm.fxml"));
			AnchorPane page = (AnchorPane) loader.load();

			// Create the filter Stage.
			Stage newFilterStage = new Stage();
			newFilterStage.setTitle("Windows Log Rules");
			newFilterStage.initModality(Modality.WINDOW_MODAL);
			newFilterStage.initOwner(windowsLogRuleViewer);
			newFilterStage.getIcons().add(new Image("icon.png"));
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

	public void showAlertMessageBox(String msg) {
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

	public void showAlertMessageBox(String title, String msg) {
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

	public void showAppliedRuleView(Filter filter) {
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
			msgStage.getIcons().add(new Image("icon.png"));
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

	public void showAppliedRuleView(WindowsLogData logData) {
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
			msgStage.getIcons().add(new Image("icon.png"));
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
	
	public void showAbout() {
		try {
			// Load the fxml file and create a new stage for the popup dialog.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(HydraCN.class.getResource("view/AboutUs.fxml"));
			AnchorPane page = (AnchorPane) loader.load();

			// Create the filter Stage.
			Stage newFilterStage = new Stage();
			newFilterStage.setTitle("About");
			newFilterStage.initModality(Modality.WINDOW_MODAL);
			newFilterStage.getIcons().add(new Image("icon.png"));
			newFilterStage.initOwner(primaryStage);
			Scene scene = new Scene(page);
			newFilterStage.setScene(scene);

			// Set the person into the controller.
			AboutUsController controller = loader.getController();
			controller.setDialogStage(newFilterStage);

			// Show the dialog and wait until the user closes it
			newFilterStage.showAndWait();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
