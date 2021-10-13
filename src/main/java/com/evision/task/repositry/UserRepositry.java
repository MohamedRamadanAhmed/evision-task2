/**
 * 
 */
package com.evision.task.repositry;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.evision.task.data.entity.UserEntity;


/**
 * @author Mohamed Ramadan
 *
 */
@Repository
public interface UserRepositry extends JpaRepository<UserEntity, Long> {
//	@Query(value = "from UserEntity u where u.userName =:userName")
//	@Param(value = "userName") 
	UserEntity findFirstByUserName (String userName);

}
