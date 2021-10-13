/**
 * 
 */
package com.evision.task.repositry;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.evision.task.data.entity.BankTransaction;
import com.evision.task.data.entity.UserEntity;

/**
 * @author Mohamed Ramadan
 *
 */
@Repository
public interface BankTransactionRepositry extends JpaRepository<BankTransaction, Long> {

}

