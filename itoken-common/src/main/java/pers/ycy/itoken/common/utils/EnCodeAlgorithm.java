package pers.ycy.itoken.common.utils;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class EnCodeAlgorithm {
	/**
	 *  用于对密码加密的算法.
	 * @param passWord 密码的原型
	 * @return 返回一个加密之后的 BASE64字符串. 先SHA-256,后MD5.
	 */
	public static String enCodePassWord(String passWord) {
		try {
			// SHA-256 来一次.
			String temp = enCode(passWord, "SHA-256");
			return enCode(temp, "MD5");
		} catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return null;
	}
	private static String enCode(String passWord,String method) throws NoSuchAlgorithmException, UnsupportedEncodingException {
		MessageDigest md = MessageDigest.getInstance(method);
		byte[] mdByte = md.digest(passWord.getBytes(StandardCharsets.UTF_8));
		byte[] resultDigest = Base64.getEncoder().encode(mdByte);
		return new String(resultDigest);
	}

}