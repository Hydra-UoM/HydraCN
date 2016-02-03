package com.uom.cse.central_node.subscriber;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.uom.cse.central_node.dataobjects.Filter;
import com.uom.cse.central_node.event.ApplicationEvent;

import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.stage.Stage;

@Component
public class CriticalEventSubscriber implements StatementSubscriber {

	public CriticalEventSubscriber(String alert) {
		this.alertMessage = alert;
	}

	public String alertMessage;

	private static Logger LOG = LoggerFactory.getLogger(CriticalEventSubscriber.class);

	public String getStatement() {
		return "";
	}

	public void update(Map<String, ApplicationEvent> eventMap) {
		
		StringBuilder sb = new StringBuilder();
		sb.append("************************************************");
		sb.append("\n* [ALERT] : CRITICAL EVENT DETECTED BY ESPER! ");
		sb.append("\n" + alertMessage);
		sb.append("\n" + "Detected in PC MAC - " + eventMap.get("mac"));
		sb.append("\n**********************************************");
		System.out.println(sb.toString());

		
		Set<String> mykeyset = eventMap.keySet();
		
		
		Platform.runLater(() -> {
			String descriptionMessage = "";
			for(String key : mykeyset){
				if (key.equals("Unintended_URLs_Accessed")){
					descriptionMessage += key + " - ";
					String urls = eventMap.get(key) + "";
					if(!urls.equals("[]")){
						String []urlArray = urls.split(",");
						for(String url : urlArray){
							if(!url.contains(ApplicationEvent.token)){
								descriptionMessage += url + ",";
							}
						}
					}
				}else{
					descriptionMessage += key + " - " + eventMap.get(key) + "\n";
				}
			}
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("HYDRA ALERT");
			alert.setHeaderText("CRITICAL EVENT DETECTED BY HYDRA" + "\n" + "at device with MAC - " + eventMap.get("Detected_Device_MAC_ID"));
			
			alert.setContentText(alertMessage + "\n" + descriptionMessage);

			// Add a custom icon.
			Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
			stage.getIcons().add(new Image("icon.png"));
			
			alert.show();
		});

		// LOG.debug(sb.toString());
	}

}
