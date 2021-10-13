/**
 * 
 */
package com.evision.task.controller;

import java.util.List;

import javax.validation.Valid;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.evision.task.data.model.BankTransactionDto;
import com.evision.task.data.response.Response;
import com.evision.task.service.BankTransactionService;

/**
 * @author Mohamed Ramadan
 *
 */
@RestController

public class TransactionController {

	@Autowired
	private BankTransactionService bankTransactionService;

	@PostMapping(path = "/transactions/method1")
	public Response<Object> saveTransactionsMethod1(@RequestBody @Valid List<BankTransactionDto> transactions)
			throws JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException,
			JobParametersInvalidException {
		bankTransactionService.insertTransactionsMethod1(transactions);
		return Response.ok().setPayload(bankTransactionService.getAllTransactions());
	}
	
	@PostMapping(path = "/transactions/method2")
	public Response<Object> saveTransactionsMethod2(@RequestBody @Valid List<BankTransactionDto> transactions)
			throws JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException,
			JobParametersInvalidException {
		bankTransactionService.insertTransactionsMethod2(transactions);
		return Response.ok().setPayload(bankTransactionService.getAllTransactions());
	}

}
