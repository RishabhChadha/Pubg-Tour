package com.stackroute.favouriteservice.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.web.filter.GenericFilterBean;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

public class JwtFilter extends GenericFilterBean {

	public JwtFilter() {
		// TODO Auto-generated constructor stub
	}

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
                                    throws IOException, ServletException {
                    // TODO Auto-generated method stub
                    
                    final HttpServletRequest request = (HttpServletRequest)req;
                    final HttpServletResponse response = (HttpServletResponse)res;
                    final String authHeader = request.getHeader("authorization");
                    if("OPTIONS".equals(request.getMethod())){
                                    response.setStatus(HttpServletResponse.SC_OK);
                                    chain.doFilter(req, res);
                    }
                    else{
                                    if(authHeader == null || !authHeader.startsWith("Bearer ")){
                                                    throw new ServletException("Missing or invalid authentication Header");
                                    }
                                    
                                    final String token = authHeader.substring(7);
                                    final Claims claims = Jwts.parser().setSigningKey("securitykey").parseClaimsJws(token).getBody();
                                    
                                    request.setAttribute("claims", claims);
                                    chain.doFilter(req,res);
                    }

    }

}
