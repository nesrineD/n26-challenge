package de.number26.challenge.controller;

import static org.junit.Assert.assertEquals;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.google.gson.Gson;

import de.number26.challenge.model.Statistics;
import de.number26.challenge.model.Transaction;
import de.number26.challenge.service.TransactionService;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class StatisticsControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private TransactionService trService;

	@Before
	public void initializeTransactionDataStore() {
		ArrayList<Transaction> trlist = new ArrayList<Transaction>(
				Arrays.asList(
						new Transaction(10D, currentTimestampMinus(20L)),
						new Transaction(20D, currentTimestampMinus(40L)),
						new Transaction(30D, currentTimestampMinus(50L)),
						new Transaction(40D, currentTimestampMinus(70L)),
						new Transaction(50D, currentTimestampMinus(80L))));
		trlist.stream().forEach(t -> trService.addTransaction(t));

	}

	/**
	 * only the statistics of the transactions with timestamp in the last minute will be calculated
	 * @throws Exception
	 */
	@Test
	public void testStatisticsResults() throws Exception {

		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/statistics");
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		MockHttpServletResponse response = result.getResponse();
		assertEquals(response.getStatus(), HttpStatus.OK.value());
		Statistics stats = new Gson().fromJson(response.getContentAsString(), Statistics.class);
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
