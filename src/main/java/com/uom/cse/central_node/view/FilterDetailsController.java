package com.uom.cse.central_node.view;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import com.uom.cse.central_node.data_objects.Filter;
import com.uom.cse.central_node.data_objects.FilterTable;
import com.uom.cse.central_node.model.FilterData;
import com.uom.cse.central_node.services.ThriftAgentProcessInfo;
import com.uom.cse.central_node.util.LogFileWritter;

import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
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
	private TableColumn<FilterData, String> sentData;
	@FXML
	private TableColumn<FilterData, String> receivedData;
	@FXML
	private TableColumn<FilterData, String> timeBound;
	@FXML
	private TableColumn<FilterData, String> process;
	@FXML
	private TableColumn<FilterData, String> message;
	@FXML
	private Button btnApply;
	@FXML
	private Button btnShowAppliedFilter;
	@FXML
	private Button btnDisableAppliedFilter;
	
	private Stage dialogStage;
	
	private FilterData selectedFilter;
	
	//executer for creating a thread pool
	private Executor exec;
	
	@FXML
	private void initialize() {
		
		// create executor that uses daemon threads:
		exec = Executors.newCachedThreadPool(runnable -> {
            Thread t = new Thread(runnable);
            t.setDaemon(true);
            return t;
        });
		
		// Initialize the person table with the two columns.
		filterName.setCellValueFactory(cellData -> cellData.getValue().filterNameProperty());
		cpu.setCellValueFactory(cellData -> cellData.getValue().cpuProperty());
		ram.setCellValueFactory(cellData -> cellData.getValue().ramProperty());
		sentData.setCellValueFactory(cellData -> cellData.getValue().sentDataProperty());
		receivedData.setCellValueFactory(cellData -> cellData.getValue().receivedProperty());
		timeBound.setCellValueFactory(cellData -> cellData.getValue().timeBoundProperty());
		process.setCellValueFactory(cellData -> cellData.getValue().processesProperty());
		message.setCellValueFactory(cellData -> cellData.getValue().messageProperty());
		
		filterTable.getSelectionModel().selectedItemProperty()
		.addListener((observable, oldValue, newValue) -> filterChanged(newValue));
		
		// Add observable list data to the table
		if(DeviceOverviewController.hydraCN.getFilterData().isEmpty()){
			populateFilterDataObservableArrayList();	
		}
		
		filterTable.setItems(DeviceOverviewController.hydraCN.getFilterData());
		
		Task<Filter> filterTask = new Task<Filter>() {
            @Override
            public Filter call() throws Exception {
                return FilterTable.getAppliedFilter();
            }
        };
        
        filterTask.setOnSucceeded((e) ->{
        	Filter filter = filterTask.getValue();
        	if(filter != null){
    			btnShowAppliedFilter.setDisable(false);
    		}
        });
        
        // run the task using a thread from the thread pool:
        exec.execute(filterTask);
	}
	
	private void filterChanged(FilterData filterData) {
		selectedFilter = filterData;
		btnApply.setDisable(false);
	}

	private void populateFilterDataObservableArrayList(){
		
		Task<List<Filter>> filterTask = new Task<List<Filter>>() {
            @Override
            public List<Filter> call() throws Exception {
                return FilterTable.getAllFilters();
            }
        };

        filterTask.setOnSucceeded((e) ->{
        	List<Filter> filterList = filterTask.getValue();
    		filterList.forEach((filter)->DeviceOverviewController.hydraCN.getFilterData().add(new FilterData(filter)));
        });
        
        // run the task using a thread from the thread pool:
        exec.execute(filterTask);
	}
	
	@FXML
	private void showFilterCreateForm() {
		DeviceOverviewController.hydraCN.showFilterCreateForm();
	}
	
	public void setDialogStage(Stage dialogStage) {
		this.dialogStage = dialogStage;
	}
	
	@FXML
	private void actionClose(){
		dialogStage.close();
	}
	
	@FXML
	private void actionApplyFilter(){
		//EventFeeder.applyFilter(new Filter(selectedFilter));
		//DeviceOverviewController.hydraCN.showAlertMessageBox("This is a warm alert!!");
//		DeviceOverviewController.hydraCN.getDeviceData().forEach(action -> {
//			ProcessStatsClient.getAllAvgProcessInfo(action.getIPAddress(), 2, 3, 0, 0, 5);
//		});
		
		//change database entry
		FilterTable.applyFilter(selectedFilter.getId());
		
		//enable apply button
		btnShowAppliedFilter.setDisable(false);
		
		ThriftAgentProcessInfo info = new ThriftAgentProcessInfo();
		info.name = "ddddssssrrrrrrrrrrrrrrr";
		info.cpuUsage = 4.0d;
		info.ramUsage = 3.0d;
		info.packageName = "sssdasd";
		info.name = "asdasd";
		info.sentData = 3.0d;
		info.receiveData = 3.0d;
		info.mac = "dsdasda";
		
		List<ThriftAgentProcessInfo> infolist = new ArrayList<>();
		infolist.add(info);
		infolist.add(info);
		
		LogFileWritter.writeFile(infolist);
		
	}
	
	@FXML
	private void actionShowAppliedFilter(){
		Filter filter = FilterTable.getAppliedFilter();
		DeviceOverviewController.hydraCN.showAppliedRuleView(filter);
	}
	
	@FXML
	private void actionDisableAppliedFilter(){
		FilterTable.disableAllFilter();
		btnShowAppliedFilter.setDisable(true);
	}
}
