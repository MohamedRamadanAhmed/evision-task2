/**
 * 
 */
package com.evision.task.controller;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.evision.task.data.model.CreatUserRequestModel;
import com.evision.task.data.model.UserDto;
import com.evision.task.data.response.Response;
import com.evision.task.service.UserService;

/**
 * @author Mohamed Ramadan
 *
 */
@RestController
@Transactional
public class UserController {

	@Autowired
	private UserService userService;

	@PostMapping(path = "/user",produces = {"application/json"} )
	public @ResponseBody Response<Object> creatUser(@RequestBody @Valid CreatUserRequestModel creatUserRequestModel) {
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		UserDto userDto = modelMapper.map(creatUserRequestModel, UserDto.class);
		return Response.created().setPayload(userService.createUser(userDto));
	}

}
