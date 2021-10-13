/**
 * 
 */
package com.evision.task.service;

import java.util.ArrayList;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.evision.task.data.entity.UserEntity;
import com.evision.task.data.model.UserDto;
import com.evision.task.exception.ExceptionFactory;
import com.evision.task.repositry.UserRepositry;

/**
 * @author Mohamed Ramadan
 *
 */

@Service
public class UserServiceImp implements UserService {

	@Autowired
	UserRepositry userRepositry;

	@Autowired
	BCryptPasswordEncoder bcryptPassword;

	@Override
	public UserDto createUser(UserDto user) {
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		user.setEncryptedPassword(bcryptPassword.encode(user.getPassword()));
		UserEntity userEntity = modelMapper.map(user, UserEntity.class);
		UserEntity userEntitySaved = userRepositry.save(userEntity);
		UserDto userDto = modelMapper.map(userEntitySaved, UserDto.class);
		return userDto;
	}

	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		UserEntity userEntity = userRepositry.findFirstByUserName(userName);
		if (userEntity == null)
			throw new ExceptionFactory.EntityNotFoundException("user: "+ userName + " notfound");
		return new User(userEntity.getUserName(), userEntity.getEncryptedPassword(), true, true, true, true, new ArrayList<>());
	}

	@Override
	public UserDto findByUserName(String userName) {
		// TODO Auto-generated method stub
		UserEntity userEntity = userRepositry.findFirstByUserName(userName);
		if (userEntity == null)
			throw new ExceptionFactory.EntityNotFoundException("user: "+ userName + " notfound");;
		return new ModelMapper().map(userEntity, UserDto.class);
	}

}
