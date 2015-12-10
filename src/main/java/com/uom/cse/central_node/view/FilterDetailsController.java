package com.uom.cse.central_node.view;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import com.uom.cse.central_node.data_objects.Filter;
import com.uom.cse.central_node.data_objects.FilterTable;
import com.uom.cse.central_node.model.FilterData;

import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;


public class FilterDetailsController {
	@FXML
	public TableView<FilterData> filterTable;
	@FXML
	private TableColumn<FilterData, String> filterName;
	@FXML
	private TableColumn<FilterData, String> cpu;
	@FXML
	private TableColumn<FilterData, String> ram;
	@FXML
	private TableColumn<FilterData, String> network;
	@FXML
	private TableColumn<FilterData, String> timeBound;
	@FXML
	private TableColumn<FilterData, String> process;
	@FXML
	private TableColumn<FilterData, String> eventId;
	@FXML
	private TableColumn<FilterData, String> message;
	
	//executer for creating a thread pool
	private Executor exec;
	
	@FXML
	private void initialize() {
		
		// create executor that uses daemon threads:
		exec = Executors.newCachedThreadPool(runnable -> {
            Thread t = new Thread(runnable);
            t.setDaemon(true);
            return t ;
        });
		
		// Initialize the person table with the two columns.
		filterName.setCellValueFactory(cellData -> cellData.getValue().filterNameProperty());
		cpu.setCellValueFactory(cellData -> cellData.getValue().cpuProperty());
		ram.setCellValueFactory(cellData -> cellData.getValue().timeBoundProperty());
		network.setCellValueFactory(cellData -> cellData.getValue().ramProperty());
		timeBound.setCellValueFactory(cellData -> cellData.getValue().timeBoundProperty());
		process.setCellValueFactory(cellData -> cellData.getValue().processesProperty());
		eventId.setCellValueFactory(cellData -> cellData.getValue().eventIdProperty());
		message.setCellValueFactory(cellData -> cellData.getValue().messageProperty());
		
		// Add observable list data to the table
		populateFilterDataObservableArrayList();
		
		filterTable.setItems(DeviceOverviewController.hydraCN.getFilterData());
	}
	
	private void populateFilterDataObservableArrayList(){
		
		Task<List<Filter>> filterTask = new Task<List<Filter>>() {
            @Override
            public List<Filter> call() throws Exception {
                return FilterTable.getAllFilters();
            }
        };
//        Filter fi = new Filter();
//    	fi.setCpuUsage(30);
//    	fi.setRamUsage(150);
//    	fi.setEventId(2000);
//    	fi.setProcesses("dasdasd, dadasdsa, sdadasd");
//    	fi.setMessage("sadsdsadsad");
//    	fi.setSentData(20);
//    	fi.setReceivedData(30);
//    	fi.setFilterName("Filter 1");
//    	
//    	FilterTable.insertFilter(fi);

        filterTask.setOnSucceeded((e) ->{
        	List<Filter> filterList = filterTask.getValue();
    		filterList.forEach((filter)->DeviceOverviewController.hydraCN.getFilterData().add(new FilterData(filter)));
        });
        
        // run the task using a thread from the thread pool:
        exec.execute(filterTask);
		
	}
}
