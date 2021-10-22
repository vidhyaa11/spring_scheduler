package com.example.scheduler.config;

import java.util.Arrays;
import java.util.TimeZone;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.support.CronTrigger;

import com.example.scheduler.utils.SchedulerZone;

@Configuration
@EnableAsync
public class SchedularConfig implements InitializingBean {

	@Autowired
	private TaskScheduler taskScheduler;

	@Bean
	public TaskScheduler taskScheduler() {
		return new ThreadPoolTaskScheduler();
	}
	
	@Autowired
	private Scheduler service;

	@Override
	public void afterPropertiesSet() throws Exception {
		Arrays.stream(new String[] { SchedulerZone.NZ.getCountryZone(), SchedulerZone.SG.getCountryZone() }).forEach(zone -> {
			taskScheduler.schedule(() -> {
				service.run(countryCode(zone));
			}, new CronTrigger("0 0/1 * ? * *", TimeZone.getTimeZone(zone)));
		});
	}
	
	private String countryCode(String zone) {
		for(SchedulerZone schedulerZone: SchedulerZone.values()) {
			if(schedulerZone.getCountryZone().equals(zone)) {
				return schedulerZone.name();
			}
		}
		return null;
	}
}
