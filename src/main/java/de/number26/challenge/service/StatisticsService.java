package de.number26.challenge.service;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.DoubleSummaryStatistics;

import de.number26.challenge.model.Statistics;
import de.number26.challenge.model.Transaction;

@Component
public class StatisticsService {
	@Autowired
	TransactionService trService;
	@Autowired
	Statistics stats;
	
	public synchronized List<Transaction> lastMinuteTransactions(){ 
		long threshold = Instant.now().minusSeconds(60L).toEpochMilli();
		List<Transaction>  allTransactions = trService.getTransactions();
		List<Transaction>  subTr= allTransactions.stream().filter(tr ->  tr.getTimestamp()> threshold).collect(Collectors.toList());
		System.out.println("transactions of the last minute ");
		subTr.forEach(t-> System.out.println(t));
		return subTr;
	}
	
	public synchronized Statistics calculateMetrics(){
		List<Transaction> tr = lastMinuteTransactions();
		DoubleSummaryStatistics  statistics =
				 tr.stream().collect(Collectors.summarizingDouble(Transaction::getAmount));
		stats.setSum(statistics.getSum());
		stats.setAvg(statistics.getAverage());
		stats.setMax(statistics.getMax());
	    stats.setMin(statistics.getMin());
	    stats.setCount(statistics.getCount());
	    System.out.println("Statistics results "+ stats.toString());
		return stats;
	}
	

}
