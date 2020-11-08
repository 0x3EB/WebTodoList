package com.todoapp.web.security;

import java.math.BigInteger;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Base64;
import java.util.List;
import java.util.Set;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.todoapp.web.entities.User;

public class Security {

	/**
	 * Function that store the Logged user in session to access during the runing
	 * time
	 * 
	 * @param session
	 * @param user
	 */
	public static void storeLoggedUser(HttpSession session, User user) {
		// On the JSP can access via ${loginedUser}
		session.setAttribute("user", user);
	}

	/**
	 * Function that store the key pair in session to access during the runing time
	 * 
	 * @param session
	 * @param keys
	 */
	public static void storeKeysSession(HttpSession session, Keys keys) {
		session.setAttribute("privatekey", keys.getPrivateKey());
		session.setAttribute("publickey", keys.getPublicKey());
	}

	/**
	 * Function that encrypt a string using th SHA1 algorithm
	 * 
	 * @param pwd
	 * @return
	 * @throws NoSuchAlgorithmException
	 */
	public static String sha1(String pwd) throws NoSuchAlgorithmException {
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-1");
			byte[] messageDigest = md.digest(pwd.getBytes());

			BigInteger no = new BigInteger(1, messageDigest);
			String hashtext = no.toString(16);

			while (hashtext.length() < 32) {
				hashtext = "0" + hashtext;
			}
			System.out.println(hashtext);
			return hashtext;
		}

		catch (NoSuchAlgorithmException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * Function that encrypt a string using the public key and the RSA algorithm and
	 * return it as Byte
	 * 
	 * @param data
	 * @param publicKey
	 * @return
	 * @throws BadPaddingException
	 * @throws IllegalBlockSizeException
	 * @throws InvalidKeyException
	 * @throws NoSuchPaddingException
	 * @throws NoSuchAlgorithmException
	 */
	public static byte[] encrypt(String data, PublicKey publicKey) throws BadPaddingException,
			IllegalBlockSizeException, InvalidKeyException, NoSuchPaddingException, NoSuchAlgorithmException {
		Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding"); // depends on the RSA module used
		cipher.init(Cipher.ENCRYPT_MODE, publicKey);
		return cipher.doFinal(data.getBytes());
	}

	/**
	 * Function that encrypt a string using the public key and the RSA algorithm and
	 * return the encrypted string
	 * 
	 * @param data
	 * @param publicKey
	 * @return
	 * @throws InvalidKeyException
	 * @throws BadPaddingException
	 * @throws IllegalBlockSizeException
	 * @throws NoSuchPaddingException
	 * @throws NoSuchAlgorithmException
	 */
	public static String EncryptedString(String data, PublicKey publicKey) throws InvalidKeyException,
			BadPaddingException, IllegalBlockSizeException, NoSuchPaddingException, NoSuchAlgorithmException {
		return Base64.getEncoder().encodeToString(encrypt(data, publicKey));
	}

	/**
	 * Function that decrypt the encrypted byte (RSA) using the private key using
	 * Byte in parameter
	 * 
	 * @param data
	 * @param privateKey
	 * @return
	 * @throws NoSuchPaddingException
	 * @throws NoSuchAlgorithmException
	 * @throws InvalidKeyException
	 * @throws BadPaddingException
	 * @throws IllegalBlockSizeException
	 */
	public static String decrypt(byte[] data, PrivateKey privateKey) throws NoSuchPaddingException,
			NoSuchAlgorithmException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
		Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
		cipher.init(Cipher.DECRYPT_MODE, privateKey);
		return new String(cipher.doFinal(data));
	}

	/**
	 * Function that decrypt the encrypted string (RSA) using the private key using
	 * string in parameter
	 * 
	 * @param data
	 * @param key
	 * @return
	 * @throws IllegalBlockSizeException
	 * @throws InvalidKeyException
	 * @throws BadPaddingException
	 * @throws NoSuchAlgorithmException
	 * @throws NoSuchPaddingException
	 */
	public static String decrypt(String data, PrivateKey key) throws IllegalBlockSizeException, InvalidKeyException,
			BadPaddingException, NoSuchAlgorithmException, NoSuchPaddingException {
		return decrypt(Base64.getDecoder().decode(data.getBytes()), key);
	}
}
