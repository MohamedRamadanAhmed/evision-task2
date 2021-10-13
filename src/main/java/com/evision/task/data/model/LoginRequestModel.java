/**
 * 
 */
package com.evision.task.data.model;

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
public class LoginRequestModel {
	public String userName;
	public String password;

}
