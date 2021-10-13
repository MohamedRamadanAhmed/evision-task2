/**
 * 
 */
package com.evision.task.data.model;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Mohamed Ramadan
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BankTransactionDto {
	
	@NotEmpty
	private String transactionType;
	@DecimalMin(value = "0.0")
	private double oldBalance;
	@DecimalMin(value = "0.0")
	private double newBalance;
	@NotEmpty
	private String userName;
	

}
