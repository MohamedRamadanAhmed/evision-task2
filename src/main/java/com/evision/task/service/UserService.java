/**
 * 
 */
package com.evision.task.service;

import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.evision.task.data.model.UserDto;

/**
 * @author Mohamed Ramadan
 *
 */
public interface UserService extends UserDetailsService {
	UserDto createUser(UserDto user);
	public UserDto findByUserName(String userName) ;

}
