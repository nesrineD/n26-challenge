package de.number26.challenge.service;

import static org.junit.Assert.assertEquals;


import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import de.number26.challenge.model.Statistics;
import de.number26.challenge.model.Transaction;


@RunWith(SpringRunner.class)
@SpringBootTest

public class StatisticsServiceTest {
	@Autowired
	private TransactionService trService;
	@Autowired
	private StatisticsService statService;

	@Before
	public void initializeTransactionStore() {
		ArrayList<Transaction> trlist = new ArrayList<Transaction>(
				Arrays.asList(
						new Transaction(10D, currentTimestampMinus(20L)),
						new Transaction(20D, currentTimestampMinus(40L)),
						new Transaction(30D, currentTimestampMinus(50L)),
						new Transaction(40D, currentTimestampMinus(70L)),
						new Transaction(50D, currentTimestampMinus(80L))));
		trlist.stream().forEach(t -> trService.addTransaction(t));
	}

	@Test
	public void checkStatistics() {

		Statistics stats = statService.calculateMetrics();
		assertEquals(stats.getAvg(), 20D, 0);
		assertEquals(stats.getCount(), 3, 0);
		assertEquals(stats.getMax(), 30D, 0);
		assertEquals(stats.getMin(), 10D, 0);
		assertEquals(stats.getSum(), 60D, 0);

	}

	@After
	public void clearTransactions() {
		trService.clearTransactions();
	}

	private long currentTimestampMinus(long seconds) {
		return Instant.now().minusSeconds(seconds).toEpochMilli();
	}
}
