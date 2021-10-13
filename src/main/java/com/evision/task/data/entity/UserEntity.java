/**
 * 
 */
package com.evision.task.data.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
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
@Table(name = "user")
public class UserEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id_Sequence")
	@SequenceGenerator(name = "id_Sequence", sequenceName = "ORACLE_DB_SEQ_ID", allocationSize = 1000)
	private Long id;
	@Column(length = 300, unique = true)
	private String userName;
	@Column(name = "e_password")
	private String encryptedPassword;

	@OneToMany(mappedBy = "user")
	private List<BankTransaction> transactions = new ArrayList<BankTransaction>();

}
