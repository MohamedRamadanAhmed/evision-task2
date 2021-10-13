/**
 * 
 */
package com.evision.task.config;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import com.evision.task.service.UserService;

import io.jsonwebtoken.Jwts;

/**
 * @author Mohamed Ramadan
 *
 */
public class BAuthenticationFilter extends OncePerRequestFilter {

	private Environment env;
	private UserService userService;

	public BAuthenticationFilter( Environment env,UserService userService) {
		this.env = env;
		this.userService = userService;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		String authorizationHeaderToken = request.getHeader(env.getProperty("authorization.token.header.name"));

		if (authorizationHeaderToken == null
				|| !authorizationHeaderToken.startsWith(env.getProperty("authorization.token.header.prefix"))) {
			chain.doFilter(request, response);
			return;

		}
		UsernamePasswordAuthenticationToken authentication;
		try {
			authentication = getAuthentication(request);
			SecurityContextHolder.getContext().setAuthentication(authentication);
		} catch (Exception e) {

			e.printStackTrace();
			chain.doFilter(request, response);
			return;
		}
		chain.doFilter(request, response);

	}

	UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request) throws Exception {
		String authorizationHeaderToken = request.getHeader(env.getProperty("authorization.token.header.name"));

		if (authorizationHeaderToken == null) {
			return null;

		}
		String token = authorizationHeaderToken.replace(env.getProperty("authorization.token.header.prefix"), "");

		String userName = Jwts.parser().setSigningKey(env.getProperty("token.secret")).parseClaimsJws(token).getBody()
				.getSubject();

		if (userName == null) {
			throw new Exception();
		} else if (userName != null) {
			userService.loadUserByUsername(userName);
		}
		return new UsernamePasswordAuthenticationToken(userName, null, new ArrayList<>());
	}

}
