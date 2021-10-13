/**
 * 
 */
package com.evision.task.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.evision.task.service.UserService;



/**
 * @author Mohamed Ramadan
 *
 */
@Configuration
@EnableWebSecurity
public class WebSecurity extends WebSecurityConfigurerAdapter {
	@Autowired
	UserService userService ;
	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;
	
	
	Environment env;
	@Autowired
	public WebSecurity(Environment env) {
		super();
		this.env = env;
	}

	@Override
	public void configure(HttpSecurity http) throws Exception {
		http.csrf().disable();
		http.authorizeRequests().antMatchers("/h2-console/**", "/resources/**", "/webjars/**")
        .permitAll().and().authorizeRequests().antMatchers(env.getProperty("api.registration.path")).permitAll()
        .anyRequest().authenticated()
        .and().addFilter(getAuthenticationFilter()).addFilterBefore(new BAuthenticationFilter(env,userService),JWTAuthenticationFilter.class);//permitAll();
		http.headers().frameOptions().disable();
		
		
	}
	private JWTAuthenticationFilter getAuthenticationFilter () throws Exception {
		JWTAuthenticationFilter authenticationFilter = new JWTAuthenticationFilter(userService,env,authenticationManager());
		authenticationFilter.setFilterProcessesUrl(env.getProperty("api.login.path"));
		return authenticationFilter;
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userService).passwordEncoder(bCryptPasswordEncoder);
	}

}
