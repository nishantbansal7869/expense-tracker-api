package com.expensetrackerapi.expensetracker.filters;

import com.expensetrackerapi.expensetracker.Constants;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.http.HttpStatus;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AuthFilter extends GenericFilterBean {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httResponse = (HttpServletResponse) servletResponse;
        String authHeader = httpRequest.getHeader("Authorization");
        if(null != authHeader){
            String[] authHeaderarr = authHeader.split("Bearer");
            if(authHeaderarr.length > 1 && authHeaderarr[1] != null){
                String token = authHeaderarr[1];
                try {
                    Claims claims = Jwts.parser().setSigningKey(Constants.API_SECRET_KEY)
                            .parseClaimsJws(token).getBody();
                    httpRequest.setAttribute("userId", Integer.parseInt(claims.get("userId").toString()));
                }catch (Exception e){
                    httResponse.sendError(HttpStatus.FORBIDDEN.value(), "User not logged in");
                    return;
                }
            }
            else {
                httResponse.sendError(HttpStatus.FORBIDDEN.value(), "Authorization token must be Bearer [token]");
                return;
            }
        }
        else {
            httResponse.sendError(HttpStatus.FORBIDDEN.value(), "Authorization token must be provided");
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }
}
