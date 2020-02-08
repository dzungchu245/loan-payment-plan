package com.payment.plan.dzungchu.dto;

import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlanResponse {
	
	@JsonProperty("borrowerPaymentAmount")
	private double payment;

	@JsonProperty("date")
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
	@JsonSerialize(using = LocalDateTimeSerializer.class)
	@JsonDeserialize(using = LocalDateTimeDeserializer.class)
	private LocalDateTime date;

	@JsonProperty("initialOutstandingPrincipal")
	private double initialOP;

	@JsonProperty("interest")
	private double interest;
	
	@JsonProperty("principal")
	private double principal;
	
	@JsonProperty("remainingOutstandingPrincipal")
	private double remainingOP;
}
