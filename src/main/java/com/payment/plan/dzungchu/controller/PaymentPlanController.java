package com.payment.plan.dzungchu.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.payment.plan.dzungchu.dto.PlanRequest;
import com.payment.plan.dzungchu.dto.PlanResponse;
import com.payment.plan.dzungchu.exception.BadRequestException;
import com.payment.plan.dzungchu.service.PaymentPlanService;

@RestController
public class PaymentPlanController {
	
	@Autowired
	private PaymentPlanService planService;
    
    @PostMapping("generate-plan")
    public ResponseEntity<List<PlanResponse>> generatePlan(@RequestBody PlanRequest request) throws BadRequestException {
        List<PlanResponse> list = planService.generateLoan(request);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }
}
