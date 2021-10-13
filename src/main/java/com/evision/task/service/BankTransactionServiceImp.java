/**
 * 
 */
package com.evision.task.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameter;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import com.evision.task.config.batch.SpringBatchConfig;
import com.evision.task.data.entity.BankTransaction;
import com.evision.task.data.model.BankTransactionDto;
import com.evision.task.repositry.BankTransactionRepositry;
import com.evision.task.repositry.UserRepositry;

/**
 * @author Mohamed Ramadan
 *
 */
@Service
public class BankTransactionServiceImp implements BankTransactionService {

	@Autowired
	BankTransactionRepositry bankTransactionRepositry;
	@Autowired
	private JobLauncher jobLauncher;
	@Autowired
	private UserRepositry userRepositry;
	@Autowired
	@Lazy
	private SpringBatchConfig springBatchConfig;

	@Override
	public List<BankTransactionDto> getTransactionsByUser(String userName) {

		return null;
	}

	@Override
	public List<BankTransactionDto> getAllTransactions() {
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

		List<BankTransaction> x = bankTransactionRepositry.findAll();
		return x.stream().map(t -> {
			return modelMapper.map(t, BankTransactionDto.class);
		}).collect(Collectors.toList());
	}

	@Override
	public String insertTransactionsMethod1(List<BankTransactionDto> transactions)
			throws JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException,
			JobParametersInvalidException {
		new Thread(() -> {
			long date1 = new Date().getTime();
			Map<String, JobParameter> maps = new HashMap<>();
			maps.put("time", new JobParameter(System.currentTimeMillis()));
			JobParameters parameters = new JobParameters(maps);
			JobExecution jobExecution = null;
			try {
				jobExecution = jobLauncher.run(springBatchConfig.createJob(transactions), parameters);
				System.out.println("method 1: "+getDiffBetwnTwoDates(date1,new Date().getTime()));
			} catch (JobExecutionAlreadyRunningException | JobRestartException | JobInstanceAlreadyCompleteException
					| JobParametersInvalidException e) {
				e.printStackTrace();
			}
			System.out.println("job status : " + jobExecution.getStatus());

		}).start();
		return "working on data...";

	}

	@Override
	public String insertTransactionsMethod2(List<BankTransactionDto> transactions) {

		new Thread(() -> {

			long date1 = new Date().getTime();
			List<List<BankTransactionDto>> lists = chunkList(transactions, 1000);
			ExecutorService threadPoolExecutor = Executors.newFixedThreadPool(20);
			List<Future<List<BankTransaction>>> futureList = new ArrayList<>();
			lists.forEach(transactionDtos -> {
				futureList.add(threadPoolExecutor.submit(() -> {
					ModelMapper modelMapper = new ModelMapper();
					modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
					List<BankTransaction> bankTransactions = transactionDtos.stream().map(bankTransactionDto -> {
						BankTransaction bankTransaction = modelMapper.map(bankTransactionDto, BankTransaction.class);
						bankTransaction.setUser(userRepositry.findFirstByUserName(bankTransactionDto.getUserName()));
						return bankTransaction;
					}).collect(Collectors.toList());
					return bankTransactionRepositry.saveAll(bankTransactions);
				}));
			});
			futureList.forEach(listFuture -> {
				try {
					listFuture.get();
					System.out.println("method 2: "+getDiffBetwnTwoDates(date1,new Date().getTime()));
				} catch (InterruptedException e) {
					e.printStackTrace();
				} catch (ExecutionException e) {
					e.printStackTrace();
				}
			});

		}).start();
		return "working on data...";
	}

	private List<List<BankTransactionDto>> chunkList(List<BankTransactionDto> transactionDtos, int chunkNumber) {
		double size = transactionDtos.size();
		int threadsNumber = (int) (size / chunkNumber);
		List<List<BankTransactionDto>> listList = new ArrayList<>();
		if (size < chunkNumber) {
			listList.add(transactionDtos);
		} else {
			for (int i = 0; i < threadsNumber; i++) {
				listList.add(transactionDtos.subList((i * chunkNumber), (i * chunkNumber) + (chunkNumber - 1)));
			}
			listList.add(transactionDtos.subList(threadsNumber * chunkNumber, transactionDtos.size()));
		}
		return listList;
	}

	String getDiffBetwnTwoDates(long date1, long date2) {
		long difference_In_Time = date2 - date1;

		long difference_In_Seconds = ((difference_In_Time / 1000) % 60)%difference_In_Time;

		long difference_In_Minutes = ((difference_In_Time / (1000 * 60)) % 60);
		
		return difference_In_Minutes + " min " + "and "+difference_In_Seconds+" seconds";

	}

}
