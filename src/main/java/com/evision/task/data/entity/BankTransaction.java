/**
 * 
 */
package com.evision.task.data.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Mohamed Ramadan
 *
 */
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "bank_transaction")
public class BankTransaction {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id_Sequence")
	@SequenceGenerator(name = "id_Sequence", sequenceName = "ORACLE_DB_SEQ_ID", allocationSize = 1000)
	private Long id;
	@Column(length = 300)
	private String transactionType;
	@Column(nullable = false)
	private double oldBalance;
	@Column(nullable = false)
	private double newBalance;

	@ManyToOne
	@JoinColumn(name = "user_name", nullable = false)
	private UserEntity user = new UserEntity();

}
