package com.example.scheduler.utils;

public enum SchedulerZone {
	
	SG("Asia/Singapore"), NZ("Antarctica/McMurdo");

	private String countryZone;

	private SchedulerZone(String countryZone) {
		this.countryZone = countryZone;
	}
	
	public String getCountryZone() {
		return countryZone;
	}
}
