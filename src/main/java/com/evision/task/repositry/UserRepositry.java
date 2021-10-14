/**
 * 
 */
package com.evision.task.repositry;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.evision.task.data.entity.UserEntity;


/**
 * @author Mohamed Ramadan
 *
 */
@Repository
@Transactional
public interface UserRepositry extends JpaRepository<UserEntity, Long> {
	UserEntity findFirstByUserName (String userName);

}
