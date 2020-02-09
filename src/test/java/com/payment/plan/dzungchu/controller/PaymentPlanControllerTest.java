package com.payment.plan.dzungchu.controller;

import static org.junit.Assert.assertEquals;

import java.time.LocalDateTime;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.payment.plan.dzungchu.dto.PlanRequest;
import com.payment.plan.dzungchu.dto.PlanResponse;
import com.payment.plan.dzungchu.util.PaymentPlanHelper;

import lombok.extern.slf4j.Slf4j;

/**
 * TEST PaymentPlanControllerTest.
 */

@RunWith(MockitoJUnitRunner.class)
@Slf4j
public class PaymentPlanControllerTest {

	@InjectMocks
	private PaymentPlanController controller;

	private MockMvc mockMvc;

	private PlanRequest planRequest;
	private List<PlanResponse> planResponse;
	private ObjectMapper mapper = new ObjectMapper();

	@Before
	public void init() {
		mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
		planRequest = new PlanRequest(5000.00, 5.00, 24, LocalDateTime.now());
		mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
	}

	@Test
	public void generatePlan() throws Exception {
		String jsonReq = mapper.writeValueAsString(planRequest);
		log.info(jsonReq);
		planResponse = PaymentPlanHelper.generateLoan(planRequest);
		MockHttpServletResponse mvcRes = mockMvc.perform(MockMvcRequestBuilders.post("/generate-plan")
				.contentType(MediaType.APPLICATION_JSON).content(jsonReq).accept(MediaType.APPLICATION_JSON_VALUE))
				.andReturn().getResponse();
		int status = mvcRes.getStatus();
		assertEquals(HttpStatus.OK.value(), status);
		String content = mvcRes.getContentAsString();
		log.info(content);
		List<PlanResponse> mapFromJson = mapper.readValue(content, new TypeReference<List<PlanResponse>>() {});
		assertEquals(planResponse, mapFromJson);
	}
}
