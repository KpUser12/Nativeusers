package com.example.demo.util;

import java.security.*;
import java.math.BigInteger;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.SecretKeyFactory;
import java.security.spec.InvalidKeySpecException;

public class HashPassword {

	String userID = null;
	int itrKey = 0;

	public static void main(String[] args) {
		HashPassword obj = new HashPassword("asro");
		try {
			String secPass = obj.getHashedPassword("Kapstone@1234");
			// System.out.println(secPass);
			String secPass1 = "537:9606698ad792a98dad424c70b6a94612:3385bc74a90b3fab48ed374a17da15a2ca83d6e296a2ddb7a1c743b4f025098e478f50630e71a174e0426970af36a779ee554844bb781cf10c39c68e6f3cb1d2";
           
			boolean res = obj.validatePassword("Kapstone@1234", secPass1);

		} catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public HashPassword(String uid) {
		this.userID = uid;
	}

	public String getHashedPassword(String password) throws NoSuchAlgorithmException, InvalidKeySpecException {
		this.itrKey = getIntValueForUserId();
		return createStrongPasswordHash(password);
	}

	private int getIntValueForUserId() {
		int retVal = 100;
		for (int k = 0; k < this.userID.length(); k++) {
			char c = this.userID.charAt(k);
			int m = (int) c;
			retVal = retVal + m;
		}
		return retVal;
	}

	private String createStrongPasswordHash(String password) throws NoSuchAlgorithmException, InvalidKeySpecException {
		// number of iteration used
		char[] charArr = password.toCharArray();
		byte[] saltArr = obtainSalt();

		PBEKeySpec pbeSpec = new PBEKeySpec(charArr, saltArr, this.itrKey, 64 * 8);

		// using PBKDF2WithHmacSHA1 for hashing
		SecretKeyFactory secKeyFact = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
		byte[] hashArr = secKeyFact.generateSecret(pbeSpec).getEncoded();
		return this.itrKey + ":" + intoHex(saltArr) + ":" + intoHex(hashArr);
	}

	// getting the salt
	private byte[] obtainSalt() throws NoSuchAlgorithmException {
		SecureRandom secRand = SecureRandom.getInstance("SHA1PRNG");
		byte[] saltArr = new byte[16];
		secRand.nextBytes(saltArr);
		return saltArr;
	}

	// converting into hexadecimal
	private static String intoHex(byte[] arr) throws NoSuchAlgorithmException {
		BigInteger bInt = new BigInteger(1, arr);
		String hexStr = bInt.toString(16);
		int paddingLen = (arr.length * 2) - hexStr.length();
		if (paddingLen > 0) {
			return String.format("%0" + paddingLen + "d", 0) + hexStr;
		} else {
			return hexStr;
		}
	}

	public boolean validatePassword(String originalPassword, String storedPassword)
			throws NoSuchAlgorithmException, InvalidKeySpecException {
		String[] parts = storedPassword.split(":");
		int iterations = Integer.parseInt(parts[0]);

		byte[] salt = fromHex(parts[1]);
		byte[] hash = fromHex(parts[2]);

		System.out.println();

		PBEKeySpec spec = new PBEKeySpec(originalPassword.toCharArray(), salt, iterations, hash.length * 8);
		SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
		byte[] testHash = skf.generateSecret(spec).getEncoded();

		int diff = hash.length ^ testHash.length;
		for (int i = 0; i < hash.length && i < testHash.length; i++) {
			diff |= hash[i] ^ testHash[i];
		}
		return diff == 0;
	}

	private byte[] fromHex(String hex) throws NoSuchAlgorithmException {
		byte[] bytes = new byte[hex.length() / 2];
		for (int i = 0; i < bytes.length; i++) {
			bytes[i] = (byte) Integer.parseInt(hex.substring(2 * i, 2 * i + 2), 16);
		}
		return bytes;
	}
}
