package com.todoapp.web.security;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.todoapp.web.entities.User;

public class Security {

//	public static boolean isSecurityPage(HttpServletRequest request) {
//		String urlPattern = UrlPattern.getUrlPattern(request);
//
//		Set<String> roles = SecurityConfig.getAllAppRoles();
//
//		for (String role : roles) {
//			List<String> urlPatterns = SecurityConfig.getUrlPatternsForRole(role);
//			if (urlPatterns != null && urlPatterns.contains(urlPattern)) {
//				return true;
//			}
//		}
//		return false;
//	}
//
//	public static boolean hasPermission(HttpServletRequest request) {
//		String urlPattern = UrlPattern.getUrlPattern(request);
//
//		Set<String> allRoles = SecurityConfig.getAllAppRoles();
//
//		for (String role : allRoles) {
//			if (!request.isUserInRole(role)) {
//				continue;
//			}
//			List<String> urlPatterns = SecurityConfig.getUrlPatternsForRole(role);
//			if (urlPatterns != null && urlPatterns.contains(urlPattern)) {
//				return true;
//			}
//		}
//		return false;
//	}

	public static void storeLoggedUser(HttpSession session, User user) {
		// On the JSP can access via ${loginedUser}
		session.setAttribute("user", user);
	}

	public static void storeKeysSession(HttpSession session, Keys keys) {
		session.setAttribute("privatekey", keys.getPrivateKey());
		session.setAttribute("publickey", keys.getPublicKey());
	}

	public static String sha1(String pwd) throws NoSuchAlgorithmException {
		MessageDigest mDigest = MessageDigest.getInstance("SHA1");
		byte[] result = mDigest.digest(pwd.getBytes());
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < result.length; i++) {
			sb.append(Integer.toString((result[i] & 0xff) + 0x100, 16).substring(1));
		}

		return sb.toString();
	}
}
