package com.todoapp.web.security;

import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;


public class Security {
	
	public static boolean isSecurityPage(HttpServletRequest request) {
        String urlPattern = UrlPattern.getUrlPattern(request);
 
        Set<String> roles = SecurityConfig.getAllAppRoles();
 
        for (String role : roles) {
            List<String> urlPatterns = SecurityConfig.getUrlPatternsForRole(role);
            if (urlPatterns != null && urlPatterns.contains(urlPattern)) {
                return true;
            }
        }
        return false;
    }
}
