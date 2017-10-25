package de.number26.challenge.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import de.number26.challenge.model.Statistics;
import de.number26.challenge.service.StatisticsService;

@RestController
public class StatisticsController {
	@Autowired
	StatisticsService  statCal;
	
	@GetMapping("/statistics")
	public Statistics getAllStatistics() {
		return statCal.calculateMetrics();
	}
}
