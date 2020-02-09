package com.payment.plan.dzungchu.util;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.payment.plan.dzungchu.dto.PlanRequest;
import com.payment.plan.dzungchu.dto.PlanResponse;

/**
 * @author dzungchu
 *
 */
public class PaymentPlanHelper {

	private PaymentPlanHelper() {
	}

	/**
	 * @param duration    number of months
	 * @param nominalRate
	 * @param loanAmount
	 * @return
	 */
	private static double calculateAnnuity(int duration, double nominalRate, double loanAmount) {
		double monthlyRate = nominalRate / (100 * 12);
		double annuity = (loanAmount * monthlyRate) / (1 - Math.pow(1 + monthlyRate, (-1.00 * duration)));
		return roundNumber(annuity);
	}

	/**
	 * @param annuity
	 * @return
	 */
	private static double roundNumber(double annuity) {
		return Math.round(annuity * 100) / 100.00;
	}

	/**
	 * @param nominalRate
	 * @param initOP
	 * @return
	 */
	private static double calculateInterest(double nominalRate, double initOP) {
		double interest = (nominalRate * 30 * initOP) / (100.00 * 360.00);
		return roundNumber(interest);
	}

	/**
	 * @param request
	 * @return
	 */
	public static List<PlanResponse> generateLoan(PlanRequest request) {
		List<PlanResponse> result = new ArrayList<PlanResponse>();
		double nominalRate = request.getNominalRate();
		if ((request.getDuration() <= 0) || (nominalRate <= 0.0) || (request.getLoanAmount() <= 0.0)) {
			return result;
		}
		double annuity = calculateAnnuity(request.getDuration(), nominalRate, request.getLoanAmount());
		double remainingOP = request.getLoanAmount();
		double initOP;
		double payment;
		double principal;
		double interest;
		LocalDateTime date = request.getStartDate();
		while (remainingOP > 0) {
			initOP = remainingOP;
			interest = calculateInterest(nominalRate, initOP);
			if ((annuity - interest) <= initOP) {
				principal = roundNumber(annuity - interest);
				payment = annuity;
			} else {
				principal = initOP;
				payment = principal + interest;
			}
			remainingOP = roundNumber(remainingOP - principal);
			PlanResponse response = new PlanResponse(payment, date, initOP, interest, principal, remainingOP);

			result.add(response);
			date.plusMonths(1);
		}
		return result;
	}
}
