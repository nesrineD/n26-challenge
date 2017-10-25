package de.number26.challenge.service;




	import static org.junit.Assert.assertEquals;

	import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
	import org.junit.After;
	import org.junit.Test;
	import org.junit.runner.RunWith;
	import org.springframework.beans.factory.annotation.Autowired;
	import org.springframework.boot.test.context.SpringBootTest;
	import org.springframework.test.context.junit4.SpringRunner;
	

	import de.number26.challenge.model.Transaction;
	import de.number26.challenge.service.TransactionService;

	@RunWith(SpringRunner.class)
	@SpringBootTest
	public class TransactionServiceTest {

		@Autowired
		private TransactionService trService;

		@Test
		public void addNewTransactions() throws Exception {
 
			ArrayList<Transaction> trlist = new ArrayList<Transaction>(
					Arrays.asList(
							new Transaction(10D, currentTimestampMinus(20L)),
							new Transaction(20D, currentTimestampMinus(40L))));
			trlist.stream().forEach(t -> trService.addTransaction(t));
			assertEquals(trService.getTransactions(), trlist);

		}
		
		@Test
		public void addOldTransactions() throws Exception {

			ArrayList<Transaction> trlist = new ArrayList<Transaction>(
					Arrays.asList(
							new Transaction(10D, currentTimestampMinus(90L)),
							new Transaction(20D, currentTimestampMinus(100L))));
			trlist.stream().forEach(t -> trService.addTransaction(t));
			assertEquals(trService.getTransactions().isEmpty(), true);

		}

		@After
		public void clearTransactions() {
			trService.clearTransactions();
		}

		private long currentTimestampMinus(long seconds) {
				return Instant.now().minusSeconds(seconds).toEpochMilli();
		}
	}
	



