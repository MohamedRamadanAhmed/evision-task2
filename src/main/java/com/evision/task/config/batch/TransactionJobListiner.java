/**
 * 
 */
package com.evision.task.config.batch;

import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;

/**
 * @author Mohamed Ramadan
 *
 */
public class TransactionJobListiner implements JobExecutionListener {

	public void beforeJob(JobExecution jobExecution) {
		System.out.println("Called beforeJob().");
	}

	public void afterJob(JobExecution jobExecution) {
//      you can make any action here. after job finished
//		like call callback confirmation of successful service
		System.out.println("Called afterJob().");
	}
}