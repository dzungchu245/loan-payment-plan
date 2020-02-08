package com.payment.plan.dzungchu.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.payment.plan.dzungchu.dto.PlanRequest;
import com.payment.plan.dzungchu.dto.PlanResponse;
import com.payment.plan.dzungchu.util.PaymentPlanHelper;

@RestController
public class PaymentPlanController {
    
    @PostMapping("generate-plan")
    public ResponseEntity<List<PlanResponse>> generatePlan(@RequestBody PlanRequest request) {
        List<PlanResponse> list = PaymentPlanHelper.generateLoan(request);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }
}
