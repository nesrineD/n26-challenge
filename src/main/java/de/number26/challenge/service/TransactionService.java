package de.number26.challenge.service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import de.number26.challenge.model.Transaction;

@Component
public class TransactionService {

	

	private List<Transaction> transactions = new ArrayList<Transaction>();
	
	
	public synchronized boolean addTransaction(Transaction tr) {
		long threshold = Instant.now().minusSeconds(60L).toEpochMilli();
		if (tr.getTimestamp() > threshold) {
			transactions.add(tr);
			return true;
		} else {
			return false;
		}

	}

	public synchronized List<Transaction> getTransactions() {
		return transactions;
	}

	public synchronized void setTransactions(List<Transaction> transactions) {
		this.transactions = transactions;
	}

	public synchronized void clearTransactions() {
		transactions.clear();
		
	}

}
