package com.zhant.entregasGestor.dto;

public class AnalyticsDTO {

	private int count;
	private double percentage;
	
	
	public AnalyticsDTO(int count, double percentage) {
		this.count = count;
		this.percentage = percentage;
	}
	
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public double getPercentage() {
		return percentage;
	}
	public void setPercentage(double percentage) {
		this.percentage = percentage;
	}
	
	
}
