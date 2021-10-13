/**
 * 
 */
package com.evision.task.data.model;


import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @author Mohamed Ramadan
 *
 */
@Data
@Getter
@NoArgsConstructor
public class UserDto {

	private String userName;
	private String password;
	private String encryptedPassword;
	

}
