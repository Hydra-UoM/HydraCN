package com.uom.central_node.windows_agent_services;

public class WindowsDeviceOverallInfo {
	
	private double cpuUsage;
	private double ramUsedUsage;
	private double networkUpload;
	private double networkDownload;
	
	public String getCpuUsage() {
		return cpuUsage + "";
	}
	public void setCpuUsage(double cpuUsage) {
		this.cpuUsage = cpuUsage;
	}
	public String getRamUsedUsage() {
		return ramUsedUsage + "";
	}
	public void setRamUsedUsage(double ramUsedUsage) {
		this.ramUsedUsage = ramUsedUsage;
	}
	public String getNetworkUpload() {
		return networkUpload + "";
	}
	public void setNetworkUpload(double networkUpload) {
		this.networkUpload = networkUpload;
	}
	public String getNetworkDownload() {
		return networkDownload + "";
	}
	public void setNetworkDownload(double networkDownload) {
		this.networkDownload = networkDownload;
	}
}
