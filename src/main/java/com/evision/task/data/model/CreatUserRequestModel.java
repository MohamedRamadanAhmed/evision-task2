/**
 * 
 */
package com.evision.task.data.model;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Mohamed Ramadan
 *
 */
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class CreatUserRequestModel {

	@NotNull(message = "username cant be null")
	public String userName;
	@NotNull(message = "password can't be null")
	@NotEmpty
	public String password;

}
