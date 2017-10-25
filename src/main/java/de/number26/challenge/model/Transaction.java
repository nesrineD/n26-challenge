package de.number26.challenge.model;

import java.io.Serializable;

public class Transaction  implements Serializable{
	public Transaction() {
		
	}

	public Transaction(Double amount, Long timestamp) {
		super();
		this.amount = amount;
		this.timestamp = timestamp;
	}
	
	private static final long serialVersionUID = 1L;
	private Double amount;
	private Long timestamp;
	
	public Double getAmount() {
		return amount;
	}
	public void setAmount(Double amount) {
		this.amount = amount;
	}
	public Long getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(Long timestamp) {
		this.timestamp = timestamp;
	}
	
	@Override
	public String toString() {
		return "Transaction [amount=" + amount + ", timestamp=" + timestamp + "]";
	}

}
