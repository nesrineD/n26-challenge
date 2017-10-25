package de.number26.challenge.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import de.number26.challenge.model.Transaction;
import de.number26.challenge.service.TransactionService;

@RestController
public class TransactionController {

	@Autowired
	TransactionService transactionsRepository;

	/*// Get All Transactions used for testing 
	@GetMapping("/transactions")
	public List<Transaction> getAllTransactions() {
		return transactionsRepository.getTransactions();
	}*/

	// Create a new Transaction
	@PostMapping(path = "/transactions", consumes = "application/json")
	public ResponseEntity<Void> createTransaction(@Valid @RequestBody Transaction tr) {

		if (transactionsRepository.addTransaction(tr)) {
			return ResponseEntity.status(HttpStatus.CREATED).build();
		} else {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		}
	}

}
