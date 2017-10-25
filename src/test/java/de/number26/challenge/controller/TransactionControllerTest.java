package de.number26.challenge.controller;

import static org.junit.Assert.assertEquals;

import java.time.Instant;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import de.number26.challenge.model.Transaction;
import de.number26.challenge.service.TransactionService;
import net.minidev.json.JSONValue;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class TransactionControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private TransactionService trService;

	@Test
	public void addNewTransaction() throws Exception {

		Transaction mockTransaction = new Transaction(20.0, currentTimestampMinus(2L));
		// Send transaction as body to /transactions
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/transactions")
				.contentType(MediaType.APPLICATION_JSON)
				.content(JSONValue.toJSONString(mockTransaction));
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		MockHttpServletResponse response = result.getResponse();
		assertEquals(HttpStatus.CREATED.value(), response.getStatus());

	}
	
	@Test
	public void addOldTransaction() throws Exception {

		Transaction mockTransaction = new Transaction(20D, currentTimestampMinus(600L));
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/transactions")
				.contentType(MediaType.APPLICATION_JSON).content(JSONValue.toJSONString(mockTransaction));
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		MockHttpServletResponse response = result.getResponse();
		assertEquals(HttpStatus.NO_CONTENT.value(), response.getStatus());

	}
	
	@After
	public void clearTransactions() {
		trService.clearTransactions();
	}

	private long currentTimestampMinus(long seconds) {
		return Instant.now().minusSeconds(seconds).toEpochMilli();
	}
}
