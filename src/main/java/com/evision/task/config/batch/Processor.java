/**
 * 
 */
package com.evision.task.config.batch;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.evision.task.data.entity.BankTransaction;
import com.evision.task.data.model.BankTransactionDto;
import com.evision.task.repositry.UserRepositry;

/**
 * @author Mohamed Ramadan
 *
 */

@Component
public class Processor implements ItemProcessor<BankTransactionDto, BankTransaction> {
	@Autowired
	UserRepositry userRepositry;

	@Override
	public BankTransaction process(BankTransactionDto bankTransactionDto) throws Exception {
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		BankTransaction bankTransaction = modelMapper.map(bankTransactionDto, BankTransaction.class);
		bankTransaction.setUser(userRepositry.findFirstByUserName(bankTransactionDto.getUserName()));

		return bankTransaction;
	}

}