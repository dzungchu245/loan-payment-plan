package com.payment.plan.dzungchu.controller;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
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
import com.payment.plan.dzungchu.exception.BadRequestException;
import com.payment.plan.dzungchu.exception.ResponseWrapper;
import com.payment.plan.dzungchu.exception.RestExceptionHandler;
import com.payment.plan.dzungchu.service.PaymentPlanService;

import lombok.extern.slf4j.Slf4j;

@RunWith(MockitoJUnitRunner.class)
@Slf4j
public class PaymentPlanControllerTest {

	@InjectMocks
	private PaymentPlanController controller;
	
	@Mock
	private PaymentPlanService planService;

	private MockMvc mockMvc;

	private PlanRequest planRequest;
	private List<PlanResponse> planResponse;
	private ObjectMapper mapper = new ObjectMapper();

	@Before
	public void init() {
		planRequest = new PlanRequest(5000.00, 5.00, 24, LocalDateTime.now());
		mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
		mockMvc = MockMvcBuilders.standaloneSetup(controller).setControllerAdvice(new RestExceptionHandler()).build();
	}

	@Test
	public void generatePlan() throws Exception {
		planResponse = planService.generateLoan(planRequest);
		String jsonReq = mapper.writeValueAsString(planRequest);
		log.info(jsonReq);
		MockHttpServletResponse mvcRes = mockMvc.perform(MockMvcRequestBuilders.post("/generate-plan")
				.contentType(MediaType.APPLICATION_JSON).content(jsonReq).accept(MediaType.APPLICATION_JSON_VALUE))
				.andReturn().getResponse();
		int status = mvcRes.getStatus();
		assertEquals(HttpStatus.OK.value(), status);
		String content = mvcRes.getContentAsString();
		log.info(content);
		List<PlanResponse> mapFromJson = mapper.readValue(content, new TypeReference<List<PlanResponse>>() {
		});
		assertEquals(planResponse, mapFromJson);
	}
	
	@Test
	public void generatePlan_BadRequest() throws Exception {
		when(planService.generateLoan(planRequest)).thenThrow(new BadRequestException());
		String jsonReq = mapper.writeValueAsString(planRequest);
		MockHttpServletResponse mvcRes = mockMvc.perform(MockMvcRequestBuilders.post("/generate-plan")
				.contentType(MediaType.APPLICATION_JSON).content(jsonReq).accept(MediaType.APPLICATION_JSON_VALUE))
				.andReturn().getResponse();
		
		int status = mvcRes.getStatus();
        assertEquals(HttpStatus.BAD_REQUEST.value(), status);

        String content = mvcRes.getContentAsString();
        ResponseWrapper responseWrapper = mapper.readValue(content, ResponseWrapper.class);

        assertEquals(1, responseWrapper.getErrors().size());
        assertEquals(Boolean.TRUE, responseWrapper.getErrors().contains(BadRequestException.BAD_REQUEST));
	}

}
