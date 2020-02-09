package com.payment.plan.dzungchu.service;

import static org.junit.Assert.assertTrue;

import java.time.LocalDateTime;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import com.payment.plan.dzungchu.dto.PlanRequest;
import com.payment.plan.dzungchu.dto.PlanResponse;
import com.payment.plan.dzungchu.exception.BadRequestException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RunWith(MockitoJUnitRunner.class)
public class PaymentPlanServiceTest {

	@InjectMocks
	private PaymentPlanService planService;

	private PlanRequest planRequest;
	private List<PlanResponse> planResponse;

	@Test
	public void generatePlan() throws BadRequestException {
		planRequest = new PlanRequest(5000.00, 5.00, 24, LocalDateTime.now());
		planResponse = planService.generateLoan(planRequest);
		log.info(planResponse.toString());
		assertTrue(planResponse.size() > 0);
	}

	@Test(expected = BadRequestException.class)
	public void generatePlan_BadRequest() throws BadRequestException {
		planRequest = new PlanRequest(0.00, 5.00, 24, LocalDateTime.now());
		planService.generateLoan(planRequest);
	}

	@Test(expected = BadRequestException.class)
	public void generatePlan_BadRequest_2() throws BadRequestException {
		planRequest = new PlanRequest(500.00, 0.00, 24, LocalDateTime.now());
		planService.generateLoan(planRequest);
	}

	@Test(expected = BadRequestException.class)
	public void generatePlan_BadRequest_3() throws BadRequestException {
		planRequest = new PlanRequest(500.00, 5.00, 0, LocalDateTime.now());
		planService.generateLoan(planRequest);
	}

	@Test(expected = BadRequestException.class)
	public void generatePlan_BadRequest_4() throws BadRequestException {
		planRequest = new PlanRequest(0.00, 5.00, 24, null);
		planService.generateLoan(planRequest);
	}
}
