package com.example.scheduler.config;

import org.springframework.stereotype.Service;

@Service
public class Scheduler {
	
	public void run(String zone) {
		System.out.println("It works for the zone: "+zone);
	}

}
