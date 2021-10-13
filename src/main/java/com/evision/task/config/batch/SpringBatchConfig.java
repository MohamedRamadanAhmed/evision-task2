/**
 * 
 */
package com.evision.task.config.batch;

import com.evision.task.data.entity.BankTransaction;
import com.evision.task.data.model.BankTransactionDto;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.List;

/**
 * @author Mohamed Ramadan
 *
 */
@Configuration
@EnableBatchProcessing
public class SpringBatchConfig {

	private JobBuilderFactory jobBuilderFactory;
	private ItemWriter<BankTransaction> itemWriter;
	private ItemProcessor<BankTransactionDto, BankTransaction> itemProcessor;
	private StepBuilderFactory stepBuilderFactory;

	@Autowired
	public SpringBatchConfig(JobBuilderFactory jobBuilderFactory, ItemWriter<BankTransaction> itemWriter,
			ItemProcessor<BankTransactionDto, BankTransaction> itemProcessor, StepBuilderFactory stepBuilderFactory,
			TaskExecutor taskExecutorBatch) {
		super();
		this.jobBuilderFactory = jobBuilderFactory;
		this.itemWriter = itemWriter;
		this.itemProcessor = itemProcessor;
		this.stepBuilderFactory = stepBuilderFactory;
	}

	public Job createJob(List<BankTransactionDto> transactions

	) {

		Step step = stepBuilderFactory.get("JOB-List-load").<BankTransactionDto, BankTransaction>chunk(1000)
				.reader(new OwnListItemReader<>(transactions)).processor(itemProcessor).writer(itemWriter)
				.taskExecutor(taskExecutorBatch()).build();

		return jobBuilderFactory.get("JOB-Load").incrementer(new RunIdIncrementer())
				.listener(new TransactionJobListiner()).start(step).build();
	}

	public TaskExecutor taskExecutorBatch() {
		ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
		executor.setCorePoolSize(5);
		executor.setMaxPoolSize(10);
		executor.setQueueCapacity(25);
		executor.afterPropertiesSet();
		return executor;
	}

}
