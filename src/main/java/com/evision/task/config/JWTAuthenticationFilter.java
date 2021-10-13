/**
 * 
 */
package com.evision.task.config;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import com.evision.task.data.model.LoginRequestModel;
import com.evision.task.data.model.UserDto;
import com.evision.task.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

/**
 * @author Mohamed Ramadan
 *
 */
public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

	UserService userService;
	Environment env;

	public JWTAuthenticationFilter(UserService userService, Environment env,
			AuthenticationManager authenticationManager) {
		this.userService = userService;
		this.env = env;
		super.setAuthenticationManager(authenticationManager);

	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {
		LoginRequestModel loginRequestModel;

		try {
			loginRequestModel = new ObjectMapper().readValue(request.getInputStream(), LoginRequestModel.class);
			return this.getAuthenticationManager()
					.authenticate(new UsernamePasswordAuthenticationToken(loginRequestModel.getUserName(),
							loginRequestModel.getPassword(), new ArrayList<GrantedAuthority>()));
		} catch (IOException e) {
			throw new RuntimeException(e);
		}

	}

	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {
		String authorizationHeaderToken = request.getHeader(env.getProperty("authorization.token.header.name"));
		if (authorizationHeaderToken != null
				&& authorizationHeaderToken.startsWith(env.getProperty("authorization.token.header.prefix"))) {
			UsernamePasswordAuthenticationToken authentication = getAuthentication(request);
			SecurityContextHolder.getContext().setAuthentication(authentication);
			chain.doFilter(request, response);
			return;

		}
		String email = ((User) authResult.getPrincipal()).getUsername();
		UserDto userDto = userService.findByUserName(email);
		String token = Jwts.builder().setSubject(userDto.getUserName())
				.setExpiration(
						new Date(System.currentTimeMillis() + Long.parseLong(env.getProperty("token.expiration_time"))))
				.signWith(SignatureAlgorithm.HS512, env.getProperty("token.secret")).compact();
		response.addHeader("token", token);
		response.addHeader("userName", userDto.getUserName());

	}

	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		String authorizationHeaderToken = request.getHeader(env.getProperty("authorization.token.header.name"));

		if (authorizationHeaderToken == null
				|| !authorizationHeaderToken.startsWith(env.getProperty("authorization.token.header.prefix"))) {
			chain.doFilter(request, response);
			return;

		}

	}

	UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request) {
		String authorizationHeaderToken = request.getHeader(env.getProperty("authorization.token.header.name"));

		if (authorizationHeaderToken == null) {
			return null;

		}
		String token = authorizationHeaderToken.replace(env.getProperty("authorization.token.header.prefix"), "");
		String userName = Jwts.parser().setSigningKey(env.getProperty("token.secret")).parseClaimsJws(token).getBody()
				.getSubject();
		if (userName == null) {
			return null;
		}
		return new UsernamePasswordAuthenticationToken(userName, null, new ArrayList<>());
	}

}
