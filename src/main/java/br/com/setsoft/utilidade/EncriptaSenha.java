package br.com.setsoft.utilidade;

import java.io.UnsupportedEncodingException;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public final class EncriptaSenha {
	
	/**md5** gera string de 32 caracteres (de 0 a 9 e/ou de A a F)*/
	public static String encriptaMD5(String senhaOriginal)
			throws NoSuchAlgorithmException, UnsupportedEncodingException {

		try {
			if(senhaOriginal == null || senhaOriginal.equals("")) return null;
			MessageDigest algorithm = MessageDigest.getInstance("MD5");
			BigInteger hash = new BigInteger(1, algorithm.digest(senhaOriginal.getBytes("UTF-8")));
			String senhaEncriptografada = hash.toString(16);
			if(senhaEncriptografada.length() % 2 != 0)
				senhaEncriptografada = "0" + senhaEncriptografada;
			return senhaEncriptografada;
		} catch (NoSuchAlgorithmException ns) {
			ns.printStackTrace();
			return null;
		} catch (UnsupportedEncodingException us) {
			us.printStackTrace();
			return null;
		}
	}
	
	/**sha-256** gera string de 64 caracteres (de 0 a 9 e/ou de A a F)
	 * Esse algoritmo está sendo usado no projeto
	 * @author Joel Marques
	 * @since Abril 06, 2012*/
	public static String encriptaSHA256(String senhaOriginal) {
		
		if (org.apache.commons.lang3.StringUtils.isBlank(senhaOriginal)) {
			return null;
		}
		
		try {
			
			MessageDigest algorithm = MessageDigest.getInstance("SHA-256");
			BigInteger hash = new BigInteger(1, algorithm.digest(senhaOriginal.getBytes("UTF-8")));
			String senhaEncriptografada = hash.toString(16);
			
			if (senhaEncriptografada.length() % 2 != 0) {//para acrescentar o ZERO para completar os 64 caracteres
				senhaEncriptografada = "0" + senhaEncriptografada;
			}
			
			return senhaEncriptografada;
			
		} catch (NoSuchAlgorithmException ns) {
			ns.printStackTrace();
			return null;
		} catch (UnsupportedEncodingException us){
			us.printStackTrace();
			return null;
		}
	}	
	
	/**sha-256 modificada** gera string de 64 caracteres (de 0 a 9 e/ou de A a F)*/
	public static String encriptaSHA256Modificada(String senhaOriginal)
			throws NoSuchAlgorithmException, UnsupportedEncodingException {
		
		if (org.apache.commons.lang3.StringUtils.isBlank(senhaOriginal)) {
			return null;
		}

		try {
			MessageDigest algorithm = MessageDigest.getInstance("SHA-256");
			byte messageDigest[] = algorithm.digest(senhaOriginal.getBytes("UTF-8"));
			StringBuilder hexString = new StringBuilder();
			for (byte b : messageDigest) {
				hexString.append(String.format("%02X", 0xFF & b));
			}
			String senhaEncriptografada = hexString.toString().toLowerCase();
			return senhaEncriptografada;
		} catch (NoSuchAlgorithmException ns) {
			ns.printStackTrace();
			return null;
		} catch (UnsupportedEncodingException us){
			us.printStackTrace();
			return null;
		}
	}
}