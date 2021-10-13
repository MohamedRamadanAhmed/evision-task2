/**
 * 
 */
package com.evision.task.config.batch;

import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.evision.task.data.entity.BankTransaction;
import com.evision.task.repositry.BankTransactionRepositry;
import java.util.List;

/**
 * @author Mohamed Ramadan
 *
 */

@Component
public class DBWriter implements ItemWriter<BankTransaction> {

	private BankTransactionRepositry bankTransactionRepositry;

	@Autowired
	public DBWriter(BankTransactionRepositry bankTransactionRepositry) {
		this.bankTransactionRepositry = bankTransactionRepositry;
	}

	@Override
	public void write(List<? extends BankTransaction> transactions) throws Exception {
		bankTransactionRepositry.saveAll(transactions);
	}
}
