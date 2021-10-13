/**
 * 
 */
package com.evision.task.service;

import java.util.List;

import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;

import com.evision.task.data.model.BankTransactionDto;

import net.bytebuddy.asm.Advice.Return;

/**
 * @author Mohamed Ramadan
 *
 */
public interface BankTransactionService {
	List<BankTransactionDto> getTransactionsByUser(String userName);
	List<BankTransactionDto> getAllTransactions();
	public String insertTransactionsMethod1(List<BankTransactionDto> transactions) throws JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException, JobParametersInvalidException ;
	public String insertTransactionsMethod2(List<BankTransactionDto> transactions);


}
